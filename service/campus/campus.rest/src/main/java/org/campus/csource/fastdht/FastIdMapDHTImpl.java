/*
 * Copyright (C), 2014-2015, 联创车盟汽车服务有限公司
 * FileName: FastMapDHTImpl.java
 * Author:   zhi
 * Date:     2015年1月27日 下午2:06:18
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>    <time>    <version>    <desc>
 * 修改人姓名        修改时间        版本号        描述
 */
package org.campus.csource.fastdht;

import org.campus.csource.FastIdMapping;
import org.campus.csource.common.DFSConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * DHT实现
 * 
 * @author dengzhi
 * 
 */
@Component("fastDHT")
public class FastIdMapDHTImpl implements FastIdMapping {
    @Autowired
    private FastDHTClient fdhtClient;
    
    protected int status; // last error code

    @Override
    public boolean setFdfsFileId(String fdfs_file_id, String fdhtFileId) throws Exception {
        try {
            KeyInfo keyInfo = new KeyInfo(DFSConstants.fdhtNamespace, DFSConstants.MY_CLIENT_FILE_ID_KEY_NAME, fdhtFileId);
            if ((this.status = this.fdhtClient.set(keyInfo, fdfs_file_id)) != 0) {
                // this.fdhtClient.delete_file1(fdfs_file_id); // rollback
            }
        } catch (Exception ex) {
            this.status = 5;
            ex.printStackTrace();
            // this.newClient.delete_file1(fdfs_file_id); // rollback
        }

        return (this.status == 0);
    }

    @Override
    public String getFdfsFileId(String fdhtId) throws Exception {
        KeyInfo keyInfo = new KeyInfo(DFSConstants.fdhtNamespace, DFSConstants.MY_CLIENT_FILE_ID_KEY_NAME,fdhtId);
        String fdfs_file_id = this.fdhtClient.get(keyInfo);
        this.status = this.fdhtClient.getErrorCode();
        return fdfs_file_id;
    }

    @Override
    public boolean deleteFdhtFileId(String fdhtId) throws Exception {
        KeyInfo keyInfo = new KeyInfo(DFSConstants.fdhtNamespace, DFSConstants.MY_CLIENT_FILE_ID_KEY_NAME, fdhtId);
        this.fdhtClient.delete(keyInfo);
        return (this.status==0);
    }

}
