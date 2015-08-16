package org.campus.repository.typehandler;

import org.apache.ibatis.type.MappedTypes;
import org.campus.core.mybatis.typeHandler.EnumCodeTypeHandler;
import org.campus.model.enums.IsReadType;

@MappedTypes(IsReadType.class)
public class IsReadTypeHandler extends EnumCodeTypeHandler<IsReadType> {
    public IsReadTypeHandler() {
        super(IsReadType.class);
    }
}
