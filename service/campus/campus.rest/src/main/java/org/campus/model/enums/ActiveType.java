package org.campus.model.enums;

import org.apache.commons.lang.StringUtils;
import org.campus.core.type.EnumCodeGetter;
import org.campus.core.type.EnumDescriptionGetter;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ActiveType implements EnumCodeGetter, EnumDescriptionGetter {

    INVALID("0", "无效"), ACTIVE("1", "有效");

    private String code;

    private String description;

    private ActiveType(String code, String description) {
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

    public static ActiveType getActiveTypeByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (ActiveType element : ActiveType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

}
