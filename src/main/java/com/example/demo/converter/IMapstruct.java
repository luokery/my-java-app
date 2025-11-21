package com.example.demo.converter;

import java.util.List;


public interface IMapstruct<V, D, E> {
	/**
	 * Vo 转换为 Dto
	 * @param vo
	 * @return
	 */
    D toDto(V vo);
	/**
	 * Vo list 转换为 Dto list
	 * @param List vo
	 * @return
	 */
    List<D> toDto(List<V> vo);
    
	/**
	 * dto 转换为 vo
	 * @param dto
	 * @return
	 */
    V toVo(D dto);
	/**
	 * Dto list 转换为 Vo list
	 * @param List dto
	 * @return
	 */
    List<V> toVo(List<D> dto);
    
	/**
	 * dto 转换为 entity
	 * @param dto
	 * @return
	 */
    E toEntity(D dto);
	/**
	 * Dto list 转换为 entity list
	 * @param List dto
	 * @return
	 */
    List<E> toEntity(List<D> dto);
    
	/**
	 * entity 转换为 dto
	 * @param entity
	 * @return
	 */
    D entityToDto(E entity);
	/**
	 * entity list 转换为 dto list
	 * @param List entity
	 * @return
	 */
    List<D> entityToDto(List<E> entity);
}
