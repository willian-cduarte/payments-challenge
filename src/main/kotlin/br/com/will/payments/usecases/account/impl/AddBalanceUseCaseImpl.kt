package br.com.will.payments.usecases.account.impl

import br.com.will.payments.domain.entities.AccountCash
import br.com.will.payments.usecases.account.AddBalanceUseCase
import br.com.will.payments.usecases.account.gateways.AccountCashGateway
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class AddBalanceUseCaseImpl(private val accountCashGateway: AccountCashGateway): AddBalanceUseCase {
    override fun execute(accountCash: AccountCash): AccountCash {
        val account = accountCashGateway.getByUserId(accountCash.userId)
        return accountCashGateway.updateBalance(
                account.copy(
                        balance = account.balance?.plus(accountCash.balance!!),
                        updatedAt = LocalDateTime.now()
                )
        )
    }

}