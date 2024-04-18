package br.com.will.payments.domain.entities

import java.time.LocalDateTime

data class TransactionNotification(
        val id: Int?,
        val transactionId: Int?,
        val payee: UserNotification?,
        val payer: UserNotification?,
        val value: Float?,
        val status: NotificationStatus?,
        val createdAt: LocalDateTime?,
        val notifiedAt: LocalDateTime?,
        val updatedAt: LocalDateTime?
) {

    data class Builder(
            var id: Int? = null,
            var transactionId: Int? = null,
            var payee: UserNotification? = null,
            var payer: UserNotification? = null,
            var value: Float? = null,
            var status: NotificationStatus? = null,
            var createdAt: LocalDateTime? = null,
            var notifiedAt: LocalDateTime? = null,
            var updatedAt: LocalDateTime? = null
    ) {
        fun id(id: Int?) = apply { this.id = id }
        fun transactionId(transactionId: Int?) = apply { this.transactionId = transactionId }
        fun payee(payee: UserNotification?) = apply { this.payee = payee }
        fun payer(payer: UserNotification?) = apply { this.payer = payer }
        fun value(value: Float?) = apply { this.value = value }
        fun status(status: NotificationStatus?) = apply { this.status = status }
        fun createdAt(createdAt: LocalDateTime?) = apply { this.createdAt = createdAt }
        fun notifiedAt(notifiedAt: LocalDateTime?) = apply { this.notifiedAt = notifiedAt }
        fun updatedAt(updatedAt: LocalDateTime?) = apply { this.updatedAt = updatedAt }
        fun build() = TransactionNotification(id, transactionId, payee, payer, value, status, createdAt, notifiedAt, updatedAt)
    }
}

enum class NotificationStatus {
    TO_NOTIFY, NOTIFIED
}