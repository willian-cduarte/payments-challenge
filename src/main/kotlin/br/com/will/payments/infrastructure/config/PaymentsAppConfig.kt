package br.com.will.payments.infrastructure.config

import br.com.will.payments.infrastructure.database.users.UserRepository
import br.com.will.payments.infrastructure.database.users.UsersAdapter
import br.com.will.payments.usecases.users.gateways.UsersGateway
import br.com.will.payments.usecases.users.AddUsersUseCase
import br.com.will.payments.usecases.users.impl.AddUsersUseCaseImpl
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["br.com.will.payments.infrastructure.database"])
@EntityScan(basePackages = ["br.com.will.payments.infrastructure.database"])
class PaymentsAppConfig {


    @Bean
    fun usersRepositoryGateway(userRepository: UserRepository): UsersGateway =
            UsersAdapter(userRepository)

    @Bean
    @Qualifier("addUsersUseCase")
    fun addUsersUseCase(usersGateway: UsersGateway): AddUsersUseCase =
            AddUsersUseCaseImpl(usersGateway)

}