package br.com.will.payments.infrastructure.database.users

import br.com.will.payments.domain.entities.User
import br.com.will.payments.usecases.users.gateways.UsersGateway

//@Component
class UsersAdapter(private val userRepository: UserRepository): UsersGateway {

    override fun create(user: User): User {
        userRepository.save(UserEntity.Builder()
                .id(user.id)
                .name(user.name)
                .email(user.email)
                .cpfOrCnpj(user.cpfOrCnpj)
                .password(user.password)
                .build()
        ).let {
            return User.Builder()
                    .id(it.id)
                    .name(it.name)
                    .email(it.email)
                    .cpfOrCnpj(it.cpfCnpj)
                    .password(it.password)
                    .build()
        }
    }

    override fun existsByCpfOrCnpj(user: User): Boolean {
        return user.email?.let { userRepository.existsByEmail(it) } ?: false
    }

    override fun existsByEmail(user: User): Boolean {
        return user.cpfOrCnpj?.let { userRepository.existsByCpfCnpj(it) } ?: false
    }
}