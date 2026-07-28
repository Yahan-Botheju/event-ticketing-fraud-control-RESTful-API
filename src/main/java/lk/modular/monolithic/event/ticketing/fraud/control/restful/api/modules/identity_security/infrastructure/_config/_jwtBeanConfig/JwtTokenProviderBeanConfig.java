package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure._config._jwtBeanConfig;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.JwtTokenProvider;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure._security.JwtTokenProviderImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtTokenProviderBeanConfig {

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
