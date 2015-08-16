package org.campus.model.enums;

import org.apache.commons.lang.StringUtils;
import org.campus.core.type.EnumCodeGetter;
import org.campus.core.type.EnumDescriptionGetter;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
 * 创建会话标识0:创建会话,1:已有会话
 *
 * @author dengzhi
 *
 */
public enum IsNewSession implements EnumCodeGetter, EnumDescriptionGetter {
    CREATE("0","创建会话"),
    UNCREATE("1","已有会话");
    private String code;

    private String description;

    private IsNewSession(String code, String description) {
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

    public static IsNewSession getIsNewSessionByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (IsNewSession element : IsNewSession.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

}
