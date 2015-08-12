package org.campus.repository.typehandler;

import org.apache.ibatis.type.MappedTypes;
import org.campus.core.mybatis.typeHandler.EnumCodeTypeHandler;
import org.campus.model.enums.DisplayModel;

@MappedTypes(DisplayModel.class)
public class DisplayModelTypeHandler extends EnumCodeTypeHandler<DisplayModel> {
    public DisplayModelTypeHandler() {
        super(DisplayModel.class);
    }

}
