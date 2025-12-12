package com.example.demo.model;

public interface IPage {

	int getRowCount();

	int getPageSize();

	void setPageCount(int pageCount);

	void setPageNum(int pageNum);

	int getPageNum();

	void setPageSize(int pageSize);

}
