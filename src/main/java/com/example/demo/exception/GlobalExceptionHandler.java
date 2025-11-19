package com.example.demo.exception;

import com.example.demo.model.vo.Result;
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

    /**
     * Handles specific business logic exceptions.
     * The HTTP status is 200 for most business errors, except for 403, 404, 500.
     * The 'code' in the response body contains the specific business error code.
     *
     * @param ex The BusinessException that was thrown.
     * @return A ResponseEntity containing the standardized error response.
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Result<Object>> handleBusinessException(BusinessException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        // The 'code' in the body is always the specific error code from the enum
        Result<Object> result = Result.error(errorCode.getHttpStatus(), errorCode.getMessage(), ex.getData());

        // Determine the HTTP status code based on the new rule
        HttpStatus httpStatus;
        int errorCodeHttpStatus = errorCode.getHttpStatus();

        if (errorCodeHttpStatus == 403 || errorCodeHttpStatus == 404 || errorCodeHttpStatus == 500) {
            httpStatus = HttpStatus.valueOf(errorCodeHttpStatus);
        } else {
            httpStatus = HttpStatus.OK;
        }

        return new ResponseEntity<>(result, httpStatus);
    }

    /**
     * Handles validation exceptions and translates them into a BusinessException.
     *
     * @param ex The MethodArgumentNotValidException that was thrown.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        // This will be caught and handled by handleBusinessException
        throw new BusinessException(ErrorCode.VALIDATION_ERROR, errors);
    }

    /**
     * A fallback handler for all other exceptions.
     *
     * @param ex The unexpected exception.
     * @return A generic internal server error response.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result<Object>> handleAllOtherExceptions(Exception ex) {
        // In a real application, you would log the exception ex here
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        Result<Object> result = Result.error(errorCode.getHttpStatus(), errorCode.getMessage());
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
