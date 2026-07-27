package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure._redis;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.RedisTokenRepository;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTokenRepositoryImpl implements RedisTokenRepository {

    //inject required dependencies
    private final RedisTemplate<String, Object> redisTemplate;
    private final String refreshTokenPrefix;

    public RedisTokenRepositoryImpl(
            RedisTemplate<String, Object> redisTemplate,
            String refreshTokenPrefix
    ) {
        this.redisTemplate = redisTemplate;
        this.refreshTokenPrefix = refreshTokenPrefix;
    }
}
