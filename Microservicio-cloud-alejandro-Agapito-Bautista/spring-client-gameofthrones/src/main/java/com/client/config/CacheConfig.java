package com.client.config;

import java.util.HashMap;
import java.util.Map;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfig {

	/**
	 * Bean correspondiente a CacheManager 
	 * permite establecer con que cache trabajar.
	 * Concurrent Map Cache Manager
	 * @return
	 *
	@Bean
	public CacheManager getManager() {
	    return new ConcurrentMapCacheManager("translations");
		
	}*/
	
	/**
	 * Bean correspondiente a CacheManager 
	 * permite establecer con que cache trabajar.
	 * Esta configurado con cache de redis
	 * @return
	 */
	@Bean
	public CacheManager getManager(RedissonClient redisson) {
		// correspondiente a cache manager : return new ConcurrentMapCacheManager("translations");
		Map<String, CacheConfig> config = new HashMap<>();
		config.put("testMap", new CacheConfig());
		return new RedissonSpringCacheManager(redisson);
	}
	
	/**
	 * Bean 
	 */
	@Bean(destroyMethod = "shutdown")
	public RedissonClient redisson() {
		Config config = new Config();
		config.useSingleServer().setAddress("redis://127.0.0.1:6379");
		return Redisson.create();
	}
}
