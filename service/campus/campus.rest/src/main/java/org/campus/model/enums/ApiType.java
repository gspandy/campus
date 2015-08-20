package org.campus.model.enums;

import org.apache.commons.lang.StringUtils;
import org.campus.core.type.EnumCodeGetter;
import org.campus.core.type.EnumDescriptionGetter;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ApiType implements EnumCodeGetter, EnumDescriptionGetter {

    QQ("1", "QQ"), WEIBO("2", "微博"), WEIXIN("3", "微信");

    private String code;

    private String description;

    private ApiType(String code, String description) {
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

    public static ApiType getApiTypeByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (ApiType element : ApiType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

}
