package br.com.will.payments.usecases.account

import br.com.will.payments.domain.entities.AccountCash

interface CreateAccountUseCase {

    fun execute(userId: Int?): AccountCash
}