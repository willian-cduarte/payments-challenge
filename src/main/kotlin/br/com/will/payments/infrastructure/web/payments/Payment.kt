package br.com.will.payments.infrastructure.web.payments

import java.time.LocalDateTime

data class PaymentRequest (
        val payee: Int?,
        val payer: Int?,
        val value: Float?
)

class PaymentResponse private constructor(
        val transactionId: Int? = null,
        val payee: Int? = null,
        val payer: Int? = null,
        val value: Float? = null,
        val createdAt: LocalDateTime? = null
) {

    data class Builder(
            var transactionId: Int? = null,
            var payee: Int? = null,
            var payer: Int? = null,
            var value: Float? = null,
            var createdAt: LocalDateTime? = null
    ) {
        fun transactionId(transactionId: Int?) = apply { this.transactionId = transactionId }
        fun payee(payee: Int?) = apply { this.payee = payee }
        fun payer(payer: Int?) = apply { this.payer = payer }
        fun value(value: Float?) = apply { this.value = value }
        fun createdAt(createdAt: LocalDateTime?) = apply { this.createdAt = createdAt }
        fun build() = PaymentResponse(transactionId, payee, payer, value, createdAt)
    }
}