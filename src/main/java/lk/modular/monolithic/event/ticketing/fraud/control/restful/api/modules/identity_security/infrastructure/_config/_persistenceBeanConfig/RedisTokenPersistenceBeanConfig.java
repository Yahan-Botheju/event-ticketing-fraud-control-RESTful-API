package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure._config._persistenceBeanConfig;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.RedisTokenRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure._redis.RedisTokenRepositoryImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisTokenPersistenceBeanConfig {
    @Bean
    public RedisTokenRepository redisTokenRepository(
            RedisTemplate<String, Object> redisTemplate,
            @Qualifier("refreshTokenPrefix") String refreshTokenPrefix
    ) {
        return new RedisTokenRepositoryImpl(redisTemplate, refreshTokenPrefix);
    }
}
