package br.com.will.payments.infrastructure.web.users

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
interface UsersApi {

    @PostMapping
    fun createUser(@RequestBody userRequest: UserRequest): UserResponse

}