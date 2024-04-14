package br.com.will.payments.infrastructure.web.users.controllers

import br.com.will.payments.domain.entities.User
import br.com.will.payments.infrastructure.web.users.UsersApi
import br.com.will.payments.infrastructure.web.users.UserRequest
import br.com.will.payments.infrastructure.web.users.UserResponse
import br.com.will.payments.usecases.users.AddUsersUseCase
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController(
        private val addUsersUseCase: AddUsersUseCase
) : UsersApi {

    override fun createUser(userRequest: UserRequest): UserResponse {
        addUsersUseCase.execute(
                User.Builder()
                        .name(userRequest.name)
                        .cpfOrCnpj(userRequest.cpfOrCnpj)
                        .email(userRequest.email)
                        .password(userRequest.password)
                        .build()
        ).let {
            return UserResponse.Builder()
                    .id(it.id)
                    .name(it.name)
                    .email(it.email)
                    .cpfOrCnpj(it.cpfOrCnpj)
                    .build()
        }
    }
}