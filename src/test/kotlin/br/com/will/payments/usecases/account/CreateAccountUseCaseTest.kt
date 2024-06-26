package br.com.will.payments.usecases.account

import br.com.will.payments.domain.entities.AccountCash
import br.com.will.payments.domain.exceptions.BusinessException
import br.com.will.payments.domain.exceptions.NotFoundException
import br.com.will.payments.usecases.BaseTest
import br.com.will.payments.usecases.account.gateways.AccountCashGateway
import br.com.will.payments.usecases.account.impl.CreateAccountUseCaseImpl
import br.com.will.payments.usecases.users.gateways.UsersGateway
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito

class CreateAccountUseCaseTest : BaseTest() {


    @Mock
    lateinit var usersGateway: UsersGateway

    @Mock
    lateinit var accountCashGateway: AccountCashGateway

    @InjectMocks
    lateinit var createAccountUseCase: CreateAccountUseCaseImpl

    private val account = AccountCash.Builder()
            .userId(1)
            .balance(0F)
            .build()

    @Test
    fun `should create account with success`() {
        // mockedSettings = mockStatic(LocalDateTime::class.java)

        Mockito.`when`(usersGateway.existsById(1)).thenReturn(true)
        Mockito.`when`(accountCashGateway.existsByUserId(1)).thenReturn(false)
        Mockito.`when`(accountCashGateway.createAccount(account)).thenReturn(account)

        createAccountUseCase.execute(1)
        // mockedSettings!!.close()

    }

    @Test
    fun `should return error with user has an account`() {

        Mockito.`when`(usersGateway.existsById(1)).thenReturn(true)
        Mockito.`when`(accountCashGateway.existsByUserId(1)).thenReturn(true)

        Assertions.assertThrows(BusinessException::class.java) {
            createAccountUseCase.execute(1)
        }
    }

    @Test
    fun `should return error with user is not exists`() {

        Mockito.`when`(usersGateway.existsById(1)).thenReturn(false)

        Assertions.assertThrows(NotFoundException::class.java) {
            createAccountUseCase.execute(1)
        }
    }
}