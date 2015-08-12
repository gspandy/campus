package org.campus.model.enums;

import org.apache.commons.lang.StringUtils;
import org.campus.core.type.EnumCodeGetter;
import org.campus.core.type.EnumDescriptionGetter;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SMSResult implements EnumCodeGetter, EnumDescriptionGetter {
    SMS_FAILED("0", "发送失败"), SMS_SUCCESS("1", "发送成功");

    private String code;

    private String description;

    private SMSResult(String code, String description) {
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

    public static SMSResult getSMSTypeByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (SMSResult element : SMSResult.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }
}
