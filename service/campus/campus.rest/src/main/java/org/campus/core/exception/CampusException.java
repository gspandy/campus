package org.campus.core.exception;

import org.campus.core.exception.handler.ExceptionInfo;
import org.campus.core.exception.handler.ExceptionInfoGetter;
import org.springframework.core.NestedRuntimeException;

public class CampusException extends NestedRuntimeException implements ExceptionInfoGetter {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private String content;

    public CampusException(String content) {
        super(content);

        this.content = content;
    }

    public CampusException(String content, Throwable throwable) {
        super(content, throwable);

        this.content = content;
    }

    public CampusException(Integer code, String content) {
        super(content);

        this.code = code;
        this.content = content;
    }

    public CampusException(Integer code, String content, Throwable throwable) {
        super(content, throwable);

        this.code = code;
        this.content = content;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public ExceptionInfo getInfo() {
        return new ExceptionInfo(this.code, this.content);
    }

}
