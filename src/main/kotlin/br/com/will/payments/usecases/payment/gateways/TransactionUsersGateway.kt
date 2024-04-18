package br.com.will.payments.usecases.payment.gateways

import br.com.will.payments.domain.entities.User

interface TransactionUsersGateway {

    fun findById(id: Int?): User
}