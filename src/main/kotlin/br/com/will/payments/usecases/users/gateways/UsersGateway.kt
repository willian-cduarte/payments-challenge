package br.com.will.payments.usecases.users.gateways

import br.com.will.payments.domain.entities.User

interface UsersGateway {

    fun create(user: User): User

    fun findById(id: Int?): User

    fun existsById(userId: Int): Boolean

    fun existsByCpfOrCnpj(user: User): Boolean

    fun existsByEmail(user: User): Boolean


}