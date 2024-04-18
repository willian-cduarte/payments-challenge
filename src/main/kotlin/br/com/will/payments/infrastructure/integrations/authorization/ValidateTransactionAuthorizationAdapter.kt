package br.com.will.payments.infrastructure.integrations.authorization

import br.com.will.payments.infrastructure.integrations.authorization.TransactionAuthorizationFeignClient
import br.com.will.payments.usecases.payment.gateways.ValidateTransactionAuthorizationGateway
import org.springframework.stereotype.Component

@Component
class ValidateTransactionAuthorizationAdapter(
        val transactionAuthorizationFeignClient: TransactionAuthorizationFeignClient
): ValidateTransactionAuthorizationGateway {

    override fun execute(): String {
        return transactionAuthorizationFeignClient.validateAuthorization().message
    }
}