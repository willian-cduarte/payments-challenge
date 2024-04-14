package br.com.will.payments.domain.exceptions.config

class ErrorCodeConstants: ErrorCodeAbstract {

    constructor(key: String, value: String, parameters: Array<String>? = null) : super(key, value, parameters)
    constructor(key: String) : super(key, key.lowercase().replace("_", "."))

    companion object {
        val INTERNAL_SERVER_ERROR: ErrorCodeConstants = ErrorCodeConstants("INTERNAL_SERVER_ERROR")
        val VALIDATION_EXCEPTION: ErrorCodeConstants = ErrorCodeConstants("VALIDATION_EXCEPTION")
        val FIELD_VALUE_INVALID: ErrorCodeConstants = ErrorCodeConstants("FIELD_VALUE_INVALID")
        val FIELD_VALUE_IS_NULL: ErrorCodeConstants = ErrorCodeConstants("FIELD_VALUE_IS_NULL")
        val FIELD_VALUE_EXISTS: ErrorCodeConstants = ErrorCodeConstants("FIELD_VALUE_EXISTS")
        val NOT_FOUND: ErrorCodeConstants = ErrorCodeConstants("NOT_FOUND")
    }
}