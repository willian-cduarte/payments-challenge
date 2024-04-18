package br.com.will.payments.infrastructure.web.payments

import br.com.will.payments.infrastructure.BaseIntegrationTest
import org.json.JSONObject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class PaymentsIntegrationTest : BaseIntegrationTest() {

    private val transactionsUrl = "/transactions"

    @Test
    @Sql("/sql/create_user.sql", "/sql/create_user_account.sql")
    fun `should execute payment transaction with success`() {

        val request = PaymentRequest(
                payee = 2,
                payer = 1,
                value = 50.00F
        )

        val jsonRequest = objectMapper.writeValueAsString(request)

        val response = mockMvc
                .perform(MockMvcRequestBuilders.post(transactionsUrl).content(jsonRequest).headers(defaultHeaders()))
                .andExpect(MockMvcResultMatchers.status().isOk)

        val jsonObject = JSONObject(response.andReturn().response.contentAsString)

        Assertions.assertEquals(jsonObject.get("payee"), request.payee)
        Assertions.assertEquals(jsonObject.get("payer"), request.payer)
        Assertions.assertEquals(jsonObject.get("value"), 50.0)
    }
}
