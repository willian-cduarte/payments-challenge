package br.com.will.payments.usecases.users.gateways

import br.com.will.payments.domain.entities.User

interface UsersGateway {

    fun create(user: User): User

    fun existsByCpfOrCnpj(user: User): Boolean

    fun existsByEmail(user: User): Boolean

}