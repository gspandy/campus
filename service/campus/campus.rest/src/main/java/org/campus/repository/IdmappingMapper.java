package org.campus.repository;

import org.campus.model.Idmapping;
import org.springframework.stereotype.Repository;

@Repository
public interface IdmappingMapper {
    int deleteByFdhtId(String fileId);

    int insert(Idmapping record);

    int insertSelective(Idmapping record);
    
    Idmapping selectByFdhtId(String fileId);

}