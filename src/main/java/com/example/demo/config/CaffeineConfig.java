package com.example.demo.config;

import lombok.Data;

@Data
public class CaffeineConfig {
    /**
     * 缓存名称
     */
    private String name;
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
    
    // 是否记录统计信息
    private boolean recordStats;
    
    // 过期策略: 最后一次写入后经过固定时间过期
    private boolean expireAfterWrite;
    // 单位毫秒
    private Long expireAfterWriteExpired;
    
    // 过期策略: 最后一次访问后经过固定时间过期
    private boolean expireAfterAccess;
	// 单位毫秒
    private Long expireAfterAccessExpired;
    
    // 过期策略: 最后一次写入后经过固定时间, 过期后刷新. 注意需要load配合
    private boolean refreshAfterWrite;
	// 单位毫秒
    private Long refreshAfterWriteExpired;
    
}
