package com.example.demo.cosnst;

import java.text.MessageFormat;

/**
 * 系统结果异常
 * @author 
 */
public enum BusinessEnum implements ResultFace {
	
    UNKONW_ERROR(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg()),
    SUCCESS(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg()),
    ERROR(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg()),
    PARAM_c_ERROR(5, "参数转换失败"),
    PARAM_v_ERROR(6, "参数验证失败"),
    DATA_COPY_ERROR(7, "拷贝数据失败"),
    NOT_FONUT_FILE(8, "没有找到文件"),
    FILE_TYPE_ERROR(8, "文件格式错误"),
    
    Lock_gain_failure(8, "可能有多个人同时处理, 请检查数据后重试!")
    ;

    private Integer code;

    private String msg;

    BusinessEnum(Integer code, String msg) {
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