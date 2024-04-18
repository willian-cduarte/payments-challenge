package br.com.will.payments.infrastructure.web.users

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "UsersApi", description = "Users Api")
@RequestMapping("/users")
interface UsersApi {

    @Operation(
            summary = "Create User",
            description = "Create users")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "successful operation")
    ])
    @PostMapping
    fun createUser(@RequestBody userRequest: UserRequest): UserResponse

}