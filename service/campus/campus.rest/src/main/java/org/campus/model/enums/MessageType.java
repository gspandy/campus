package org.campus.model.enums;

import org.apache.commons.lang.StringUtils;
import org.campus.core.type.EnumCodeGetter;
import org.campus.core.type.EnumDescriptionGetter;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
 * 消息类型(0:系统公告;1:普通用户信息)
 *
 * @author dengzhi
 *
 */
public enum MessageType implements EnumCodeGetter, EnumDescriptionGetter {
    SYSTEM_ANNOUNCEMENT("0","系统公告"),
    GENERAL_USER("1","普通用户信息");
    private String code;

    private String description;

    private MessageType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @JsonValue
    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        return "(" + this.code + "," + this.description + ")";
    }

    public static MessageType getMessageTypeByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (MessageType element : MessageType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

}
