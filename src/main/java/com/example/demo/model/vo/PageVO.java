package com.example.demo.model.vo;

import java.util.List;
import java.util.Objects;

/**
 * <p>PageDTO.java(泛型分页DTO类)</p>
 * <a href="http://commons.apache.org/proper/commons-lang/分页">分页设计使用方法</a>
 * for a more comprehensive suite of {@code PageDTO} utilities.
 * 
 * @param <T> 结果VO
 * @param <E> 参数VO
 * 
 * @Version 1.0
 * @since 1.0
 * @Date 2019-11-17 12:12:12
 * @Author lj
 * @Mail lj@163.com
 *
 */
public class PageVO<T, E> {
	/**
	 * 页面号
	 * 查询的页号
	 */
	private int pageNum = 0;
	
	/**
	 * 页大小
	 */
	private int pageSize = 0;
	
	/**
	 * 总行数
	 */
	private int rowCount = 0;
	
	/**
	 * 总页数
	 */
	private int pageCount = 0;
	
	/**
	 *  参数数据
	 */
	private E paramData = null;
	
	/**
	 *  结果记录集
	 */
	private List<T> resultData = null;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public E getParamData() {
		return paramData;
	}

	public void setParamData(E paramData) {
		this.paramData = paramData;
	}

	public List<T> getResultData() {
		return resultData;
	}

	public void setResultData(List<T> resultData) {
		this.resultData = resultData;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pageCount, pageNum, pageSize, paramData, resultData, rowCount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageVO<?, ?> other = (PageVO<?, ?>) obj;
		return pageCount == other.pageCount && pageNum == other.pageNum && pageSize == other.pageSize
				&& Objects.equals(paramData, other.paramData) && Objects.equals(resultData, other.resultData)
				&& rowCount == other.rowCount;
	}

	@Override
	public String toString() {
		return String.format("PageVO [pageNum=%s, pageSize=%s, rowCount=%s, pageCount=%s, paramData=%s, resultData=%s]",
				pageNum, pageSize, rowCount, pageCount, paramData, resultData);
	}
}