package br.com.will.payments.usecases.notifications.gateways

import br.com.will.payments.domain.entities.TransactionNotification

interface TransactionNotificationExecutorGateway {

    fun execute(notification: TransactionNotification): Boolean

}