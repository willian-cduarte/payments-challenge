package br.com.will.payments.usecases

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.MockedStatic
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.mock.mockito.SpyBean
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
abstract class BaseTest {

    @SpyBean
    private var mockedSettings: MockedStatic<LocalDateTime>? = null

    @BeforeEach
    fun init() {
        mockedSettings = Mockito.mockStatic(LocalDateTime::class.java)
    }

    @AfterEach
    fun close() {
        mockedSettings!!.close()
    }


}