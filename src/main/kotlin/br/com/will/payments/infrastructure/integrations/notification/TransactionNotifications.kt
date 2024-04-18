package br.com.will.payments.infrastructure.integrations.notification


import java.time.LocalDateTime

data class NotificationResponse(
        val message: Boolean
)

data class NotificationRequest(
        val transactionId: Int?,
        val payee: UserNotification?,
        val payer: UserNotification?,
        val value: Float?,
        val transactionDateTime: LocalDateTime?
) {
    data class Builder(
            var transactionId: Int? = null,
            var payee: UserNotification? = null,
            var payer: UserNotification? = null,
            var value: Float? = null,
            var transactionDateTime: LocalDateTime? = null
    ) {
        fun transactionId(transactionId: Int?) = apply { this.transactionId = transactionId }
        fun payee(payee: UserNotification?) = apply { this.payee = payee }
        fun payer(payer: UserNotification?) = apply { this.payer = payer }
        fun value(value: Float?) = apply { this.value = value }
        fun transactionDateTime(transactionDateTime: LocalDateTime?) = apply { this.transactionDateTime = transactionDateTime }
        fun build() = NotificationRequest(transactionId, payee, payer, value, transactionDateTime)
    }
}

data class UserNotification(
        val name: String?,
        val email: String?
) {
    data class Builder(
            var name: String? = null,
            var email: String? = null
    ) {
        fun name(name: String?) = apply { this.name = name }
        fun email(email: String?) = apply { this.email = email }
        fun build() = UserNotification(name, email)
    }
}