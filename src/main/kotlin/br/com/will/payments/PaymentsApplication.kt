package br.com.will.payments

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class PaymentsApplication

fun main(args: Array<String>) {
	runApplication<PaymentsApplication>(*args)
}
