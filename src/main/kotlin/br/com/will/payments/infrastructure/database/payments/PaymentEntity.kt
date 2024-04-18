package br.com.will.payments.infrastructure.database.payments

import br.com.will.payments.domain.entities.Payment
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneId

@Entity
@Table(name = "payment_transactions")
class PaymentEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val transactionId: Int?,

        @Column(name = "payee", nullable = false)
        val payee: Int?,

        @Column(name = "payer", nullable = false)
        val payer: Int?,

        @Column(name = "value", nullable = false)
        val value: Float?,

        @Column(name = "created_at")
        val createdAt: Timestamp?,

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
        fun build() = PaymentEntity(transactionId, payee, payer, value, Timestamp.valueOf(createdAt))
    }

}