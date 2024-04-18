package br.com.will.payments.usecases.payment.gateways

import br.com.will.payments.domain.entities.TransactionNotification

interface NotifyTransactionGateway {

    fun createNotification(notification: TransactionNotification)

}