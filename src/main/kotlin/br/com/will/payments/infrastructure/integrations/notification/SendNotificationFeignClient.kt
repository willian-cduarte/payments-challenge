package br.com.will.payments.infrastructure.integrations.notification

import br.com.will.payments.infrastructure.integrations.authorization.AuthorizationResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody


@FeignClient(
        value = "sendNotification",
        url = "https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6"
)
interface SendNotificationFeignClient {

    @PostMapping
    fun sendNotification(@RequestBody notificationRequest: NotificationRequest): NotificationResponse

}


