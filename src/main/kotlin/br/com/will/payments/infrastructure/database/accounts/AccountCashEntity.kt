package br.com.will.payments.infrastructure.database.accounts

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.sql.Timestamp

@Entity
@Table(name = "account_cash")
class AccountCashEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int?,

        @Column(name = "user_id", nullable = false, updatable = false, unique = true)
        val userId: Int?,

        @Column(name = "balance", nullable = false)
        val balance: Float?,

        @Column(name = "created_at")
        val createdAt: Timestamp?,

        @Column(name = "updated_at")
        val updatedAt: Timestamp?
) {

    data class Builder(
            var id: Int? = null,
            var userId: Int? = null,
            var balance: Float? = null,
            var createdAt: Timestamp? = null,
            var updatedAt: Timestamp? = null
    ) {
        fun id(id: Int?) = apply { this.id = id }
        fun userId(userId: Int?) = apply { this.userId = userId }
        fun balance(balance: Float?) = apply { this.balance = balance }
        fun createdAt(createdAt: Timestamp?) = apply { this.createdAt = createdAt }
        fun updatedAt(updatedAt: Timestamp?) = apply { this.updatedAt = updatedAt }
        fun build() = AccountCashEntity(id, userId, balance, createdAt, updatedAt)
    }

}