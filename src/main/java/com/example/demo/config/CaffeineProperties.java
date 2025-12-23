package com.example.demo.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * Caffeine属性配置
 */
@Data
@ConfigurationProperties(prefix = "caffeine")
public class CaffeineProperties {

    /**
     * 自定义缓存列表
     */
    private List<CaffeineConfig> caffeines;
	
}
