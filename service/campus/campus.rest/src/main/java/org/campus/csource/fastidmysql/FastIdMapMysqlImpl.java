/*
 * Copyright (C), 2014-2015, 联创车盟汽车服务有限公司
 * FileName: FastIdMapMysqlClient.java
 * Author:   zhi
 * Date:     2015年1月27日 上午10:39:00
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>    <time>    <version>    <desc>
 * 修改人姓名        修改时间        版本号        描述
 */
package org.campus.csource.fastidmysql;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.campus.core.exception.FastdfsIOException;
import org.campus.csource.FastIdMapping;
import org.campus.model.Idmapping;
import org.campus.repository.IdmappingMapper;
import org.campus.util.ToolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 使用mysql作为id映射存储
 * 
 * @author dengzhi
 * 
 */
@Component("mysqlImpl")
public class FastIdMapMysqlImpl implements FastIdMapping {
    private static Logger logger = LoggerFactory.getLogger(FastIdMapMysqlImpl.class);

    @Autowired
    private IdmappingMapper idmappingRepository;
    
    public FastIdMapMysqlImpl(){}
    
    public FastIdMapMysqlImpl(IdmappingMapper idmappingRepository){
        this.idmappingRepository = idmappingRepository;
    }

    @Override
    public boolean setFdfsFileId(String fdfs_file_id, String fdhtFileId) throws Exception {
        Idmapping idmapping = new Idmapping();
        idmapping.setId(ToolUtil.getId());
        idmapping.setFdfsid(fdfs_file_id);
        idmapping.setFileId(fdhtFileId);
        idmapping.setCreateTime(new Date());
        int status = 0;
        try {
            status = idmappingRepository.insert(idmapping);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        
        return status == 1;
    }

    @Override
    public String getFdfsFileId(String fdhtId) throws Exception {
        Idmapping idmapping = idmappingRepository.selectByFdhtId(fdhtId);
        if(null == idmapping || StringUtils.isBlank(idmapping.getFdfsid())){
            throw new FastdfsIOException(1800001, "文件id不存在");
        }
        return idmapping.getFdfsid();
    }

    @Override
    public boolean deleteFdhtFileId(String fdhtId) throws Exception {
        int status = 0;
        try{
            status = idmappingRepository.deleteByFdhtId(fdhtId);
        }catch (Exception e) {
            logger.error(e.getMessage());
        }
        
        return status == 1;
    }
    
}
