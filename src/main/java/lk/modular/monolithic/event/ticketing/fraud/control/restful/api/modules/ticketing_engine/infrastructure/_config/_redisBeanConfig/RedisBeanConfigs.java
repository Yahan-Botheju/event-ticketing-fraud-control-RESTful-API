package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure._config._redisBeanConfig;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.RedisLockService;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.lock.RedisLockServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisBeanConfigs {

    //redis bean config
    @Bean
    public RedisLockService redisLockService(
            RedisTemplate<String, Object> redisTemplate
    ) {
        return new RedisLockServiceImpl(redisTemplate);
    }
}
