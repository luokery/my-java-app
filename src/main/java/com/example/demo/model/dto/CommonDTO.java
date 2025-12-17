package com.example.demo.model.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.aspect.IRecStatusAspect;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


/**
 * 公共DTO
 * 按需继承
 * @author 
 *
 */
@Data
public class CommonDTO implements IRecStatusAspect {
	
	/**
	 * 创建时间
	 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date crtTime;
	
	/**
	 * 创建人
	 */
	private String crtUser;
	
	/**
	 * 最后更新时间
	 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lstUpdTime;
	
	/**
	 * 最后更新用户
	 */
	private String lstUpdUser;
	
	/**
	 * 记录状态
	 */
	private Integer recStatus;

	@Override
	public Integer recStatus() {
		return recStatus;
	}

	@Override
	public void recStatus(Integer recStatus) {
		this.recStatus = recStatus;
	}
}
