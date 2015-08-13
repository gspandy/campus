package org.campus.core.exception.handler;

import java.lang.reflect.UndeclaredThrowableException;

import org.campus.config.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionData handleException(Exception ex) {
        ExceptionData exceptionData = null;
        Throwable exception = getHasInfoException(ex);
        if (exception == null) {
            exceptionData = new ExceptionData(HttpStatus.INTERNAL_SERVER_ERROR.value(), "发生未知的错误");
        } else {
            ExceptionInfo exceptionInfo = ((ExceptionInfoGetter) exception).getInfo();
            Integer code = exceptionInfo.getCode();
            String message = null;
            if (code != null) {
                try {
                    message = SystemConfig.getString(code.toString(), String.valueOf(exceptionInfo.getMessage()));
                } catch (Exception e) {
                    logger.error("根据异常编码获取异常描述信息发生异常，errorCode：" + code);
                    message = exceptionInfo.getMessage();
                }
            } else {
                message = exceptionInfo.getMessage();
            }
            if (StringUtils.isEmpty(message)) {
                message = "发生未知的错误";
            }
            exceptionData = new ExceptionData(code, message);
        }
        logger.error("exception code:" + exceptionData.getCode() + ",exception message:" + exceptionData.getMessage(),
                ex);
        return exceptionData;
    }

    private Throwable getHasInfoException(Throwable throwable) {
        Throwable exception = null;
        if (throwable instanceof ExceptionInfoGetter) {
            exception = (Exception) throwable;
        }
        Throwable childThrowable = null;
        if (throwable instanceof UndeclaredThrowableException) {
            childThrowable = ((UndeclaredThrowableException) throwable).getUndeclaredThrowable();
        } else {
            childThrowable = throwable.getCause();
        }
        if (childThrowable != null) {
            Throwable childExp = getHasInfoException(childThrowable);
            if (childExp != null) {
                return childExp;
            }
        }
        return exception;
    }
}
