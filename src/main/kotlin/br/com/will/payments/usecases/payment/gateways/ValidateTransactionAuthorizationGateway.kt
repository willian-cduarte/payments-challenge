package br.com.will.payments.usecases.payment.gateways

interface ValidateTransactionAuthorizationGateway {

    fun execute(): String
}