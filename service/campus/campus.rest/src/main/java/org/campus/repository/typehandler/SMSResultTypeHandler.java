package org.campus.repository.typehandler;

import org.apache.ibatis.type.MappedTypes;
import org.campus.core.mybatis.typeHandler.EnumCodeTypeHandler;
import org.campus.model.enums.SMSResult;

@MappedTypes(SMSResult.class)
public class SMSResultTypeHandler extends EnumCodeTypeHandler<SMSResult> {
    public SMSResultTypeHandler() {
        super(SMSResult.class);
    }

}
