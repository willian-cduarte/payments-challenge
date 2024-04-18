package br.com.will.payments.usecases.payment.impl

import br.com.will.payments.domain.entities.NotificationStatus
import br.com.will.payments.domain.entities.Payment
import br.com.will.payments.domain.entities.TransactionNotification
import br.com.will.payments.domain.entities.UserNotification
import br.com.will.payments.domain.exceptions.BusinessException
import br.com.will.payments.domain.exceptions.config.ErrorCodeConstants
import br.com.will.payments.usecases.account.gateways.AccountCashGateway
import br.com.will.payments.usecases.payment.ExecuteTransactionUseCase
import br.com.will.payments.usecases.payment.gateways.NotifyTransactionGateway
import br.com.will.payments.usecases.payment.gateways.PaymentTransactionGateway
import br.com.will.payments.usecases.payment.gateways.TransactionUsersGateway
import br.com.will.payments.usecases.payment.gateways.ValidateTransactionAuthorizationGateway
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ExecutePaymentTransactionUseCaseImpl(
        private val usersGateway: TransactionUsersGateway,
        private val paymentTransactionGateway: PaymentTransactionGateway,
        private val accountCashGateway: AccountCashGateway,
        private val validateTransactionAuthorizationGateway: ValidateTransactionAuthorizationGateway,
        private val notifyTransactionGateway: NotifyTransactionGateway
) : ExecuteTransactionUseCase {

    @Transactional
    override fun execute(payment: Payment): Payment {

        val payer = usersGateway.findById(payment.payer)
        if (payer.isMerchant())
            throw BusinessException(ErrorCodeConstants.NOT_ACCEPTABLE, null, "Tradesman cannot make payments")

        val payee = usersGateway.findById(payment.payee)

        val payerAccount = accountCashGateway.getByUserId(payment.payer)
        val payeeAccount = accountCashGateway.getByUserId(payment.payee)

        if (payerAccount.balance!! < payment.value!!)
            throw BusinessException(ErrorCodeConstants.NOT_FOUND, null, "Insufficient founds")

        validateTransactionAuthorizationGateway.execute().let {
            if (it != "Autorizado")
                throw BusinessException(ErrorCodeConstants.NOT_ACCEPTABLE, null, "Payment not authorized")

            val transactionDateTime = LocalDateTime.now()


            accountCashGateway.updateBalance(payerAccount.copy(
                    balance = payerAccount.balance.minus(payment.value),
                    updatedAt = transactionDateTime
            ))

            accountCashGateway.updateBalance(payeeAccount.copy(
                    balance = payerAccount.balance.plus(payment.value),
                    updatedAt = transactionDateTime
            ))

            val executedPayment = paymentTransactionGateway.create(payment.copy(createdAt = transactionDateTime))

            notifyTransactionGateway.createNotification(TransactionNotification.Builder()
                    .transactionId(executedPayment.transactionId)
                    .payee(UserNotification(payee.name, payee.email))
                    .payer(UserNotification(payer.name, payer.email))
                    .value(executedPayment.value)
                    .status(NotificationStatus.TO_NOTIFY)
                    .createdAt(transactionDateTime)
                    .build()
            )

            return executedPayment
        }
    }
}