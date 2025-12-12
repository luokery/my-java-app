package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.demo.model.entity.PageEntity;

public interface BaseMapper<T> {
	
	/**
	 * 增加
	 * 
	 * @param entity
	 */
	int insert(T entity);
	
	/**
	 * 删除: 通过编号
	 * 
	 * @param id
	 */
	int deleteById(@Param("entity") String id);
	
	/**
	 * 删除: 通过条件, 危险, 使用时候请明确使用并检查条件是否为null或者空字符串
	 * 
	 * @param entity
	 */
	int delete(T entity);

	int deleteBatch(String[] ids);
	
	/**
	 * 修改: 全字段更新,通过编号
	 * 不允许更新创建时间,创建人
	 * @param entity
	 */
	int updateAllFieldByNo(T entity);
	
	/**
	 * 修改: 通过动态条件,当字段不为null或者空时候,通过编号
	 * 不允许更新创建时间,创建人
	 * @param entity
	 */
	int updateNotNullByNo(T entity);

	/**
	 * 查询列表: 通过动态条件
	 * 
	 * @param entity
	 * @return
	 */
	List<T> queryList(T entity);
	
	/**
	 * 查询: 通过编号
	 * 
	 * @param entity
	 * @return
	 */
	T findByNo(@Param("userNo") String userNo);
	
	/**
	 * 查询:查询分页列表
	 * 
	 * @param page
	 * @param entity
	 * @return
	 */
	 List<T> queryPageList(@Param("page") PageEntity page, @Param("entity") T entity);

	 /**
	  * 查询: 查询数据总数
	  * 
	  * @param entity
	  * @return
	  */
	 Integer findCount(@Param("entity") T entity);
}
