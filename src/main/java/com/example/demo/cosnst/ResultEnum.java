package com.example.demo.cosnst;

import java.text.MessageFormat;

public enum ResultEnum implements ResultFace {
	
	// 500 Internal Server Error
    UNKONW_ERROR(500, "未知错误"),
    SUCCESS(200, "成功"),
    ERROR(1, "失败"),
    // 400 Bad Request
    VALIDATION_ERROR(400, "Validation failed"),
    // 404 Not Found
    PRODUCT_NOT_FOUND(404, "Product not found"),
    
    PARAM_VERIFY_ERROR(6, "参数验证失败"),
    PARAM_c_ERROR(5, "参数转换失败"),
    PARAM_v_ERROR(6, "参数验证失败"),
    Unauthorized(401, "请先登录"),
    Forbidden(403, "没有权限!"),
    LoginFailure(401, "登录凭证已失效，请重新登录"),
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
	public String toLogMsg() {
		return MessageFormat.format(toLogMsgPattern, code, msg);
	}

	@Override
	public Integer code() {
		return code;
	}

	@Override
	public String message() {
		return msg;
	}
}