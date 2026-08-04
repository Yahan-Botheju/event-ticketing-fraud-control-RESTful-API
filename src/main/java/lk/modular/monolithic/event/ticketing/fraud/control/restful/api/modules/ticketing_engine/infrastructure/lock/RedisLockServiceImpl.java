package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.lock;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.RedisLockService;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisLockServiceImpl implements RedisLockService {

    //inject required dependencies
    private RedisTemplate<String, Object> redisTemplate;

    public RedisLockServiceImpl(
            RedisTemplate<String, Object> redisTemplate
    ) {
        this.redisTemplate = redisTemplate;
    }
}
