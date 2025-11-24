package com.example.demo.model.vo;

import org.slf4j.MDC;

import com.example.demo.cosnst.BusinessEnum;
import com.example.demo.cosnst.ConstSysBase;
import com.example.demo.cosnst.ResultFace;

import lombok.Data;

@Data
public class ResultVo<T> {

    private int code;
    private String message;
    private T data;

    public static <T> ResultVo<T> success(T data) {
        ResultVo<T> result = new ResultVo<>();
        result.setCode(200);
        result.setMessage("Success");
        result.setData(data);
        return result;
    }

    public static <T> ResultVo<T> success() {
        return success(null);
    }

    public static <T> ResultVo<T> error(int code, String message) {
        ResultVo<T> result = new ResultVo<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> ResultVo<T> error(int code, String message, T data) {
        ResultVo<T> result = new ResultVo<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
    public static <T> ResultVo<T> build(ResultFace faceEnum){
    	ResultVo<T> resultVO = new ResultVo<T>();
    	resultVO.setCode( faceEnum.code());
    	resultVO.setMessage( faceEnum.message());
//    	resultVO.setSerialNumber( MDC.get( ConstSysBase.BUSINESS_NO_KEY));
		return resultVO;
	}
}
