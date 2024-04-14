package br.com.will.payments.domain.exceptions.config

abstract class ErrorCodeAbstract(val key: String, val value: String, var parameters: Array<String>? = null)