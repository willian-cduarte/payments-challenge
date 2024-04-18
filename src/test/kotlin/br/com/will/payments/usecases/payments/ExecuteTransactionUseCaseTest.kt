package br.com.will.payments.usecases.payments

import br.com.will.payments.domain.entities.AccountCash
import br.com.will.payments.domain.entities.NotificationStatus
import br.com.will.payments.domain.entities.Payment
import br.com.will.payments.domain.entities.TransactionNotification
import br.com.will.payments.domain.entities.User
import br.com.will.payments.domain.entities.UserNotification
import br.com.will.payments.domain.exceptions.BusinessException
import br.com.will.payments.usecases.account.gateways.AccountCashGateway
import br.com.will.payments.usecases.payment.gateways.NotifyTransactionGateway
import br.com.will.payments.usecases.payment.gateways.PaymentTransactionGateway
import br.com.will.payments.usecases.payment.gateways.TransactionUsersGateway
import br.com.will.payments.usecases.payment.gateways.ValidateTransactionAuthorizationGateway
import br.com.will.payments.usecases.payment.impl.ExecutePaymentTransactionUseCaseImpl
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockedStatic
import org.mockito.Mockito
import org.mockito.Mockito.doNothing
import org.mockito.MockitoSession
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.quality.Strictness
import org.springframework.test.context.event.annotation.AfterTestExecution
import org.springframework.test.context.event.annotation.BeforeTestExecution
import org.springframework.test.context.transaction.AfterTransaction
import org.springframework.test.context.transaction.BeforeTransaction
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class ExecuteTransactionUseCaseTest {

    @Mock
    lateinit var usersGateway: TransactionUsersGateway

    @Mock
    lateinit var paymentTransactionGateway: PaymentTransactionGateway

    @Mock
    lateinit var accountCashGateway: AccountCashGateway

    @Mock
    lateinit var validateTransactionAuthorizationGateway: ValidateTransactionAuthorizationGateway

    @Mock
    lateinit var notifyTransactionGateway: NotifyTransactionGateway

    @InjectMocks
    lateinit var executeTransactionUseCase: ExecutePaymentTransactionUseCaseImpl


    private var mockedSettings: MockedStatic<LocalDateTime>? = null

    @BeforeClass
    fun init() {
        mockedSettings = Mockito.mockStatic(LocalDateTime::class.java)
    }

    @AfterClass
    fun close() {
        mockedSettings!!.close()
    }

    private val payer = User.Builder()
            .id(1)
            .name("João dos Santos")
            .email("email@test.com")
            .cpfOrCnpj("001.234.567-89")
            .password("123abc")
            .build()

    private val payee = User.Builder()
            .id(2)
            .name("Jose dos Santos")
            .email("email2@test.com")
            .cpfOrCnpj("00.234.567/0001-89")
            .password("123abc")
            .build()

    private val payerAccount = AccountCash.Builder()
            .userId(1)
            .balance(100F)
            .build()

    private val payeeAccount = AccountCash.Builder()
            .userId(2)
            .balance(100F)
            .build()


    private val payment = Payment.Builder()
            .payer(payer.id)
            .payee(payee.id)
            .value(50.00F)
            .transactionId(1)
            .build()

    private val payeeNotification = UserNotification(payee.name, payee.email)
    private val payerNotification = UserNotification(payer.name, payer.email)

    val transaction = TransactionNotification.Builder()
            .transactionId(payment.transactionId)
            .payee(payeeNotification)
            .payer(payerNotification)
            .value(payment.value)
            .status(NotificationStatus.TO_NOTIFY)
            .createdAt(LocalDateTime.now())
            .build()


    @Test
    fun `should execute payment transaction with success`() {

        Mockito.`when`(usersGateway.findById(1)).thenReturn(payer)
        Mockito.`when`(usersGateway.findById(2)).thenReturn(payee)

        Mockito.`when`(accountCashGateway.getByUserId(1)).thenReturn(payerAccount)
        Mockito.`when`(accountCashGateway.getByUserId(2)).thenReturn(payeeAccount)

        Mockito.`when`(validateTransactionAuthorizationGateway.execute()).thenReturn("Autorizado")

        val updatedPayerAccount = payerAccount.copy(balance = 50F)
        val updatedPayeeAccount = payeeAccount.copy(balance = 150F)

        Mockito.`when`(accountCashGateway.updateBalance(updatedPayerAccount)).thenReturn(updatedPayerAccount)
        Mockito.`when`(accountCashGateway.updateBalance(updatedPayeeAccount)).thenReturn(updatedPayeeAccount)


        Mockito.`when`(paymentTransactionGateway.create(payment)).thenReturn(payment)


        executeTransactionUseCase.execute(payment)

    }


    @Test
    fun `should throw error when payer is merchant`() {

        val payment = Payment.Builder()
                .payer(payee.id)
                .payee(payer.id)
                .value(50.00F)
                .transactionId(1)
                .build()

        Mockito.`when`(usersGateway.findById(2)).thenReturn(payee)

        Assertions.assertThrows(BusinessException::class.java) {
            executeTransactionUseCase.execute(payment)
        }

    }

    @Test
    fun `should throw error when payer does not have funds`() {

        Mockito.`when`(usersGateway.findById(1)).thenReturn(payer)
        Mockito.`when`(usersGateway.findById(2)).thenReturn(payee)

        Mockito.`when`(accountCashGateway.getByUserId(1)).thenReturn(payerAccount.copy(balance = 10F))
        Mockito.`when`(accountCashGateway.getByUserId(2)).thenReturn(payeeAccount)


        Assertions.assertThrows(BusinessException::class.java) {
            executeTransactionUseCase.execute(payment)
        }

    }

    @Test
    fun `should throw error when transaction is not authorized`() {

        Mockito.`when`(usersGateway.findById(1)).thenReturn(payer)
        Mockito.`when`(usersGateway.findById(2)).thenReturn(payee)

        Mockito.`when`(accountCashGateway.getByUserId(1)).thenReturn(payerAccount)
        Mockito.`when`(accountCashGateway.getByUserId(2)).thenReturn(payeeAccount)

        Mockito.`when`(validateTransactionAuthorizationGateway.execute()).thenReturn("Não Autorizado")

        Assertions.assertThrows(BusinessException::class.java) {
            executeTransactionUseCase.execute(payment)
        }

    }

}