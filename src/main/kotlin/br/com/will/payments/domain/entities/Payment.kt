package br.com.will.payments.domain.entities

import br.com.will.payments.infrastructure.web.payments.PaymentResponse
import java.time.LocalDateTime

data class Payment(
        val transactionId: Int?,
        val payee: Int?,
        val payer: Int?,
        val value: Float?,
        val createdAt: LocalDateTime?
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
        fun build() = Payment(transactionId, payee, payer, value, createdAt)
    }
}