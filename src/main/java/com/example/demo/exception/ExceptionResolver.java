package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.example.demo.design.policymodel.ExceptionHandlerPolicyModel;


/**
 * 异常处理策略模型
 * 构造异常处理策略
 * 实现InitializingBean接口: spring容器创建bean的时候帮你执行初始化的接口
 * 实现ApplicationContextAware接口: 用于注入 ApplicationContext
 * @author 
 */
@Component
public class ExceptionResolver implements InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private Map<Class<?>, ExceptionHandlerPolicyModel<?>> exceptionHandlerMap = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
    	
    	// 1. 获取容器中实现: 异常处理实现
        Map<String, ExceptionHandlerPolicyModel> beanMap = applicationContext.getBeansOfType(ExceptionHandlerPolicyModel.class);
        
        // 2. 保存到缓存中, 方便拿取
        for (String key : beanMap.keySet()) {
            this.exceptionHandlerMap.put( beanMap.get(key).sequence(), beanMap.get(key));
        }
    }
    
    /**
     * 获取处理实现
     * @param clss  类
     * @return
     */
    public ExceptionHandlerPolicyModel<?> getHandler(Class<?> clss) {
        return exceptionHandlerMap.get(clss);
    }

}