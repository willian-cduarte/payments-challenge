package br.com.will.payments.usecases.account

import br.com.will.payments.domain.entities.AccountCash
import br.com.will.payments.domain.exceptions.BusinessException
import br.com.will.payments.usecases.account.gateways.AccountCashGateway
import br.com.will.payments.usecases.account.impl.CreateAccountUseCaseImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockedStatic
import org.mockito.Mockito
import org.mockito.Mockito.mockStatic
import org.mockito.junit.jupiter.MockitoExtension
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import java.time.LocalDateTime


@ExtendWith(MockitoExtension::class)
class CreateAccountUseCaseTest {



        @Mock
        lateinit var accountCashGateway: AccountCashGateway

        @InjectMocks
        lateinit var createAccountUseCase: CreateAccountUseCaseImpl

        private var mockedSettings: MockedStatic<LocalDateTime>? = null

        @BeforeClass
        fun init() {
                mockedSettings = mockStatic(LocalDateTime::class.java)
        }

        @AfterClass
        fun close() {
                mockedSettings!!.close()
        }

        private val account = AccountCash.Builder()
                .userId(1)
                .balance(0F)
                .build()

        @Test
        fun `should create account with success`() {


                Mockito.`when`(accountCashGateway.existsByUserId(1)).thenReturn(false)
                Mockito.`when`(accountCashGateway.createAccount(account)).thenReturn(account)

                createAccountUseCase.execute(1)


        }

        @Test
        fun `should return error with user has an account`() {

                Mockito.`when`(accountCashGateway.existsByUserId(1)).thenReturn(true)

                Assertions.assertThrows(BusinessException::class.java) {
                        createAccountUseCase.execute(1)
                }
        }
}