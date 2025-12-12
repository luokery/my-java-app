package com.example.demo.model.dto;

import java.util.List;

import com.example.demo.model.IPage;

/****************************************************
 * <p>PageDTO.java(泛型分页DTO类)</p>
 * <a href="http://commons.apache.org/proper/commons-lang/分页">分页设计使用方法</a>
 * for a more comprehensive suite of {@code PageDTO} utilities.
 * @param <T>
 * 
 * @Version 1.0
 * @since 1.0
 * @Date 2019-11-17 12:12:12
 * @Author 
 * @Mail 
 * 
 */
public class PageDTO<T> implements IPage {
	
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
	
	/** 分页点 */
	private int offset = 0;
	
	/**
	 *  结果记录集
	 */
	private List<T> pageDataList = null;
	
	public PageDTO() {
	}
	
	public PageDTO( int pageNum, int pageSize) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}
	
	public PageDTO( int pageNum, int pageSize, int rowCount, List<T> pageDatalist) {
		this(pageNum, pageSize);
		this.pageDataList = pageDatalist;
		// 计算总行数
		this.rowCount = rowCount;
	}

	public List<T> getPageDataList() {
		return pageDataList;
	}
	public void setPageDataList(List<T> pageDataList) {
		this.pageDataList = pageDataList;
	}

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

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
}
