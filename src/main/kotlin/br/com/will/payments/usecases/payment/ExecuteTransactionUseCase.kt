package br.com.will.payments.usecases.payment

import br.com.will.payments.domain.entities.Payment

interface ExecuteTransactionUseCase {

    fun execute(payment: Payment): Payment

}