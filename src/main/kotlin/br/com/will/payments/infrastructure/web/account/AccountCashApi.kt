package br.com.will.payments.infrastructure.web.account

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Tag(name = "AccountCashApi", description = "Account Cash Api")
@RequestMapping("/account")
interface AccountCashApi {

    @Operation(
            summary = "Create Account cash",
            description = "Create Account cash")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "successful operation")
    ])
    @PostMapping("")
    fun createAccount(@RequestBody userAccount: CreateAccount): AccountResponse

    @Operation(
            summary = "Add Balance",
            description = "Add balance into account cash")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "successful operation")
    ])
    @PutMapping("/balance")
    fun addBalance(@RequestBody account: AccountRequest): AccountResponse
}