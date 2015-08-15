package org.campus.model.enums;

import org.apache.commons.lang.StringUtils;
import org.campus.core.type.EnumCodeGetter;
import org.campus.core.type.EnumDescriptionGetter;

public enum TopicType implements EnumCodeGetter,EnumDescriptionGetter {
	
	RELAXATION("1","休闲"),
	NOVELTY("2","新鲜"),
	PRIVACY("3","秘密"),
	SPEECH("4","言论"),
	HOT("5","热门"),
	ATTENTION("6","关注");
	
    private String code;

    private String description;
    
    private TopicType(String code, String description) {
        this.code = code;
        this.description = description;
    }
    
	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public String getCode() {
		return this.code;
	}
	
    @Override
    public String toString() {
        return "(" + this.code + "," + this.description + ")";
    }
    
    public static TopicType getTypeCodeByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (TopicType element : TopicType.values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }
}
