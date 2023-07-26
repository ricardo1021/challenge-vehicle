package com.api.vehicle.apiRestVehicleConversion.infrastructure.config;

import com.api.vehicle.apiRestVehicleConversion.infrastructure.adapter.cache.entity.QuoteRedis;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
public class RedisConf {

    @Bean
    ReactiveRedisTemplate<String, QuoteRedis> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<QuoteRedis> serializer = new Jackson2JsonRedisSerializer<>(QuoteRedis.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, QuoteRedis> builder =
                RedisSerializationContext.newSerializationContext(new Jackson2JsonRedisSerializer<>(String.class));
        RedisSerializationContext<String, QuoteRedis> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(factory, context);
    }
}
