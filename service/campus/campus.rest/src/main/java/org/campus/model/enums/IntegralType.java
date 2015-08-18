package org.campus.model.enums;

import org.apache.commons.lang.StringUtils;
import org.campus.core.type.EnumCodeGetter;
import org.campus.core.type.EnumDescriptionGetter;

import com.fasterxml.jackson.annotation.JsonValue;

public enum IntegralType implements EnumCodeGetter, EnumDescriptionGetter {

    LOGIN("0", "每日登陆"), POSTS("1", "发帖"), CHECk_POSTS("2", "审帖"), COMMENT("3", "评论"), SHARE("4", "分享"), TRANSFER("5",
            "转发");

    private String code;

    private String description;

    private IntegralType(String code, String description) {
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

    public static IntegralType getIntegralTypeByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (IntegralType element : IntegralType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

}
