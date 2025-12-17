package com.example.demo.service.api;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.dto.PageDTO;
import com.example.demo.model.dto.UserDTO;

public interface BaseService<T, E> {
	
	/**
	 * 添加
	 * @param DTO
	 */
	@Transactional
	T save(T DTO);
	
	/**
	 * 删除
	 * @param DTO
	 */
	@Transactional
	T delete(T DTO);
	
	/**
	 * 批量删除
	 * @param DTO
	 */
	@Transactional
	int deleteBatch(String[] ids);
	
	/**
	 * 更新: 更新所有字段
	 * @param DTO
	 */
	@Transactional
	T updateAllField(UserDTO DTO);
	
	/**
	 * 更新: 更新所有非空字段
	 * @param DTO
	 */
	@Transactional
	T updateNotNull(UserDTO DTO);
	
	/**
	 * 查询
	 * @param DTO
	 * @return
	 */
	T query(T DTO);
	
	/**
	 * 查询列表
	 * @param DTO
	 * @return
	 */
	List<T> queryList(T DTO);

	/**
	 * 分页查询
	 * @param pageDto
	 * @return
	 */
	PageDTO<T, E> queryPageList(PageDTO<T, E> pageDTO);

}
