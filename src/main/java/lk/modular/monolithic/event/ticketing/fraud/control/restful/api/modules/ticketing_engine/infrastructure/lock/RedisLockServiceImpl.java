package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.lock;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.RedisLockService;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisLockServiceImpl implements RedisLockService {

    //inject required dependencies
    private final StringRedisTemplate stringRedisTemplate;

    public RedisLockServiceImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }
}
