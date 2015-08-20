package org.campus.repository.typehandler;

import org.apache.ibatis.type.MappedTypes;
import org.campus.core.mybatis.typeHandler.EnumCodeTypeHandler;
import org.campus.model.enums.ApiType;

@MappedTypes(ApiType.class)
public class ApiTypeHandler extends EnumCodeTypeHandler<ApiType> {
    public ApiTypeHandler() {
        super(ApiType.class);
    }

}
