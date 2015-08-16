package org.campus.repository.typehandler;

import org.apache.ibatis.type.MappedTypes;
import org.campus.core.mybatis.typeHandler.EnumCodeTypeHandler;
import org.campus.model.enums.SessionType;

@MappedTypes(SessionType.class)
public class SessionTypeHandler extends EnumCodeTypeHandler<SessionType>{
    public SessionTypeHandler() {
        super(SessionType.class);
    }
}
