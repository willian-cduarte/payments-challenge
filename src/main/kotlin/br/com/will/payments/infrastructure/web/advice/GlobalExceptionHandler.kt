package br.com.will.payments.infrastructure.web.advice

import br.com.will.payments.domain.exceptions.BusinessException
import br.com.will.payments.domain.exceptions.NotFoundException
import br.com.will.payments.domain.exceptions.ValidationException
import br.com.will.payments.domain.exceptions.config.ErrorCodeConstants
import br.com.will.payments.domain.exceptions.config.PaymentChallengeErrorRepresentation
import br.com.will.payments.domain.exceptions.config.PaymentsChallengeException
import br.com.will.payments.domain.exceptions.config.ValidationFieldError
import org.slf4j.LoggerFactory
import org.springframework.context.MessageSource
import org.springframework.context.NoSuchMessageException
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.util.CollectionUtils
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class GlobalExceptionHandler(private val messageSource: MessageSource) {

    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException::class)
    @ResponseBody
    fun businessException(ex: BusinessException): PaymentChallengeErrorRepresentation {
        logger.error("BusinessException: {}", ex.message, ex)
        return PaymentChallengeErrorRepresentation(ex.errorCode.key, getMessageFromResource(ex), originalError = ex.originalErrorMessage)
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    @ResponseBody
    fun notFoundException(ex: NotFoundException): PaymentChallengeErrorRepresentation {
        logger.error("NotFoundException: {}", ex.message, ex)
        return PaymentChallengeErrorRepresentation(ex.errorCode.key, getMessageFromResource(ex), originalError = ex.originalErrorMessage)
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException::class)
    @ResponseBody
    fun validationException(ex: ValidationException): PaymentChallengeErrorRepresentation? {
        logger.error("ValidationException: {}", ex.message, ex)

        val fields: Map<String, MutableList<String>?>? = buildErrorMap(ex.getListFieldErrors())

        return if (fields != null) {
            PaymentChallengeErrorRepresentation(ex.errorCode.key, getMessageFromResource(ex), fields, ex.originalErrorMessage)
        } else {
            PaymentChallengeErrorRepresentation(ex.errorCode.key, getMessageFromResource(ex), originalError = ex.originalErrorMessage)
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun genericException(ex: Exception): PaymentChallengeErrorRepresentation {
        val message: String?
        val key: String = ErrorCodeConstants.INTERNAL_SERVER_ERROR.key
        val originalError = ex.javaClass.name + " - " + ex.message

        message = try {
            messageSource.getMessage(key, null, null,
                    LocaleContextHolder.getLocale())
        } catch (nsme: NoSuchMessageException) {
            ex.message
        }

        logger.error("Exception: {}", originalError, ex)
        return PaymentChallengeErrorRepresentation(key, message, null, originalError)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseBody
    fun validException(ex: MethodArgumentNotValidException): PaymentChallengeErrorRepresentation {
        val errors = StringBuffer()
        ex.bindingResult.fieldErrors.map { "${it.field}: ${getMessageFromResource(it.defaultMessage!!)}" }.joinTo(errors)

        logger.error("MethodArgumentNotValidException: {}", ex.message, ex)
        return PaymentChallengeErrorRepresentation(ErrorCodeConstants.VALIDATION_EXCEPTION.key, errors.toString())
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseBody
    fun messageNotReadableException(ex: HttpMessageNotReadableException): PaymentChallengeErrorRepresentation {
        logger.error("MethodArgumentNotValidException: {}", ex.message, ex)
        return PaymentChallengeErrorRepresentation(ErrorCodeConstants.VALIDATION_EXCEPTION.key, ex.mostSpecificCause.message.toString())
    }


    private fun buildErrorMap(errors: MutableList<ValidationFieldError>?): Map<String, MutableList<String>?>? {
        var map: MutableMap<String, MutableList<String>?>? = null

        if (errors != null) {
            map = mutableMapOf()
            for (error in errors) {
                val key = error.field
                val message = getMessageFromResource(error.errorCodeConstants)

                var fieldErrors = map[key]
                if (CollectionUtils.isEmpty(fieldErrors)) {
                    fieldErrors = mutableListOf()
                }
                message?.let {
                    fieldErrors?.add(message)
                    map[key] = fieldErrors
                }
            }
        }
        return map
    }

    private fun getMessageFromResource(ex: PaymentsChallengeException): String? {
        val message = try {
            messageSource.getMessage(ex.errorCode.value, ex.errorCode.parameters, null, LocaleContextHolder.getLocale())
        } catch (nsme: NoSuchMessageException) {
            ex.message
        }
        return message
    }

    private fun getMessageFromResource(errorCodeConstants: ErrorCodeConstants): String? {
        val message =
                messageSource.getMessage(errorCodeConstants.value, errorCodeConstants.parameters, null, LocaleContextHolder.getLocale())
                        ?: messageSource.getMessage(ErrorCodeConstants.FIELD_VALUE_INVALID.value, null, null, LocaleContextHolder.getLocale())
        return message
    }

    private fun getMessageFromResource(propertyName: String): String {
        return messageSource.getMessage(propertyName, null, null, LocaleContextHolder.getLocale())
                ?: propertyName
    }
}
