package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure._config._jwtBeanConfig;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.JwtTokenProvider;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.RedisTokenRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure._security.JwtTokenProviderImpl;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure._security._user_wrapper.CustomUserDetailsService;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure._security.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;

@Configuration
public class JwtBeanConfigs {

    //jwt filter bean config
    @Bean
    public OncePerRequestFilter oncePerRequestFilter(
            JwtTokenProvider jwtTokenProvider,
            CustomUserDetailsService customUserDetailsService,
            RedisTokenRepository redisTokenRepository
    ) {
        return new JwtAuthenticationFilter(jwtTokenProvider, customUserDetailsService, redisTokenRepository);
    }

    //jwt token provider bean config
    @Bean
    public JwtTokenProvider jwtTokenProvider(
            SecretKey secretKey,
            @Value("${application.security.jwt.refresh-token.expiration-second}") long refreshTokenExpirationSecond,
            @Value("${application.security.jwt.access-token.expiration-second}") long accessTokenExpirationSecond
    ) {
        long refreshTokenExpirationMs = refreshTokenExpirationSecond * 1000L;
        long accessTokenExpirationMs = accessTokenExpirationSecond * 1000L;

        return new JwtTokenProviderImpl(secretKey, refreshTokenExpirationMs, accessTokenExpirationMs);
    }
}
