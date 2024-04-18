package br.com.will.payments.infrastructure.web.payments

import org.hibernate.Transaction
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/transactions")
interface PaymentsApi {

    @PostMapping
    fun pay(@RequestBody payment: PaymentRequest): PaymentResponse


}