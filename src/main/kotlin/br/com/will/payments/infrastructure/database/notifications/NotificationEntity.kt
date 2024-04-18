package br.com.will.payments.infrastructure.database.notifications

import br.com.will.payments.domain.entities.NotificationStatus
import jakarta.persistence.AttributeOverride
import jakarta.persistence.AttributeOverrides
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.sql.Timestamp

@Entity
@Table(name = "notifications")
class NotificationEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int?,

        @Column(name = "transaction_id", unique = true, nullable = false)
        val transactionId: Int?,

        @Embedded
        @AttributeOverrides(
                AttributeOverride( name = "name", column = Column(name = "payee_name")),
                AttributeOverride( name = "email", column = Column(name = "payee_email"))
        )
        val payee: UserNotificationEntity?,

        @Embedded
        @AttributeOverrides(
            AttributeOverride( name = "name", column = Column(name = "payer_name")),
            AttributeOverride( name = "email", column = Column(name = "payer_email"))
        )
        val payer: UserNotificationEntity?,

        @Column(name = "value", nullable = false)
        val value: Float?,

        @Column(name = "status", nullable = false)
        @Enumerated(EnumType.STRING)
        var status: NotificationStatus? = NotificationStatus.TO_NOTIFY,

        @Column(name = "created_at")
        val createdAt: Timestamp?,

        @Column(name = "notified_at")
        var notifiedAt: Timestamp?,

        @Column(name = "updated_at")
        var updatedAt: Timestamp?
) {

    data class Builder(
            var id: Int? = null,
            var transactionId: Int? = null,
            var payee: UserNotificationEntity? = null,
            var payer: UserNotificationEntity? = null,
            var value: Float? = null,
            var status: NotificationStatus? = null,
            var createdAt: Timestamp? = null,
            var notifiedAt: Timestamp? = null,
            var updatedAt: Timestamp? = null
    ) {
        fun id(id: Int?) = apply { this.id = id }
        fun transactionId(transactionId: Int?) = apply { this.transactionId = transactionId }
        fun payee(payee: UserNotificationEntity?) = apply { this.payee = payee }
        fun payer(payer: UserNotificationEntity?) = apply { this.payer = payer }
        fun value(value: Float?) = apply { this.value = value }
        fun status(status: NotificationStatus?) = apply { this.status = status }
        fun createdAt(createdAt: Timestamp?) = apply { this.createdAt = createdAt }
        fun notifiedAt(notifiedAt: Timestamp?) = apply { this.notifiedAt = notifiedAt }
        fun updatedAt(updatedAt: Timestamp?) = apply { this.updatedAt = updatedAt }
        fun build() = NotificationEntity(id, transactionId, payee, payer, value, status, createdAt, notifiedAt, updatedAt)
    }

}

@Embeddable
class UserNotificationEntity(
        @Column(name = "payee_name", nullable = false)
        val name: String?,
        @Column(name = "payee_email", nullable = false)
        val email: String?
)
