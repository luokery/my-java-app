package com.example.demo.exception;

import com.example.demo.cosnst.BusinessEnum;
import com.example.demo.design.policymodel.ExceptionHandlerPolicyModel;
import com.example.demo.model.vo.ResponseVO;
import com.example.demo.model.vo.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler using a strategy pattern with ErrorCode and BusinessException.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	/**
	 * 异常处理策略模型
	 */
	private ExceptionResolver exceptionResolver;
	
    public GlobalExceptionHandler(ExceptionResolver exceptionResolver) {
		super();
		this.exceptionResolver = exceptionResolver;
	}

    /**
     * 所有的业务异常处理
     * Handles specific business logic exceptions.
     * The HTTP status is 200 for most business errors, except for 403, 404, 500.
     * The 'code' in the response body contains the specific business error code.
     *
     * @param ex The BusinessException that was thrown.
     * @return A ResponseEntity containing the standardized error response.
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseVO<?> handleBusinessException(BusinessException ex) {
    	
    	ExceptionHandlerPolicyModel exceptionHandler = exceptionResolver.getHandler(ex.getClass());
    	
    	if(null != exceptionHandler) {
    		
    		log.info("业务异常处理: ", ex); // FIXME : 是否需要异常自己处理, 异常的日志等级, 现在全抛错误. info warn error
    		return exceptionHandler.handler( ex);
    	}else {
        	log.error("系统异常: {}", BusinessEnum.UNKONW_ERROR.getMsg(), ex);
            return Result.build(BusinessEnum.UNKONW_ERROR);
    	}
    }

    /**
     * Handles validation exceptions and translates them into a BusinessException.
     *
     * @param ex The MethodArgumentNotValidException that was thrown.
     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        // This will be caught and handled by handleBusinessException
        throw new BusinessException(com.example.demo.cosnst.ResultEnum.VALIDATION_ERROR, errors);
    }

    /**
     * A fallback handler for all other exceptions.
     *
     * @param ex The unexpected exception.
     * @return A generic internal server error response.
     */
    @ExceptionHandler(Exception.class)
    public ResponseVO<?> handleAllOtherExceptions(Exception ex) {
        // In a real application, you would log the exception ex here
        
    	ExceptionHandlerPolicyModel exceptionHandler = exceptionResolver.getHandler(ex.getClass());
    	
    	if(null != exceptionHandler) {
    		
    		log.error("全局异常处理: ", ex); // FIXME : 是否需要异常自己处理, 异常的日志等级, 现在全抛错误. info warn error
    		return exceptionHandler.handler( ex);
    	}else {
        	log.error("系统异常: {}", BusinessEnum.UNKONW_ERROR.getMsg(), ex);
            return Result.build(BusinessEnum.UNKONW_ERROR);
    	}
    }
}
