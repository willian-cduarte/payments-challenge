package br.com.will.payments.usecases.account.impl

import br.com.will.payments.domain.entities.AccountCash
import br.com.will.payments.domain.exceptions.BusinessException
import br.com.will.payments.domain.exceptions.config.ErrorCodeConstants
import br.com.will.payments.usecases.account.CreateAccountUseCase
import br.com.will.payments.usecases.account.gateways.AccountCashGateway
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class CreateAccountUseCaseImpl(private val accountCashGateway: AccountCashGateway): CreateAccountUseCase {
    override fun execute(userId: Int?): AccountCash {

        if (accountCashGateway.existsByUserId(userId))
            throw BusinessException(ErrorCodeConstants.USER_ALREADY_HAS_ACCOUNT)

        return accountCashGateway.createAccount(
                AccountCash.Builder()
                        .userId(userId)
                        .createdAt(LocalDateTime.now())
                        .build()
        )

    }
}