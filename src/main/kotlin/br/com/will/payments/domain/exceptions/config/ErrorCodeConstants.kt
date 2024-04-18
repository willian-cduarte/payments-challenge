package br.com.will.payments.domain.exceptions.config

class ErrorCodeConstants: ErrorCodeAbstract {

    constructor(key: String, value: String, parameters: Array<String>? = null) : super(key, value, parameters)
    constructor(key: String) : super(key, key.lowercase().replace("_", "."))

    companion object {
        val INTERNAL_SERVER_ERROR: ErrorCodeConstants = ErrorCodeConstants("INTERNAL_SERVER_ERROR")
        val VALIDATION_EXCEPTION: ErrorCodeConstants = ErrorCodeConstants("VALIDATION_EXCEPTION")
        val FIELD_VALUE_INVALID: ErrorCodeConstants = ErrorCodeConstants("FIELD_VALUE_INVALID")
        val FIELD_VALUE_CANNOT_BE_NULL: ErrorCodeConstants = ErrorCodeConstants("FIELD_VALUE_CANNOT_BE_NULL")
        val FIELD_VALUE_EXISTS: ErrorCodeConstants = ErrorCodeConstants("FIELD_VALUE_EXISTS")
        val EMAIL_ALREADY_EXISTS: ErrorCodeConstants = ErrorCodeConstants("EMAIL_ALREADY_EXISTS")
        val DOCUMENT_ALREADY_EXISTS: ErrorCodeConstants = ErrorCodeConstants("DOCUMENT_ALREADY_EXISTS")
        val NOT_FOUND: ErrorCodeConstants = ErrorCodeConstants("NOT_FOUND")
        val USER_NOT_FOUND: ErrorCodeConstants = ErrorCodeConstants("NOT_FOUND", "user.not.found")
        val NOT_ACCEPTABLE: ErrorCodeConstants = ErrorCodeConstants("NOT_ACCEPTABLE")
        val USER_NOT_HAVE_ACCOUNT: ErrorCodeConstants = ErrorCodeConstants("NOT_FOUND", "user.not.have.account")
        val USER_ALREADY_HAS_ACCOUNT: ErrorCodeConstants = ErrorCodeConstants("USER_ALREADY_HAS_ACCOUNT")




    }
}