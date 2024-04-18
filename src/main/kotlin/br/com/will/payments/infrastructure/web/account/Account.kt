package br.com.will.payments.infrastructure.web.account

import java.time.LocalDateTime

data class CreateAccount(
        val userId: Int
        )

data class AccountRequest(
        val userId: Int,
        val addCash: Float
)

class AccountResponse private constructor(
        val userId: Int?,
        val balance: Float?,
        val createdAt: LocalDateTime? = null,
        val updatedAt: LocalDateTime? = null
) {

    data class Builder(
            var userId: Int? = null,
            var balance: Float? = null,
            var createdAt: LocalDateTime? = null,
            var updatedAt: LocalDateTime? = null
    ) {

        fun userId(userId: Int?) = apply { this.userId = userId }
        fun balance(balance: Float?) = apply { this.balance = balance }
        fun createdAt(createdAt: LocalDateTime?) = apply { this.createdAt = createdAt }
        fun updatedAt(updatedAt: LocalDateTime?) = apply { this.updatedAt = updatedAt }
        fun build() = AccountResponse(userId, balance, createdAt, updatedAt)
    }
}