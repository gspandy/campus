package org.campus.model.enums;

import org.apache.commons.lang.StringUtils;
import org.campus.core.type.EnumCodeGetter;
import org.campus.core.type.EnumDescriptionGetter;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
 * 是否已读(0:未读;1:已读)
 *
 * @author dengzhi
 *
 */
public enum IsReadType implements EnumCodeGetter, EnumDescriptionGetter {
    UNREAD("0","未读"),
    READ("1","已读");
    private String code;

    private String description;

    private IsReadType(String code, String description) {
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

    public static IsReadType getIsReadTypeByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (IsReadType element : IsReadType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

}
