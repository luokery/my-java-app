package com.example.demo.converter;

import java.util.List;

import com.example.demo.model.dto.PageDTO;
import com.example.demo.model.vo.PageVO;


public interface IMapstruct<V, D, E> {
	/**
	 * Vo 转换为 Dto
	 * @param vo
	 * @return
	 */
    D toDTO(V vo);
	/**
	 * Vo list 转换为 Dto list
	 * @param List vo
	 * @return
	 */
    List<D> toDTO(List<V> vo);
    
	/**
	 * dto 转换为 vo
	 * @param dto
	 * @return
	 */
    V toVO(D dto);
	/**
	 * Dto list 转换为 Vo list
	 * @param List dto
	 * @return
	 */
    List<V> toVO(List<D> dto);
    
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
    D entityToDTO(E entity);
	/**
	 * entity list 转换为 dto list
	 * @param List entity
	 * @return
	 */
    List<D> entityToDTO(List<E> entity);
    
    /**
     * 分页DTO转换为分页VO
     * @param pageDTO
     * @return
     */
    PageVO<V, V> toPageVO(PageDTO<D, D> pageDTO);
    
    /**
     * 分页VO转换为分页DTO
     * @param pageVO
     * @return
     */
    PageDTO<D, D> toPageDTO(PageVO<V, V> pageVO);
}
