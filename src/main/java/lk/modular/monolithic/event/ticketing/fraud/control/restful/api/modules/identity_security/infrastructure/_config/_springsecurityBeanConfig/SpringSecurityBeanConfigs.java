package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure._config._springsecurityBeanConfig;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.IdentityProvider;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.UserRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure._security.IdentityProvider.IdentityProviderImpl;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure._security.resolver_user_provider.SpringSecurityUserProviderImpl;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.web_resolver.resolver.CurrentUserIdArgumentResolver;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure._security._user_wrapper.CustomUserDetailsService;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.web_resolver.resolver.SpringSecurityUserProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityBeanConfigs {

    //spring security custom use details service bean config
    @Bean
    public CustomUserDetailsService customUserDetailsService(
            UserRepository userRepository
    ) {
        return new CustomUserDetailsService(userRepository);
    }

    //identity provider bean config
    @Bean
    public IdentityProvider identityProvider(
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager
    ){
        return new IdentityProviderImpl(passwordEncoder, authenticationManager);
    }

    //resolver
    @Bean
    public CurrentUserIdArgumentResolver currentUserIdArgumentResolver(){
        return new CurrentUserIdArgumentResolver();
    }


    //spring security user provider impl config
    @Bean
    public SpringSecurityUserProvider springSecurityUserProvider(){
        return new SpringSecurityUserProviderImpl();
    }
}
