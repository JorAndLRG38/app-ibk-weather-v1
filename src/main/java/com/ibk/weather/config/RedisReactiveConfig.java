package com.ibk.weather.config;

import com.ibk.weather.models.CurrentWeatherResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Configuración de Redis para operaciones reactivas.
 * Define un bean de ReactiveRedisOperations para manejar la serialización y deserialización
 * de objetos CurrentWeatherResponse en Redis.
 */
@Configuration
public class RedisReactiveConfig {

  @Bean
  ReactiveRedisOperations<String, CurrentWeatherResponse>
        redisOperations(ReactiveRedisConnectionFactory factory) {
    Jackson2JsonRedisSerializer<CurrentWeatherResponse> serializer
        = new Jackson2JsonRedisSerializer<>(CurrentWeatherResponse.class);
    RedisSerializationContext.RedisSerializationContextBuilder<String,
        CurrentWeatherResponse> builder =
        RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
    RedisSerializationContext<String, CurrentWeatherResponse> context
        = builder.value(serializer).build();
    return new ReactiveRedisTemplate<>(factory, context);
  }
}