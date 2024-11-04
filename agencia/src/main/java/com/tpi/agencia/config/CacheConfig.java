package com.tpi.agencia.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Value("${tpi-agencia.microservicio-restricciones.duration}")
    private int cacheDuration;

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("restrictionsApiCache") {
            @Override
            protected Cache createConcurrentMapCache(String name) {
                return new CaffeineCache(name, Caffeine.newBuilder()
                        .expireAfterWrite(cacheDuration, TimeUnit.MINUTES)
                        .build());
            }
        };
    }
}