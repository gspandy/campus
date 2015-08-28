package org.campus.model.enums;

import org.apache.commons.lang.StringUtils;
import org.campus.core.type.EnumCodeGetter;
import org.campus.core.type.EnumDescriptionGetter;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CheckType implements EnumCodeGetter, EnumDescriptionGetter {

    COMPLAIN("1", "投诉"), SUPPORT("2", "赞"), NOT_SUPPORT("3", "踩");

    private String code;

    private String description;

    private CheckType(String code, String description) {
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

    public static CheckType getCheckTypeCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (CheckType element : CheckType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

}
