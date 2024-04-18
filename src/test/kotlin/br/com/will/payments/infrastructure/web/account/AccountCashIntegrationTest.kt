package br.com.will.payments.infrastructure.web.account

import br.com.will.payments.infrastructure.BaseIntegrationTest
import org.json.JSONObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
class AccountCashIntegrationTest : BaseIntegrationTest() {

    private val accountUrl = "/account"


    @Test
    @Sql("/sql/create_user.sql")
    fun `should create account with success`() {

        val jsonAccount = objectMapper.writeValueAsString(CreateAccount(userId = 1))

        val response = mockMvc
                .perform(post(accountUrl).content(jsonAccount).headers(defaultHeaders()))
                .andExpect(MockMvcResultMatchers.status().isOk)

        val jsonObject = JSONObject(response.andReturn().response.contentAsString)

        assertEquals(jsonObject.get("userId"), 1)
        assertEquals(jsonObject.get("balance"), 0.0)

    }

    @Test
    @Sql("/sql/delete_users.sql")
    fun `should throw error when user is not exists`() {

        val jsonAccount = objectMapper.writeValueAsString(CreateAccount(userId = 1))

        mockMvc.perform(
                post(accountUrl)
                        .content(jsonAccount)
                        .headers(defaultHeaders())
        )
                .andExpect(MockMvcResultMatchers.status().isNotFound)

    }


    @Test
    @Sql("/sql/create_user_account.sql")
    fun `should throw error when user already has an account`() {

        val jsonAccount = objectMapper.writeValueAsString(CreateAccount(userId = 1))

        mockMvc.perform(
                post(accountUrl)
                        .content(jsonAccount)
                        .headers(defaultHeaders())
        )
                .andExpect(MockMvcResultMatchers.status().isBadRequest)

    }

    /*
    @PutMapping("/balance")
    fun addBalance(@RequestBody account: AccountRequest): AccountResponse
*/
}