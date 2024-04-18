package br.com.will.payments.usecases.notifications

import br.com.will.payments.usecases.notifications.NotificationTaskExecutor
import br.com.will.payments.usecases.notifications.gateways.NotificationsGateway
import br.com.will.payments.usecases.notifications.gateways.TransactionNotificationExecutorGateway
import org.awaitility.Awaitility
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito

import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.SpyBean
import java.util.concurrent.TimeUnit

@ExtendWith(MockitoExtension::class)
@SpringBootTest
class NotificationsTaskTest {

    @Mock
    lateinit var notificationsGateway: NotificationsGateway

    @Mock
    lateinit var transactionNotificationExecutorGateway: TransactionNotificationExecutorGateway

    @SpyBean
    lateinit var notificationTaskExecutor: NotificationTaskExecutor

    @Test
    fun `validate scheduled execution`() {
        Awaitility.await()
                .atMost(15, TimeUnit.SECONDS)
                .untilAsserted {
                    Mockito.verify(notificationTaskExecutor, Mockito.times(1)).run()
                }
    }

}