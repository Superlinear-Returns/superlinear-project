package com.eight.common.module.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager  = new CaffeineCacheManager();
        // 設置緩存名稱，緩存名稱和配置需要匹配 業務多一個@Cacheable 範例 @Cacheable(cacheNames = "shortLivedCache", key = "#id")
        cacheManager.setCacheNames(List.of("shortLivedCache", "longLivedCache", "defaultCache"));
        // default
        cacheManager.setCaffeine(Caffeine.newBuilder()
                // 缓存的過期時間限制
                .expireAfterWrite(30, TimeUnit.MINUTES)
                // 缓存個數的最大容量
                .maximumSize(500));
        return cacheManager;
    }
    @Bean
    public CaffeineCache shortLivedCache() {
        return new CaffeineCache("shortLivedCache", Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .maximumSize(100)
                .build());
    }

    @Bean
    public CaffeineCache longLivedCache() {
        return new CaffeineCache("longLivedCache", Caffeine.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build());
    }
}

