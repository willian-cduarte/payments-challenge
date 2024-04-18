package br.com.will.payments.infrastructure.web.payments

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.hibernate.Transaction
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Tag(name = "PaymentsApi", description = "Payments Api")
@RequestMapping("/transactions")
interface PaymentsApi {

    @Operation(
            summary = "Pay",
            description = "Create payment transactions")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "successful operation")
    ])
    @PostMapping
    fun pay(@RequestBody payment: PaymentRequest): PaymentResponse


}