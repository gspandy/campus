package org.campus.repository.typehandler;

import org.apache.ibatis.type.MappedTypes;
import org.campus.core.mybatis.typeHandler.EnumCodeTypeHandler;
import org.campus.model.enums.CheckType;

@MappedTypes(CheckType.class)
public class CheckTypeTypeHandler extends EnumCodeTypeHandler<CheckType> {
    public CheckTypeTypeHandler() {
        super(CheckType.class);
    }

}
