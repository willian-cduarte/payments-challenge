package br.com.will.payments.usecases.account.gateways

import br.com.will.payments.domain.entities.AccountCash
import java.util.Optional

interface AccountCashGateway {

    fun existsByUserId(userId: Int?): Boolean

    fun getByUserId(userId: Int?): AccountCash

    fun createAccount(accountCash: AccountCash): AccountCash

    fun updateBalance(accountCash: AccountCash): AccountCash
}