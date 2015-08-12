package org.campus.model.enums;

import org.apache.commons.lang.StringUtils;
import org.campus.core.type.EnumCodeGetter;
import org.campus.core.type.EnumDescriptionGetter;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DisplayModel implements EnumCodeGetter, EnumDescriptionGetter {
    MOON("0", "月亮"), SUN("1", "太阳");

    private String code;

    private String description;

    private DisplayModel(String code, String description) {
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

    public static DisplayModel getDisplayModelByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (DisplayModel element : DisplayModel.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }
}
