package com.example.demo.config;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.github.benmanes.caffeine.cache.Caffeine;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableConfigurationProperties(CaffeineProperties.class)
@Slf4j
public class CacheConfig {

    /**
     * 自定义缓存配置
     */
    @Bean(name = "simpleCacheManager")
    @Primary
    public CacheManager caffeineCacheManager(CaffeineProperties caffeineProperties) {
    	
        
        //把各个cache注册到cacheManager中，CaffeineCache实现了org.springframework.cache.Cache接口
//        CaffeineCache userCache = new CaffeineCache("userCache", 
//        		Caffeine.newBuilder()
//        		// 初始的缓存大小
//        		.initialCapacity(30)
//        		// 缓存的最大限制
//        		.maximumSize(100L)
//        		// 记录统计信息
//        		.recordStats()
//        		// 过期策略: 最后一次写入后经过固定时间过期
//        		.expireAfterWrite(3L, TimeUnit.SECONDS)
//        		.build()
//        		);
        
//        CaffeineCache roleCache = new CaffeineCache("roleCache", 
//        		Caffeine.newBuilder()
//        		// 初始的缓存大小
//        		.initialCapacity(30)
//        		// 缓存的最大限制
//        		.maximumSize(100L)
//        		// 记录统计信息
//        		.recordStats()
//        		// 过期策略: 最后一次写入后经过固定时间过期
//        		.expireAfterWrite(3L, TimeUnit.SECONDS)
//        		.build()
//        		);
		
        // 异步加载dict缓存 FIXME: 未完成
//		Supplier<Object> dictLoad = new Supplier<Object>() {
//			@Override
//			public Object get() {
//				return null;
//			}
//		};
//		
//		AsyncCache<String, Object> dictAsyncCache = Caffeine.newBuilder()
//			    .expireAfterWrite(10, TimeUnit.MINUTES)  // 写入后10分钟过期
//			    .maximumSize(1000)  // 最大1000条目
//			    .refreshAfterWrite(5, TimeUnit.MINUTES)  // 写入后5分钟刷新
//			    .buildAsync(new AsyncCacheLoader<String, Object>() {
//			        @Override
//			        public CompletableFuture<Object> asyncLoad(String key, Executor executor) {
//			            return CompletableFuture.supplyAsync(dictLoad, executor);
//			        }
//			    });
//
//		CaffeineCache dictCache = new CaffeineCache("dictCache", (Cache<Object, Object>) dictAsyncCache);
        
        List<CaffeineCache> caches = new ArrayList<>();
//        caches.add(userCache);
//        caches.add(roleCache);
//        caches.add(dictCache);
        
        
        List<CaffeineConfig> Caffeines = caffeineProperties.getCaffeines();
        for(CaffeineConfig item: Caffeines) {
        	
        	Caffeine<Object, Object> caffeine = Caffeine.newBuilder();
        	// 初始的缓存大小
        	caffeine.initialCapacity(item.getInitCapacity());
        	// 缓存的最大限制
        	caffeine.maximumSize(item.getMaxCapacity());
        	// 记录统计信息
        	if(item.isRecordStats()) {
        		caffeine.recordStats();
        	}
        	if(item.isExpireAfterAccess()) {
        		caffeine.expireAfterAccess(item.getExpireAfterAccessExpired() , TimeUnit.MILLISECONDS);
        	}
        	// 过期策略: 最后一次写入后经过固定时间过期
        	if(item.isExpireAfterWrite()) {
        		caffeine.expireAfterWrite(item.getExpireAfterWriteExpired(), TimeUnit.MILLISECONDS);
        	}
        	if(item.isRefreshAfterWrite()) {
        		caffeine.refreshAfterWrite(item.getRefreshAfterWriteExpired() , TimeUnit.MILLISECONDS);
        	}
            CaffeineCache customCache = new CaffeineCache(item.getName(), caffeine.build(), item.getAllowNullValue());
            caches.add(customCache);
        }
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(caches);
        return manager;
    }

	@Bean(name = "redisCacheManager")
    public RedisCacheManager redisCacheManager(RedisConnectionFactory factory) {
    	
    	RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
    			// 缓存数据保存时间: 3秒
    			.entryTtl(Duration.ofSeconds(3L))
    			// 设置key为String
    			.serializeKeysWith(SerializationPair.fromSerializer(new StringRedisSerializer()))
    			// 设置value 为自动转Json的Object
    			.serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
    			// 不缓存null
    			.disableCachingNullValues()
    			// 缓存前戳
    			.prefixCacheNameWith("RDS_CACHE")
    			// 关闭缓存前戳
    			.disableKeyPrefix();
    	
    	RedisCacheManager redisCacheManager = RedisCacheManager
    			// Redis 连接工厂
    			.builder(factory)
    			// 缓存配置
    			.cacheDefaults(config)
    			// 配置同步修改或删除 put/evict
    			.transactionAware()
    			.build();
    	
    	return redisCacheManager;
    }
	
    /**
     * 依赖注入日志输出
     */
    @PostConstruct
    private void initDi() {
        log.info("############ {} Configuration initDi.", this.getClass().getSimpleName().split("\\$\\$")[0]);
    }
}
