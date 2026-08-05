package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.lock;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.RedisLockService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import java.time.Duration;
import java.util.Collections;

public class RedisLockServiceImpl implements RedisLockService {

    //inject required dependencies
    private final StringRedisTemplate stringRedisTemplate;
    private final RedisScript<Long> releaseRedisScript;

    public RedisLockServiceImpl(
            StringRedisTemplate stringRedisTemplate,
            RedisScript<Long>  releaseRedisScript
    ) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.releaseRedisScript = releaseRedisScript;
    }

    //distribute lock
    @Override
    public boolean acquireLock(
            String lockKey,
            String lockValue,
            long expireTimeInSeconds
    ) {
        Boolean success = stringRedisTemplate.opsForValue()
                .setIfAbsent(lockKey, lockValue, Duration.ofSeconds(expireTimeInSeconds));

        return Boolean.TRUE.equals(success);
    }

    //release lock
    @Override
    public boolean releaseLock(String lockKey, String lockValue) {
        Long result = stringRedisTemplate.execute(
                this.releaseRedisScript,
                Collections.singletonList(lockKey),
                lockValue
        );
        return Long.valueOf(1L).equals(result);
    }
}
