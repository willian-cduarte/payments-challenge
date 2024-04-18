package br.com.will.payments.infrastructure.database.payments

import br.com.will.payments.domain.entities.Payment
import br.com.will.payments.usecases.payment.gateways.PaymentTransactionGateway

class PaymentsAdapter(private val paymentsRepository: PaymentsRepository): PaymentTransactionGateway {
    override fun create(payment: Payment): Payment {
        paymentsRepository.save(PaymentEntity.Builder()
                .payee(payment.payee)
                .payer(payment.payer)
                .value(payment.value)
                .createdAt(payment.createdAt)
                .build()
        ).let {
            return Payment.Builder()
                    .transactionId(it.transactionId)
                    .payee(it.payee)
                    .payer(it.payer)
                    .value(it.value)
                    .createdAt(it.createdAt?.toLocalDateTime())
                    .build()
        }
    }
}