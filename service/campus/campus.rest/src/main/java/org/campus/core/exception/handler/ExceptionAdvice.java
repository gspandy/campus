package org.campus.core.exception.handler;

import java.lang.reflect.UndeclaredThrowableException;

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
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionData handleException(Exception ex) {
        ExceptionData exceptionData = null;
        Throwable exception = getHasInfoException(ex);
        if (exception == null) {
            exceptionData = new ExceptionData(HttpStatus.BAD_REQUEST.value(), "发生未知的错误");
        } else {
            ExceptionInfo exceptionInfo = ((ExceptionInfoGetter) exception).getInfo();
            Integer code = exceptionInfo.getCode();
            String message = exceptionInfo.getMessage();
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
