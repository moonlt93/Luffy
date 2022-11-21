package com.zerobase.luffy.configuration;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
@EnableCaching
public class RedisConfig {


    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        return new LettuceConnectionFactory(host,port);
    }


    @Bean
    public RedissonClient redissonClient(){

        Config redisConfig = new Config();
        redisConfig.useSingleServer()
                .setAddress("redis://"+host+":"+port)
                .setConnectionPoolSize(5)
                .setConnectionMinimumIdleSize(5);
        return Redisson.create(redisConfig);

    }

}
