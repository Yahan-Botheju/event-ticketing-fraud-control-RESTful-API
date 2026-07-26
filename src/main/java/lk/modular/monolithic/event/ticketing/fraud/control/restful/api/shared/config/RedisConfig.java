package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(
            RedisConnectionFactory redisConnectionFactory
    ) {
        //create template object
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        //set redis template to connection factory
        redisTemplate.setConnectionFactory(redisConnectionFactory);
    }
}
