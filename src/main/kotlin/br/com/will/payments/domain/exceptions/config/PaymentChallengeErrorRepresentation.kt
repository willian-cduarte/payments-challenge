package br.com.will.payments.domain.exceptions.config

data class PaymentChallengeErrorRepresentation (
        val key: String,
        val message: String? = null,
        val fields: Map<String, MutableList<String>?>? = null,
        val originalError: String? = null
)