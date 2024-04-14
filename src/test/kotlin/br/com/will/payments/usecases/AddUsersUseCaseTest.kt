package br.com.will.payments.usecases

import br.com.will.payments.domain.entities.User
import br.com.will.payments.domain.exceptions.ValidationException
import br.com.will.payments.usecases.users.AddUsersUseCase
import br.com.will.payments.usecases.users.gateways.UsersGateway
import br.com.will.payments.usecases.users.impl.AddUsersUseCaseImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class AddUsersUseCaseTest {

    @Mock
    lateinit var usersGateway: UsersGateway

    @InjectMocks
    lateinit var addUsersUseCase: AddUsersUseCaseImpl

    private val user = User.Builder()
            .name("João dos Santos")
            .email("email@test.com")
            .cpfOrCnpj("001.234.567-89")
            .password("123abc")
            .build()

    @Test
    fun `should throw error when verify if exists by email`() {

       Mockito.`when`(usersGateway.existsByEmail(user)).thenReturn(true)

        Assertions.assertThrows(ValidationException::class.java) {
            addUsersUseCase.execute(user)
        }
    }

    @Test
    fun `should returns success when verify if exists by email`() {

        Mockito.`when`(usersGateway.existsByEmail(user)).thenReturn(false)

        addUsersUseCase.execute(user)

    }

    @Test
    fun `should throw error when verify if exists by cpf or cnpj`() {

        Mockito.`when`(usersGateway.existsByEmail(user)).thenReturn(false)
        Mockito.`when`(usersGateway.existsByCpfOrCnpj(user)).thenReturn(true)

        Assertions.assertThrows(ValidationException::class.java) {
            addUsersUseCase.execute(user)
        }
    }

    @Test
    fun `should returns success when verify if exists by cpf or cnpj`() {

        Mockito.`when`(usersGateway.existsByEmail(user)).thenReturn(false)
        Mockito.`when`(usersGateway.existsByCpfOrCnpj(user)).thenReturn(false)

        addUsersUseCase.execute(user)

    }

    @Test
    fun `should persist user with success`() {

        Mockito.`when`(usersGateway.existsByEmail(user)).thenReturn(false)
        Mockito.`when`(usersGateway.existsByCpfOrCnpj(user)).thenReturn(false)
        Mockito.`when`(usersGateway.create(user)).thenReturn(user.copy(id = 1))

        val savedUser = addUsersUseCase.execute(user)

        Assertions.assertEquals(1, savedUser.id)
        Assertions.assertEquals(user.name, savedUser.name)
        Assertions.assertEquals(user.name, savedUser.name)
        Assertions.assertEquals(user.name, savedUser.name)
        Assertions.assertEquals(user.name, savedUser.name)

    }
}