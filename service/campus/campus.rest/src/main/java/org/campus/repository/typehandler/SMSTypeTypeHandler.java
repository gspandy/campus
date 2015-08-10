package org.campus.repository.typehandler;

import org.apache.ibatis.type.MappedTypes;
import org.campus.core.mybatis.typeHandler.EnumCodeTypeHandler;
import org.campus.model.enums.SMSType;

@MappedTypes(SMSType.class)
public class SMSTypeTypeHandler extends EnumCodeTypeHandler<SMSType> {
    public SMSTypeTypeHandler() {
        super(SMSType.class);
    }

}
