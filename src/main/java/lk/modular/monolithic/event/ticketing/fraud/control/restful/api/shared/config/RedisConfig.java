package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(
            RedisConnectionFactory redisConnectionFactory
            @Qualifier("redisObjectMapper")Qualifier ObjectMapper redisObjectMapper
    ) {
        //create template object
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        //set redis template to connection factory
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        //serialize key as string
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        //initiate redis serializer
        RedisSerializer<Object> redisJsonSerializer = RedisSerializer.json();

        //serialize values
        redisTemplate.setValueSerializer(redisJsonSerializer);
        redisTemplate.setHashValueSerializer(redisJsonSerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
