package br.com.will.payments.usecases.account

import br.com.will.payments.domain.entities.AccountCash

interface AddBalanceUseCase {

    fun execute(accountCash: AccountCash): AccountCash
}