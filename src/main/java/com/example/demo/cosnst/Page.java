package com.example.demo.cosnst;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.model.IPage;

public class Page<T extends IPage> {
	
	/** 默认页面大小{@value} */
	private final static int DEFAULT_PAGE_SIZE = 20; 
	
	/** 最小页面大小{@value} */
	private final static int PAGE_SIZE_MIN = 1;
	
	/** 最大页面大小{@value} */
	private final static int PAGE_SIZE_MAX = 1000;
	
	public int getPageCount(T page) {
		
		if(page == null)
			return 0;
		
		if(page.getRowCount() <= 0) {
			return 0;
		}
		if(page.getPageSize() <= 0) {
			return 0;
		}
		// 计算出总页数
		int pageCount = (page.getRowCount() + page.getPageSize() - 1) / page.getPageSize();
		page.setPageCount(pageCount);
		return pageCount;
	}
	
	private static final Logger log = LoggerFactory.getLogger(Page.class);
	public boolean verify(T page) {
		
		/**
		 * 处理分页数
		 */
		if (page.getPageNum() < 1) {
			log.error("分页: 错误 {}", "当前分页值转换错误, 默认当页为1, 请检查!");
			throw new RuntimeException("430001");
		}
		/**
		 * 处理分页大小
		 */
		if (page.getPageSize() < PAGE_SIZE_MIN) {
			log.error("分页: 警告 分页大小,小于最小分页大小[{}], 使用默认分页[{}], 请检查!", PAGE_SIZE_MIN, DEFAULT_PAGE_SIZE);
			throw new RuntimeException("430002");
		}
		
		// 页面大小不能大于页面最大值, 否则设置为默认页面大小
		// TODO 是否抛出异常
		if (page.getPageSize() > PAGE_SIZE_MAX) {
			log.error("分页: 警告 分页大小大于最大分页大小[{}], 使用默认分页[{}], 请检查!", PAGE_SIZE_MAX, DEFAULT_PAGE_SIZE);
			throw new RuntimeException("430003");
		}
		return true;
	}
}
