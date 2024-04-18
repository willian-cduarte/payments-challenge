package br.com.will.payments.infrastructure.integrations.authorization

import br.com.will.payments.infrastructure.integrations.authorization.AuthorizationResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(
        value = "transactionAuthorization",
        url = "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc"
)
interface TransactionAuthorizationFeignClient {

    @GetMapping
    fun validateAuthorization(): AuthorizationResponse

}