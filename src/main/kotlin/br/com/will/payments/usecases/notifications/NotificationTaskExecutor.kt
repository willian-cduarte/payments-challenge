package br.com.will.payments.usecases.notifications

import br.com.will.payments.domain.entities.NotificationStatus
import br.com.will.payments.usecases.notifications.gateways.NotificationsGateway
import br.com.will.payments.usecases.notifications.gateways.TransactionNotificationExecutorGateway
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class NotificationTaskExecutor(
        private val notificationsGateway: NotificationsGateway,
        private val transactionNotificationExecutorGateway: TransactionNotificationExecutorGateway
) {
    private val loggger = LoggerFactory.getLogger(NotificationTaskExecutor::class.java)

    @Scheduled(fixedDelay = 10000)
    fun run() {
        loggger.info("find notifications....")
        notificationsGateway
                .findAllByStatus(NotificationStatus.TO_NOTIFY)
                .map { notification ->
                    loggger.info("send notification....")

                    transactionNotificationExecutorGateway
                            .execute(notification)
                            .let { hasBeenNotified ->
                                if (hasBeenNotified)
                                    notificationsGateway.updateNotificationStatus(notification.copy(
                                            status = NotificationStatus.NOTIFIED,
                                            notifiedAt = LocalDateTime.now(),
                                            updatedAt = LocalDateTime.now()
                                    ))
                            }
                }
        loggger.info("notifications completed")


    }
}