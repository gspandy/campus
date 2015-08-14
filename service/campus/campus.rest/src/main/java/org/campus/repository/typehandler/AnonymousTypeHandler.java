package org.campus.repository.typehandler;

import org.apache.ibatis.type.MappedTypes;
import org.campus.core.mybatis.typeHandler.EnumCodeTypeHandler;
import org.campus.model.enums.AnonymousType;

@MappedTypes(AnonymousType.class)
public class AnonymousTypeHandler extends EnumCodeTypeHandler<AnonymousType> {
    public AnonymousTypeHandler() {
        super(AnonymousType.class);
    }

}
