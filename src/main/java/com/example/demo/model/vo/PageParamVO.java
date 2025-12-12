package com.example.demo.model.vo;

import java.util.Objects;

import com.example.demo.validation.Groups;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PageParamVO {
	
	/**
	 * 页面号
	 * 查询的页号
	 */
	@NotNull(groups = {Groups.Query.class, Groups.QueryPage.class}, message = "页面号不能为空")
	@Min(value=1, groups = {Groups.Query.class, Groups.QueryPage.class}, message = "页面号不能:最小不能小于1")
	private int pageNum = 0;
	
	/**
	 * 页大小
	 */
	@NotNull(groups = {Groups.Query.class, Groups.QueryPage.class}, message = "页大小不能为空")
	@Min(value=1, groups = {Groups.Query.class, Groups.QueryPage.class}, message = "页大小不能:最小不能小于1")
	@Max(value=1000, groups = {Groups.Query.class, Groups.QueryPage.class}, message = "页大小不能:最大不能大于1000")
	private int pageSize = 0;
	
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
	// 重载不同参数名称(layui)
	public void setPage(int page) {
		this.pageNum = page;
	}
	// 重载不同参数名称(layui)
	public void setLimit(int limit) {
		this.pageSize = limit;
	}
	@Override
	public String toString() {
		return String.format("PageParamVO [pageNum=%s, pageSize=%s]", pageNum, pageSize);
	}
	@Override
	public int hashCode() {
		return Objects.hash(pageNum, pageSize);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageParamVO other = (PageParamVO) obj;
		return pageNum == other.pageNum && pageSize == other.pageSize;
	}
}
