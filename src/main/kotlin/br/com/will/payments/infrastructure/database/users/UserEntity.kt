package br.com.will.payments.infrastructure.database.users

import br.com.will.payments.domain.entities.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class UserEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
        val id: Int?,
        @Column(name = "name")
        val name: String?,
        @Column(name = "cpfCnpj", unique=true, nullable = false)
        val cpfCnpj: String?,
        @Column(name = "email", unique=true, nullable = false)
        val email: String?,
        @Column(name = "password")
        val password: String?
) {
        data class Builder(
                var id: Int? = null,
                var name: String? = null,
                var cpfCnpj: String? = null,
                var email: String? = null,
                var password: String? = null
        ) {
                fun id(id: Int?) = apply { this.id = id }
                fun name(name: String?) = apply { this.name = name }
                fun cpfOrCnpj(cpfOrCnpj: String?) = apply { this.cpfCnpj = cpfOrCnpj }
                fun email(email: String?) = apply { this.email = email }
                fun password(password: String?) = apply { this.password = password }
                fun build() = UserEntity(id, name, cpfCnpj, email, password)
        }
}
