package org.campus.repository.typehandler;

import org.apache.ibatis.type.MappedTypes;
import org.campus.core.mybatis.typeHandler.EnumCodeTypeHandler;
import org.campus.model.enums.TypeCode;

@MappedTypes(TypeCode.class)
public class TypeCodeTypeHandler extends EnumCodeTypeHandler<TypeCode> {
    public TypeCodeTypeHandler() {
        super(TypeCode.class);
    }

}
