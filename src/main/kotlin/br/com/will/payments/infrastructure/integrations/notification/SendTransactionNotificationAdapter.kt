package br.com.will.payments.infrastructure.integrations.notification

import br.com.will.payments.domain.entities.TransactionNotification
import br.com.will.payments.usecases.notifications.gateways.TransactionNotificationExecutorGateway
import org.springframework.stereotype.Component

@Component
class SendTransactionNotificationAdapter(
        private val sendNotificationFeignClient: SendNotificationFeignClient
): TransactionNotificationExecutorGateway {

    override fun execute(notification: TransactionNotification): Boolean {
        sendNotificationFeignClient.sendNotification(NotificationRequest.Builder()
                .transactionId(notification.transactionId)
                .transactionDateTime(notification.createdAt)
                .value(notification.value)
                .payee(UserNotification(notification.payee?.name, notification.payee?.email))
                .payer(UserNotification(notification.payer?.name, notification.payer?.email))
                .build()
        ).let {
            return it.message
        }
    }
}