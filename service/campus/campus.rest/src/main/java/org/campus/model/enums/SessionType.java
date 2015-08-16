package org.campus.model.enums;

import org.apache.commons.lang.StringUtils;
import org.campus.core.type.EnumCodeGetter;
import org.campus.core.type.EnumDescriptionGetter;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
 * 会话类型代码,1单聊 2 群聊
 *
 * @author dengzhi
 *
 */
public enum SessionType implements EnumCodeGetter, EnumDescriptionGetter {
    SINGLE_CHANNEL("1","单聊"),
    GROUP_CHANNEL("2","群聊");
    private String code;

    private String description;

    private SessionType(String code, String description) {
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

    public static SessionType getSessionTypeByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (SessionType element : SessionType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }
}
