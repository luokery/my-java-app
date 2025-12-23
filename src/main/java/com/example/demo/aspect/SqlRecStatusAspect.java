package com.example.demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 切面: 对数据库的查询操作
 * 		recstatus: 1正常 2锁定 3删除
 * 		删除的数据只有管理员能查询
 * 
 * 切面挂载点解释: 类似正则表达式
 * @Pointcut("execution(* com.example.demo.service..*.*(..)) && args(dataDTO,..)")
 * execution() 为表达式主体
 * 返回类型: 第一个 * 号的位置, 表示返回值类型, * 表示所有类型;
 * 包名: com.example.demo.service表示需要拦截的包名;
 * 匹配路径: 
 * 		 . 表示当前包; 例: com.example.demo.service表示service下的包, api和impl不会匹配
 * 		 ..后面的两个句点表示当前包和当前包的所有子包, 包、子包下所有类的方法; 例: com.example.demo.service.api 和 impl
 * 类名: 第二个 * 号的位置,表示类名, * 表示所有类; 例:UserServiceImpl表示UserServiceImpl实现类. User*表示User开头的类.
 * 方法: *(..) 这个星号表示方法名, * 表示所有的方法; 例:save表示save方法. find*表示find开头的方法.
 * 参数: 
 * 		 (..) 弧里面表示方法的参数; 其中的..表示任意数量的参数. 
 *       args(dataDTO,..)表示第一个参数是dataDTO, 一般在切面时候直接获取参数用. 否则用JoinPoint获取关联信息.
 */
@Slf4j
@Aspect
@Component
public class SqlRecStatusAspect {
	
	/**
	 * 切面锚点： 
	 * 使用锚点的好处是可以方便的切不同类型， 如 @Before， @Around
	 * 其中args表示参数， 参数顺序依赖， 名称无关 
	 * @param securityReqVO
	 */
	@Pointcut("execution(* com.example.demo.service..*.*(..)) && args(dataDTO,..)")
	private void recStatusAspect(IRecStatusAspect dataDTO) {}
    
    /**
     * 前置通知(引用切入点)
     * @param dataDTO
     * @Before(argNames = "dataDTO", value = "recStatusAspect(dataDTO)")
     * 
     */
    @Before(argNames = "dataDTO", value = "recStatusAspect(dataDTO)")
    public void before(IRecStatusAspect dataDTO){
        log.info("before ...-------------------------------------");
        dataDTO.recStatus();
    }
}
