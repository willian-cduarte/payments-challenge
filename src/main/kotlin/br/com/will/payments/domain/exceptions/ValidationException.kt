package br.com.will.payments.domain.exceptions

import br.com.will.payments.domain.exceptions.config.ErrorCodeConstants
import br.com.will.payments.domain.exceptions.config.ValidationFieldError

class ValidationException: BusinessException {

    private var listFieldErrors: MutableList<ValidationFieldError>? = null

    constructor(validationErrors: MutableList<ValidationFieldError>?) : super(ErrorCodeConstants.VALIDATION_EXCEPTION) {
        listFieldErrors = validationErrors
    }

    constructor(objectName: String, field: String, defaultMessage: ErrorCodeConstants) : super(ErrorCodeConstants.VALIDATION_EXCEPTION) {
        listFieldErrors =  mutableListOf<ValidationFieldError>()
        listFieldErrors?.add(ValidationFieldError(objectName, field, defaultMessage))
    }

    fun getListFieldErrors(): MutableList<ValidationFieldError>? {
        return listFieldErrors
    }
}