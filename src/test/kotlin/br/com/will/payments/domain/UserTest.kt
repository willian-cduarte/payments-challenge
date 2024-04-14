package br.com.will.payments.domain

import br.com.will.payments.domain.entities.User
import br.com.will.payments.domain.exceptions.BusinessException
import br.com.will.payments.domain.exceptions.ValidationException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class UserTest {

    @Test
    fun `should returns cpfOrCnpj without mask`() {

        val user = User.Builder()
                .cpfOrCnpj("123.456.789-00")
                .build()

        val cpfOrCnpjWithoutMask = "12345678900"

        Assertions.assertEquals(cpfOrCnpjWithoutMask, user.cpfOrCnpj!!)
    }

    @Test
    fun `should validate cpf with success`() {
        val user = User.Builder()
                .cpfOrCnpj("123.456.789-00")
                .build()

        Assertions.assertDoesNotThrow { user.isValidCpfOrCnpj() }

    }

    @Test
    fun `should validate cnpj with success`() {
        val user = User.Builder()
                .cpfOrCnpj("13.456.789/0001-00")
                .build()

        Assertions.assertDoesNotThrow { user.isValidCpfOrCnpj() }

    }

    @Test
    fun `should throw error when verify if cpfOrCnpj is valid`() {
        val user = User.Builder()
                .cpfOrCnpj("123.456.789-00/1")
                .build()

        Assertions.assertThrows(ValidationException::class.java) { user.isValidCpfOrCnpj() }

    }

    @Test
    fun `should validate email with success`() {
        val user = User.Builder()
                .email("email@google.com")
                .build()

        Assertions.assertDoesNotThrow { user.isValidEmail() }

    }

    @Test
    fun `should throw error when verify if email is valid`() {
        val user = User.Builder()
                .email("email@test")
                .build()

        Assertions.assertThrows(ValidationException::class.java) { user.isValidEmail() }

    }



}