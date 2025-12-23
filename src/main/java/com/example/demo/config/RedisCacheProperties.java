package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * Caffeine属性配置
 */
@Data
@ConfigurationProperties(prefix = "redis-cache")
public class RedisCacheProperties {

    /**
     * 是否启动
     */
    private Boolean enable = false;

    /**
     * 缓存初始容量
     */
    private Integer initCapacity = 256;

    /**
     * 缓存最大容量，超过之后会按照最近最少策略进行缓存剔除
     */
    private Integer maxCapacity = 10000;

    /**
     * 是否允许空值null作为缓存的value
     */
    private Boolean allowNullValue = true;
    
    
    /**
     * 最后一次写入或访问后经过固定时间过期，单位：秒
     */
    private Long expired = 1800L;
	
}
