package br.com.will.payments.usecases.account

import br.com.will.payments.domain.entities.AccountCash
import br.com.will.payments.domain.exceptions.NotFoundException
import br.com.will.payments.domain.exceptions.config.ErrorCodeConstants
import br.com.will.payments.usecases.BaseTest
import br.com.will.payments.usecases.account.gateways.AccountCashGateway
import br.com.will.payments.usecases.account.impl.AddBalanceUseCaseImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import java.time.LocalDateTime


class AddBalanceUseCaseTest : BaseTest() {

    @Mock
    lateinit var accountCashGateway: AccountCashGateway

    @InjectMocks
    lateinit var addBalanceUseCase: AddBalanceUseCaseImpl

    private val account = AccountCash.Builder()
            .id(1)
            .userId(1)
            .balance(0F)
            .createdAt(LocalDateTime.now())
            .build()

    @Test
    fun `should add balance with success`() {

        val updateAccount = account.copy(balance = 100.0F, updatedAt = LocalDateTime.now())

        given(accountCashGateway.getByUserId(1)).willReturn(account)

        given(accountCashGateway.updateBalance(updateAccount)).willReturn(updateAccount)

        val accountUpdated = addBalanceUseCase.execute(updateAccount)

        Assertions.assertEquals(updateAccount, accountUpdated)

    }

    @Test
    fun `should return error with user not be found`() {

        Mockito.`when`(accountCashGateway.getByUserId(1))
                .thenThrow(NotFoundException(ErrorCodeConstants.USER_NOT_HAVE_ACCOUNT))

        Assertions.assertThrows(NotFoundException::class.java) {
            addBalanceUseCase.execute(account.copy(balance = 100.0F, updatedAt = LocalDateTime.now()))
        }
    }
}