package com.example.nobsv2;

import java.util.Arrays;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfiguration {
    
    @Bean // gets injected into the Spring container using dependency injection
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager manager = new ConcurrentMapCacheManager();

        manager.setAllowNullValues(false); // if value is null don't cache it
        manager.setCacheNames(Arrays.asList("productCache"));
        
        return manager;
    }

    @CacheEvict(allEntries = true, value = "productCache")
    @Scheduled(fixedDelay = 7000, initialDelay = 0)
    public void evictProductCache() {
        System.out.println("Evicting product cache");
    }
}
