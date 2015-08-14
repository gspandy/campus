package org.campus.model.enums;

import org.apache.commons.lang.StringUtils;
import org.campus.core.type.EnumCodeGetter;
import org.campus.core.type.EnumDescriptionGetter;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AnonymousType implements EnumCodeGetter, EnumDescriptionGetter {

    NOT_ANONYMOUS("0", "不匿名"), ANONYMOUS("1", "匿名");

    private String code;

    private String description;

    private AnonymousType(String code, String description) {
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

    public static AnonymousType getAnonymousTypeByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (AnonymousType element : AnonymousType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

}
