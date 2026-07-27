package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;
import tools.jackson.databind.ObjectMapper;


@Configuration
public class RedisConfig {

    //refresh token refix
    @Value("${application.security.redis.refresh-token-prefix}")
    private String refreshTokenPrefix;

    @Bean
    public String refreshTokenPrefix() {
        return refreshTokenPrefix;
    }


    // custom serialize bean template
    @Bean
    public RedisTemplate<String, Object> redisTemplate(
            RedisConnectionFactory redisConnectionFactory,
            @Qualifier("redisObjectMapper") ObjectMapper redisObjectMapper
    ) {
        //create template object
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        //set redis template to connection factory
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        //serialize key as string
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        //initiate redis serializer
        GenericJacksonJsonRedisSerializer jsonSerializer = new GenericJacksonJsonRedisSerializer(redisObjectMapper);

        //serialize values
        redisTemplate.setValueSerializer(jsonSerializer);
        redisTemplate.setHashValueSerializer(jsonSerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
