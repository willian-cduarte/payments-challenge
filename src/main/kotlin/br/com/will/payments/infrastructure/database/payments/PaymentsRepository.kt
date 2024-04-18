package br.com.will.payments.infrastructure.database.payments

import br.com.will.payments.domain.entities.Payment
import org.springframework.data.repository.CrudRepository

interface PaymentsRepository: CrudRepository<PaymentEntity, Int> {
}