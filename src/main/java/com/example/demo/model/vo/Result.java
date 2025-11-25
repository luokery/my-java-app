package com.example.demo.model.vo;

import org.slf4j.MDC;

import com.example.demo.cosnst.ConstSysBase;
import com.example.demo.cosnst.ResultEnum;
import com.example.demo.cosnst.ResultFace;

/**
 * 结果自定义构建工具
 * @author 
 *
 */
public class Result {
	
	/**
	 * 响应构建(code String)
	 * @param <T>
	 * @param code  	返回的代码
	 * @param message	返回的消息
	 * @param data  	返回的数据
	 * @return
	 */
	public static <T> ResponseVO<T> build(String code, String message, T data){
		ResponseVO<T> responseVO = new ResponseVO<T>();
		responseVO.setCode(code);
		responseVO.setMessage(message);
		responseVO.setData(data);
		responseVO.setSerialNumber( MDC.get( ConstSysBase.BUSINESS_NO_KEY));
		return responseVO;
	}
	
	/**
	 * 响应构建(code Integer)
	 * @param <T>
	 * @param code  	返回的代码
	 * @param message	返回的消息
	 * @param data  	返回的数据
	 * @return
	 */
	public static <T> ResponseVO<T> build(Integer code, String message, T data){
		ResponseVO<T> responseVO = new ResponseVO<T>();
		responseVO.setCode( String.valueOf(code));
		responseVO.setMessage(message);
		responseVO.setData( data);
		responseVO.setSerialNumber( MDC.get( ConstSysBase.BUSINESS_NO_KEY));
		return responseVO;
	}
	
	/**
	 * 响应构建(ResultFace T)
	 * @param <T>
	 * @param faceEnum
	 * @param data
	 * @return
	 */
	public static <T> ResponseVO<T> build(ResultFace faceEnum, T data){
		ResponseVO<T> responseVO = new ResponseVO<T>();
		responseVO.setCode( String.valueOf( faceEnum.code()));
		responseVO.setMessage( faceEnum.message());
		responseVO.setData( data);
		responseVO.setSerialNumber( MDC.get( ConstSysBase.BUSINESS_NO_KEY));
		return responseVO;
	}
	/**
	 * 响应构建(ResultFace)
	 * @param <T>
	 * @param faceEnum
	 * @param data
	 * @return
	 */
	public static <T> ResponseVO<T> build(ResultFace faceEnum){
		ResponseVO<T> responseVO = new ResponseVO<T>();
		responseVO.setCode( String.valueOf( faceEnum.code()));
		responseVO.setMessage( faceEnum.message());
		responseVO.setSerialNumber( MDC.get( ConstSysBase.BUSINESS_NO_KEY));
		return responseVO;
	}
	
	/**
	 * 系统默认成功返回(无数据)
	 * @param <T>
	 * @return
	 */
	public static <T> ResponseVO<T> success(){
		return Result.build(ResultEnum.SUCCESS, null);
	}
	
	/**
	 * 系统默认成功返回(有数据)
	 * @param <T>
	 * @return
	 */
	public static <T> ResponseVO<T> success(T data){
		return Result.build(ResultEnum.SUCCESS, data);
	}
}
