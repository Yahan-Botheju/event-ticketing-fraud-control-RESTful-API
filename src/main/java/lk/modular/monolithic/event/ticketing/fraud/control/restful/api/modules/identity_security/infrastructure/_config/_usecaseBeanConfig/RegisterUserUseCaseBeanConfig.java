package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure._config._usecaseBeanConfig;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.IdentityProvider;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.UserRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.register.RegisterUserUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.usecase.register.RegisterUserUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticationUserUseCaseBeanConfig {
    @Bean
    public RegisterUserUseCase authenticateUserUseCase(
            IdentityProvider identityProvider,
            UserRepository userRepository
    ) {
        return new RegisterUserUseCaseImpl(identityProvider, userRepository);
    }
}
