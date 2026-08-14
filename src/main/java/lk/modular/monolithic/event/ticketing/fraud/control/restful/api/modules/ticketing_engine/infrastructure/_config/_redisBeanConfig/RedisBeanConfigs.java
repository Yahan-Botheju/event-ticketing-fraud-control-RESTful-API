package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure._config._redisBeanConfig;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.RedisLockService;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.lock.RedisLockServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

@Configuration
public class RedisBeanConfigs {

    //redis lock bean config
    @Bean
    public RedisLockService redisLockService(
            StringRedisTemplate stringRedisTemplate

    ) {
        DefaultRedisScript<Long> releaseRedisScript = new DefaultRedisScript<>();
        releaseRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("scripts/redis_lock.lua")));
        releaseRedisScript.setResultType(Long.class);

        return new RedisLockServiceImpl(stringRedisTemplate, releaseRedisScript);
    }

    //
}
