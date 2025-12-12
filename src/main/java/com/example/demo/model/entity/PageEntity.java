package com.example.demo.model.entity;


public class PageEntity {
	
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
	
	public PageEntity( int pageNum, int pageSize) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
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
