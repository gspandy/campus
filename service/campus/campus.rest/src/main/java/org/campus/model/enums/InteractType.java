package org.campus.model.enums;

import org.apache.commons.lang.StringUtils;
import org.campus.core.type.EnumCodeGetter;
import org.campus.core.type.EnumDescriptionGetter;

import com.fasterxml.jackson.annotation.JsonValue;

public enum InteractType implements EnumCodeGetter, EnumDescriptionGetter {

    NOT_SUPPORT("0", "踩"), SUPPORT("1", "赞");

    private String code;

    private String description;

    private InteractType(String code, String description) {
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

    public static InteractType getInteractTypeCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (InteractType element : InteractType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

}
