package org.campus.model.enums;

import org.apache.commons.lang.StringUtils;
import org.campus.core.type.EnumCodeGetter;
import org.campus.core.type.EnumDescriptionGetter;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SMSType implements EnumCodeGetter, EnumDescriptionGetter {
    SMS_REGISTER("1", "注册短信"), SMS_FORGET("2", "找回密码短信");

    private String code;

    private String description;

    private SMSType(String code, String description) {
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

    public static SMSType getSMSTypeByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (SMSType element : SMSType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

}
