package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure._config._identityProviderBeanConfig;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.IdentityProvider;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure._security.IdentityProvider.IdentityProviderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class IdentityProviderImplBeanConfig {
    @Bean
    public IdentityProvider identityProvider(
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager
    ){
        return new IdentityProviderImpl(passwordEncoder, authenticationManager);
    }
}
