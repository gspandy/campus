package org.campus.repository.typehandler;

import org.apache.ibatis.type.MappedTypes;
import org.campus.core.mybatis.typeHandler.EnumCodeTypeHandler;
import org.campus.model.enums.InteractType;

@MappedTypes(InteractType.class)
public class InteractTypeHandler extends EnumCodeTypeHandler<InteractType> {
    public InteractTypeHandler() {
        super(InteractType.class);
    }

}
