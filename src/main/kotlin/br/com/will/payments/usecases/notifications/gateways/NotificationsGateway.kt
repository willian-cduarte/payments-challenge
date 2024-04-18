package br.com.will.payments.usecases.notifications.gateways

import br.com.will.payments.domain.entities.NotificationStatus
import br.com.will.payments.domain.entities.TransactionNotification

interface NotificationsGateway {

    fun updateNotificationStatus(notification: TransactionNotification): TransactionNotification

    fun findAllByStatus(status: NotificationStatus): List<TransactionNotification>
}