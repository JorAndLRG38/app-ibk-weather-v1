package com.ibk.weather.config;

import com.ibk.weather.models.CurrentWeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
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
  @Primary
  public ReactiveRedisConnectionFactory reactiveRedisConnectionWithHost(
      @Value("${spring.data.redis.host}") String host,
      @Value("${spring.data.redis.port}") int port,
      @Value("${spring.data.redis.password}") String password)
      throws RedisConnectionFailureException {
    RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
    try {
      config.setHostName(host);
      config.setPort(port);
      config.setPassword(RedisPassword.of(password));
    } catch (RedisConnectionFailureException ex) {
      throw new RedisConnectionFailureException(ex.getMessage());
    }
    return new LettuceConnectionFactory(config);
  }

  @Bean
  public ReactiveRedisOperations<String, CurrentWeatherResponse>
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