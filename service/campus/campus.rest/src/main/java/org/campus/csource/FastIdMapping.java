/*
 * Copyright (C), 2014-2015, 联创车盟汽车服务有限公司
 * FileName: FastIdMapping.java
 * Author:   zhi
 * Date:     2015年1月27日 上午10:30:54
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>    <time>    <version>    <desc>
 * 修改人姓名        修改时间        版本号        描述
 */
package org.campus.csource;

/**
 * id映射接口
 *
 * @author dengzhi
 *
 */
public interface FastIdMapping {
    public boolean setFdfsFileId(String fdfs_file_id,String fdhtFileId) throws Exception;
    public String getFdfsFileId(String fdhtId) throws Exception;
    public boolean deleteFdhtFileId(String fdhtId) throws Exception;
}
