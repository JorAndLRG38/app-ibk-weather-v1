package com.ibk.weather.config;

import com.ibk.weather.models.CurrentWeatherResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
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

  /**
   * Metodo para la serialización de objetos Redis.
   *
   * @param factory  parametros de conexión a redis
   * @return ReactiveRedisTemplate.
   */
  @Bean
  public ReactiveRedisTemplate<String, CurrentWeatherResponse> reactiveRedisTemplate(
      ReactiveRedisConnectionFactory factory) {
    Jackson2JsonRedisSerializer<CurrentWeatherResponse> serializer =
        new Jackson2JsonRedisSerializer<>(CurrentWeatherResponse.class);
    RedisSerializationContext<String, CurrentWeatherResponse> context =
        RedisSerializationContext.<String, CurrentWeatherResponse>newSerializationContext(
                new StringRedisSerializer())
            .value(serializer).build();
    return new ReactiveRedisTemplate<>(factory, context);
  }
}