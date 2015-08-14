package org.campus.repository.typehandler;

import org.apache.ibatis.type.MappedTypes;
import org.campus.core.mybatis.typeHandler.EnumCodeTypeHandler;
import org.campus.model.enums.ActiveType;

@MappedTypes(ActiveType.class)
public class ActiveTypeTypeHandler extends EnumCodeTypeHandler<ActiveType> {
    public ActiveTypeTypeHandler() {
        super(ActiveType.class);
    }

}
