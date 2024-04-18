package br.com.will.payments.infrastructure.database.accounts

import br.com.will.payments.domain.entities.AccountCash
import br.com.will.payments.domain.exceptions.NotFoundException
import br.com.will.payments.domain.exceptions.config.ErrorCodeConstants
import br.com.will.payments.usecases.account.gateways.AccountCashGateway
import java.sql.Timestamp

class AccountCashAdapter(private val accountCashRepository: AccountCashRepository) : AccountCashGateway {

    override fun existsByUserId(userId: Int?): Boolean {
        return accountCashRepository.existsByUserId(userId)
    }

    override fun getByUserId(userId: Int?): AccountCash {
        return accountCashRepository.findByUserId(userId).orElseThrow {
            NotFoundException(ErrorCodeConstants.USER_NOT_HAVE_ACCOUNT)
        }.let {
            AccountCash.Builder()
                    .id(it.id)
                    .userId(it.userId)
                    .balance(it.balance)
                    .createdAt(it.createdAt?.toLocalDateTime())
                    .updatedAt(it.updatedAt?.toLocalDateTime())
                    .build()
        }
    }

    override fun createAccount(accountCash: AccountCash): AccountCash {
        return accountCashRepository.save(AccountCashEntity.Builder()
                .userId(accountCash.userId)
                .balance(accountCash.balance)
                .createdAt(Timestamp.valueOf(accountCash.createdAt))
                .build()
        ).let {
            AccountCash.Builder()
                    .id(it.id)
                    .userId(it.userId)
                    .balance(it.balance)
                    .createdAt(it.createdAt?.toLocalDateTime())
                    .updatedAt(it.updatedAt?.toLocalDateTime())
                    .build()
        }
    }

    override fun updateBalance(accountCash: AccountCash): AccountCash {
        return accountCashRepository.save(AccountCashEntity.Builder()
                .id(accountCash.id)
                .userId(accountCash.userId)
                .balance(accountCash.balance)
                .createdAt(Timestamp.valueOf(accountCash.createdAt))
                .updatedAt(Timestamp.valueOf(accountCash.updatedAt))
                .build()
        ).let {
            AccountCash.Builder()
                    .id(it.id)
                    .userId(it.userId)
                    .balance(it.balance)
                    .createdAt(it.createdAt?.toLocalDateTime())
                    .updatedAt(it.updatedAt?.toLocalDateTime())
                    .build()
        }
    }

}