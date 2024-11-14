package com.paul.project.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotRoleException;
import com.paul.project.common.BaseResponse;
import com.paul.project.common.ErrorCode;
import com.paul.project.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author paul
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("businessException: " + e.getMessage(), e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("runtimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, e.getMessage());
    }

    @ExceptionHandler(NotRoleException.class)
    public BaseResponse<?> notRoleExceptionHandler(NotRoleException e) {
        log.error("notRoleException", e);
        return ResultUtils.error(ErrorCode.NO_AUTH_ERROR, e.getMessage());
    }

    @ExceptionHandler(NotLoginException.class)
    public BaseResponse<?> notLoginExceptionHandler(NotLoginException e) {
        log.error("notLoginException", e);
        return ResultUtils.error(ErrorCode.NOT_LOGIN_ERROR, e.getMessage());
    }
}
