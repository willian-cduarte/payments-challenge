package br.com.will.payments.domain.exceptions.config

data class ValidationFieldError(val objectName: String, val field: String, val errorCodeConstants: ErrorCodeConstants)
