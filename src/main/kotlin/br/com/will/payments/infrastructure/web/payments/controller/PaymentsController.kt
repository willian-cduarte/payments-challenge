package br.com.will.payments.infrastructure.web.payments.controller

import br.com.will.payments.domain.entities.Payment
import br.com.will.payments.infrastructure.web.payments.PaymentRequest
import br.com.will.payments.infrastructure.web.payments.PaymentResponse
import br.com.will.payments.infrastructure.web.payments.PaymentsApi
import br.com.will.payments.usecases.payment.ExecuteTransactionUseCase
import org.springframework.web.bind.annotation.RestController

@RestController
class PaymentsController(private val executeTransactionUseCase: ExecuteTransactionUseCase): PaymentsApi {
    override fun pay(payment: PaymentRequest): PaymentResponse {
        return executeTransactionUseCase.execute(
                Payment.Builder()
                        .payee(payment.payee)
                        .payer(payment.payer)
                        .value(payment.value)
                        .build()
        ).let {
            PaymentResponse.Builder()
                    .transactionId(it.transactionId)
                    .payee(it.payee)
                    .payer(it.payer)
                    .value(it.value)
                    .createdAt(it.createdAt)
                    .build()
        }
    }
}