package br.com.will.payments.infrastructure

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
abstract class BaseIntegrationTest {

    @Autowired
    private lateinit var context: WebApplicationContext

    lateinit var mockMvc: MockMvc

    lateinit var objectMapper: ObjectMapper

    /* @SpyBean
     private var mockedSettings: MockedStatic<LocalDateTime>? = null
 */
    @BeforeEach
    fun setup() {
        objectMapper = ObjectMapper()
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
        // mockedSettings = Mockito.mockStatic(LocalDateTime::class.java)
    }

    /*    @AfterEach
        fun close() {
            mockedSettings!!.close()
        }*/

    fun defaultHeaders(): HttpHeaders {
        val headers = HttpHeaders()
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        return headers
    }
}