package br.com.will.payments.usecases.users

import br.com.will.payments.domain.entities.User

interface AddUsersUseCase {

    fun execute(user: User): User;
}
