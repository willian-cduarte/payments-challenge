package br.com.will.payments.usecases.payment.gateways

import br.com.will.payments.domain.entities.Payment

interface PaymentTransactionGateway {

    fun create(payment: Payment): Payment
}