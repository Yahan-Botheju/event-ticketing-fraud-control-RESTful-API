package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.infrastructure._redis;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.repositories.RedisTokenRepository;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

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

    /*  __HELPER_METHODS__ */

    private String buildKey(Long userId) {
        return refreshTokenPrefix + userId;
    }



    /*  __PUBLIC_METHODS__ */

    //save refresh token in redis context
    @Override
    public void saveRefreshToken(
            Long userId,
            String refreshToken,
            long durationInMs
    ) {
        String key = buildKey(userId);

        redisTemplate.opsForValue().set(key, refreshToken, Duration.ofMillis(durationInMs));
    }
}
