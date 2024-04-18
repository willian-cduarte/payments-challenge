package br.com.will.payments.infrastructure.web.account

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/account")
interface AccountCashApi {

    @PostMapping("")
    fun createAccount(@RequestBody userAccount: CreateAccount): AccountResponse

    @PutMapping("/balance")
    fun addBalance(@RequestBody account: AccountRequest): AccountResponse
}