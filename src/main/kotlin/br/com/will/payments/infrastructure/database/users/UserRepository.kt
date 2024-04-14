package br.com.will.payments.infrastructure.database.users

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface UserRepository: JpaRepository<UserEntity, Int> {

    fun existsByCpfCnpj(cpfCnpj: String): Boolean

    fun existsByEmail(email: String): Boolean
}