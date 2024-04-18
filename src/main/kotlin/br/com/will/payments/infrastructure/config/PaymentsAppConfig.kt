package br.com.will.payments.infrastructure.config

import br.com.will.payments.infrastructure.database.accounts.AccountCashAdapter
import br.com.will.payments.infrastructure.database.accounts.AccountCashRepository
import br.com.will.payments.infrastructure.database.notifications.NotificationsAdapter
import br.com.will.payments.infrastructure.database.notifications.NotificationsRepository
import br.com.will.payments.infrastructure.database.payments.PaymentsAdapter
import br.com.will.payments.infrastructure.database.payments.PaymentsRepository
import br.com.will.payments.infrastructure.database.users.UserRepository
import br.com.will.payments.infrastructure.database.users.UsersAdapter
import br.com.will.payments.usecases.account.gateways.AccountCashGateway
import br.com.will.payments.usecases.notifications.gateways.NotificationsGateway
import br.com.will.payments.usecases.payment.gateways.NotifyTransactionGateway
import br.com.will.payments.usecases.payment.gateways.PaymentTransactionGateway
import br.com.will.payments.usecases.users.gateways.UsersGateway
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import java.util.Locale


@Configuration
@EnableJpaRepositories(basePackages = ["br.com.will.payments.infrastructure.database"])
@EntityScan(basePackages = ["br.com.will.payments.infrastructure.database"])
@EnableScheduling
class PaymentsAppConfig {

    @Bean
    fun localeResolver(): LocaleResolver {
        val localResolver = SessionLocaleResolver()
        localResolver.setDefaultLocale(Locale.US)
        return localResolver
    }

    @Bean(name = ["messageResource"])
    fun messageResource(): MessageSource {
        val messageSource = ResourceBundleMessageSource()
        messageSource.setBasename("message")
        return messageSource
    }

    @Bean
    @Primary
    @Qualifier("usersGateway")
    fun usersGateway(userRepository: UserRepository): UsersGateway =
            UsersAdapter(userRepository)

    @Bean
    @Qualifier("PaymentTransactionGateway")
    fun paymentTransactionGateway(paymentsRepository: PaymentsRepository): PaymentTransactionGateway =
            PaymentsAdapter(paymentsRepository)

    @Bean
    @Qualifier("AccountCashGateway")
    fun accountCashGateway(accountCashRepository: AccountCashRepository): AccountCashGateway =
            AccountCashAdapter(accountCashRepository)

    @Bean
    @Primary
    @Qualifier("NotifyTransactionGateway")
    fun notifyTransactionGateway(notificationsRepository: NotificationsRepository): NotifyTransactionGateway =
            NotificationsAdapter(notificationsRepository)

    @Bean
    @Qualifier("NotificationsGateway")
    fun notificationsGateway(notificationsRepository: NotificationsRepository): NotificationsGateway =
            NotificationsAdapter(notificationsRepository)

}