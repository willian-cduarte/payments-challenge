package br.com.will.payments.infrastructure.web.users

import br.com.will.payments.infrastructure.BaseIntegrationTest
import org.json.JSONObject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
class UsersIntegrationTest : BaseIntegrationTest() {

    private val usersUrl = "/users"

    @Test
    @Sql("/sql/delete_users.sql")
    fun `should create user with success`() {

        val request = UserRequest(
                name = "Teste",
                email = "teste@teste.com",
                cpfOrCnpj = "000.123.456-78",
                password = "abc123"
        )

        val jsonRequest = objectMapper.writeValueAsString(request)

        val response = mockMvc
                .perform(MockMvcRequestBuilders.post(usersUrl).content(jsonRequest).headers(defaultHeaders()))
                .andExpect(MockMvcResultMatchers.status().isOk)

        val jsonObject = JSONObject(response.andReturn().response.contentAsString)

        Assertions.assertEquals(jsonObject.get("name"), request.name)
        Assertions.assertEquals(jsonObject.get("email"), request.email)
        Assertions.assertEquals(jsonObject.get("cpfOrCnpj"), request.cpfOrCnpj
                ?.replace(".", "")
                ?.replace("-", "")
        )
        Assertions.assertEquals(jsonObject.get("email"), request.email)

    }
}