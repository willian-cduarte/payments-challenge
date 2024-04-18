package br.com.will.payments.infrastructure.database.notifications

import br.com.will.payments.domain.entities.NotificationStatus
import br.com.will.payments.domain.entities.TransactionNotification
import br.com.will.payments.domain.entities.UserNotification
import br.com.will.payments.usecases.notifications.gateways.NotificationsGateway
import br.com.will.payments.usecases.payment.gateways.NotifyTransactionGateway
import org.springframework.stereotype.Component
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneId

@Component
class NotificationsAdapter(private val notificationsRepository: NotificationsRepository) : NotifyTransactionGateway, NotificationsGateway {

    override fun createNotification(notification: TransactionNotification) {
        notificationsRepository.save(NotificationEntity.Builder()
                .transactionId(notification.transactionId)
                .payee(UserNotificationEntity(notification.payee?.name, notification.payee?.email))
                .payer(UserNotificationEntity(notification.payer?.name, notification.payer?.email))
                .value(notification.value)
                .status(notification.status)
                .createdAt(Timestamp.valueOf(notification.createdAt))
                .build()
        )
    }

    override fun updateNotificationStatus(notification: TransactionNotification): TransactionNotification {
        return notificationsRepository.getReferenceById(notification.id!!)
                .let { entity ->
                    entity.status = notification.status
                    entity.notifiedAt = Timestamp.valueOf(notification.notifiedAt)
                    entity.updatedAt = Timestamp.valueOf(notification.updatedAt)
                    notificationsRepository.save(entity).let {
                        TransactionNotification.Builder()
                                .id(it.id)
                                .transactionId(it.transactionId)
                                .payee(UserNotification(it.payee?.name, it.payee?.email))
                                .payer(UserNotification(it.payer?.name, it.payer?.email))
                                .value(it.value)
                                .status(it.status)
                                .createdAt(it.createdAt?.toLocalDateTime())
                                .notifiedAt(it.notifiedAt?.toLocalDateTime())
                                .updatedAt(it.updatedAt?.toLocalDateTime())
                                .build()

                    }
                }
    }

    override fun findAllByStatus(status: NotificationStatus): List<TransactionNotification> {
        return notificationsRepository.findAllByStatus(NotificationStatus.valueOf(status.name))
                .let { list ->
                    list.map {
                        TransactionNotification.Builder()
                                .id(it.id)
                                .transactionId(it.transactionId)
                                .payee(UserNotification(it.payee?.name, it.payee?.email))
                                .payer(UserNotification(it.payer?.name, it.payer?.email))
                                .value(it.value)
                                .status(it.status)
                                .createdAt(it.createdAt?.toLocalDateTime())
                                .notifiedAt(it.notifiedAt?.toLocalDateTime())
                                .updatedAt(it.updatedAt?.toLocalDateTime())
                                .build()
                    }
                }
    }

}