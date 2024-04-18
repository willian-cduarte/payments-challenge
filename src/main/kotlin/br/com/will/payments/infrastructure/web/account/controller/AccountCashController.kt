package br.com.will.payments.infrastructure.web.account.controller

import br.com.will.payments.domain.entities.AccountCash
import br.com.will.payments.infrastructure.web.account.AccountCashApi
import br.com.will.payments.infrastructure.web.account.AccountRequest
import br.com.will.payments.infrastructure.web.account.AccountResponse
import br.com.will.payments.infrastructure.web.account.CreateAccount
import br.com.will.payments.usecases.account.AddBalanceUseCase
import br.com.will.payments.usecases.account.CreateAccountUseCase
import org.springframework.web.bind.annotation.RestController


@RestController
class AccountCashController(
        val createAccountUseCase: CreateAccountUseCase,
        val addBalanceUseCase: AddBalanceUseCase
): AccountCashApi {
    override fun createAccount(userAccount: CreateAccount): AccountResponse {
        return createAccountUseCase.execute(userAccount.userId)
                .let {
                    AccountResponse.Builder()
                            .userId(it.userId)
                            .balance(it.balance)
                            .createdAt(it.createdAt)
                            .build()
                }
    }


    override fun addBalance(account: AccountRequest): AccountResponse {
        return addBalanceUseCase.execute(AccountCash.Builder()
                .userId(account.userId)
                .balance(account.addCash)
                .build()
        ).let {
            AccountResponse.Builder()
                    .userId(it.userId)
                    .balance(it.balance)
                    .createdAt(it.createdAt)
                    .updatedAt(it.updatedAt)
                    .build()
        }
    }
}