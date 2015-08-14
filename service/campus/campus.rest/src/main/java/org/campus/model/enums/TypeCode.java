package org.campus.model.enums;

import org.apache.commons.lang.StringUtils;
import org.campus.core.type.EnumCodeGetter;
import org.campus.core.type.EnumDescriptionGetter;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TypeCode implements EnumCodeGetter, EnumDescriptionGetter {

    ACTIVITY("0", "活动"), FRESH_NEWS("1", "新鲜事"), PHOTOS("2", "相册");

    private String code;

    private String description;

    private TypeCode(String code, String description) {
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

    public static TypeCode getTypeCodeByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (TypeCode element : TypeCode.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

}
