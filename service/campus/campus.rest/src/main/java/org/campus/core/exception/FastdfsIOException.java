package org.campus.core.exception;
import org.campus.core.exception.handler.ExceptionInfo;
import org.campus.core.exception.handler.ExceptionInfoGetter;
import org.springframework.core.NestedRuntimeException;

public class FastdfsIOException extends NestedRuntimeException implements ExceptionInfoGetter{

	private static final long serialVersionUID = 4234899139606659965L;

    private Integer code;

    private Object[] args;

    private String content;

    public FastdfsIOException(String content) {
        super(content);

        this.content = content;
    }

    public FastdfsIOException(String content, Throwable throwable) {
        super(content, throwable);

        this.content = content;
    }

    /**
     * 
     * @param code 异常编码，在i18n配置文件中配置的编码，请确保该异常编码已经定义
     * @param content 后台异常内容，这个内容主要用于输出后台日志，便于异常诊断
     */
    public FastdfsIOException(Integer code, String content) {
        super(content);
        this.code = code;
        this.content = content;
    }

    /**
     * 
     * @param code 异常编码，在i18n配置文件中配置的编码，请确保该异常编码已经定义
     * @param args 在i18n配置文件中配置的错误描述中的占位符填充信息
     * @param content 后台异常内容，这个内容主要用于输出后台日志，便于异常诊断
     */
    public FastdfsIOException(Integer code, Object[] args, String content) {
        super(content);
        this.code = code;
        this.args = args;
        this.content = content;
    }

    /**
     * 
     * @param code 异常编码，在i18n配置文件中配置的编码，请确保该异常编码已经定义
     * @param content 后台异常内容，这个内容主要用于输出后台日志，便于异常诊断
     * @param throwable
     */
    public FastdfsIOException(Integer code, String content, Throwable throwable) {
        super(content, throwable);
        this.code = code;
        this.content = content;
    }

    /**
     * 
     * @param code 异常编码，在i18n配置文件中配置的编码，请确保该异常编码已经定义
     * @param args 在i18n配置文件中配置的错误描述中的占位符填充信息
     * @param content 后台异常内容，这个内容主要用于输出后台日志，便于异常诊断
     * @param throwable
     */
    public FastdfsIOException(Integer code, Object[] args, String content, Throwable throwable) {
        super(content, throwable);
        this.code = code;
        this.args = args;
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
        ExceptionInfo exceptionInfo = new ExceptionInfo(this.code, this.content);
        return exceptionInfo;
    }

}
