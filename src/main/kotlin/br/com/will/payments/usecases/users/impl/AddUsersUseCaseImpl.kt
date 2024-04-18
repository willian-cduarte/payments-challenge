package br.com.will.payments.usecases.users.impl

import br.com.will.payments.domain.entities.User
import br.com.will.payments.domain.exceptions.ValidationException
import br.com.will.payments.domain.exceptions.config.ErrorCodeConstants
import br.com.will.payments.usecases.users.gateways.UsersGateway
import br.com.will.payments.usecases.users.AddUsersUseCase
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component

@Component
class AddUsersUseCaseImpl(private val usersGateway: UsersGateway) : AddUsersUseCase {

    @Transactional
    override fun execute(user: User): User {
        user.isValidCpfOrCnpj()
        user.isValidEmail()


        usersGateway.existsByCpfOrCnpj(user).let { existsByCpfOrCnpj ->
            if (existsByCpfOrCnpj)
                throw ValidationException("user", "cpfOrCnpj", ErrorCodeConstants.DOCUMENT_ALREADY_EXISTS)
        }

        usersGateway.existsByEmail(user).let { existsByEmail ->
            if (existsByEmail)
                throw ValidationException("user", "email", ErrorCodeConstants.EMAIL_ALREADY_EXISTS)
        }


        return usersGateway.create(user)
    }

}