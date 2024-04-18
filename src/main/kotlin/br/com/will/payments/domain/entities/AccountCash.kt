package br.com.will.payments.domain.entities

import java.time.LocalDateTime

data class AccountCash(
        val id: Int?,
        val userId: Int?,
        val balance: Float?,
        val createdAt: LocalDateTime?,
        val updatedAt: LocalDateTime?
) {

    data class Builder(
            var id: Int? = null,
            var userId: Int? = null,
            var balance: Float? = 0F,
            var createdAt: LocalDateTime? = null,
            var updatedAt: LocalDateTime? = null
    ) {
        fun id(id: Int?) = apply { this.id = id }
        fun userId(userId: Int?) = apply { this.userId = userId }
        fun balance(balance: Float?) = apply { this.balance = balance }
        fun createdAt(createdAt: LocalDateTime?) = apply { this.createdAt = createdAt }
        fun updatedAt(updatedAt: LocalDateTime?) = apply { this.updatedAt = updatedAt }
        fun build() = AccountCash(id, userId, balance, createdAt, updatedAt)
    }
}