package br.com.will.payments.infrastructure.database.accounts

import br.com.will.payments.infrastructure.database.payments.PaymentEntity
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface AccountCashRepository: CrudRepository<AccountCashEntity, Int> {

    fun existsByUserId(userId: Int?): Boolean

    fun findByUserId(userId: Int?): Optional<AccountCashEntity>
}