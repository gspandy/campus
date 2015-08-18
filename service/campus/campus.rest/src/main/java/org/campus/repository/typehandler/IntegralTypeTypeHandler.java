package org.campus.repository.typehandler;

import org.apache.ibatis.type.MappedTypes;
import org.campus.core.mybatis.typeHandler.EnumCodeTypeHandler;
import org.campus.model.enums.IntegralType;

@MappedTypes(IntegralType.class)
public class IntegralTypeTypeHandler extends EnumCodeTypeHandler<IntegralType> {
    public IntegralTypeTypeHandler() {
        super(IntegralType.class);
    }

}
