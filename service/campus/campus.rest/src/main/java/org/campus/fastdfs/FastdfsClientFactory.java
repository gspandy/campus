package org.campus.fastdfs;

import java.util.List;

import javax.naming.ConfigurationException;

import org.campus.csource.FastIdMapping;
import org.campus.csource.fastidmysql.FastIdMapMysqlImpl;
import org.campus.fastdfs.client.StorageClient;
import org.campus.fastdfs.client.StorageClientFactory;
import org.campus.fastdfs.client.TrackerClient;
import org.campus.fastdfs.client.TrackerClientFactory;
import org.campus.repository.IdmappingMapper;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FastdfsClientFactory {

    private static Logger logger = LoggerFactory.getLogger(FastdfsClientFactory.class);
	
	private static volatile FastdfsClient fastdfsClient;
    private static FastdfsClientConfig config = null;
    
    private static FastIdMapping fastIdMapping ;
    
    @Autowired
    private IdmappingMapper idmappingRepository;

    public FastdfsClientFactory() {
    }
    public FastdfsClient getFastdfsClient() {
        if (fastdfsClient == null) {
            synchronized (FastdfsClient.class) {
                if (fastdfsClient == null) {
                    try {
//                        org.csource.fastdht.ClientGlobal.init();
                        config = new FastdfsClientConfig();
                    } catch (ConfigurationException e) {
                        logger.warn("Load fastdfs config failed.",e);
                    }
                    int connectTimeout = config.getConnectTimeout();
                    int networkTimeout = config.getNetworkTimeout();
                    TrackerClientFactory trackerClientFactory = new TrackerClientFactory(connectTimeout, networkTimeout);
                    StorageClientFactory storageClientFactory = new StorageClientFactory(connectTimeout, networkTimeout);
                    GenericKeyedObjectPoolConfig trackerClientPoolConfig = config.getTrackerClientPoolConfig();
                    GenericKeyedObjectPoolConfig storageClientPoolConfig = config.getStorageClientPoolConfig();
                    GenericKeyedObjectPool<String,TrackerClient> trackerClientPool = new GenericKeyedObjectPool<String,TrackerClient>(trackerClientFactory, trackerClientPoolConfig);
                    GenericKeyedObjectPool<String,StorageClient> storageClientPool = new GenericKeyedObjectPool<String,StorageClient>(storageClientFactory, storageClientPoolConfig);
                    List<String> trackerAddrs = config.getTrackerAddrs();
                    
                    if(fastIdMapping ==null){
                        fastIdMapping = new FastIdMapMysqlImpl(idmappingRepository);
                    }
                    
                    fastdfsClient = new FastdfsClientImpl(trackerAddrs,trackerClientPool,storageClientPool,fastIdMapping);
                }
            }
        }
        return fastdfsClient;
    }
    
    @Autowired
    @Qualifier("mysqlImpl")
    public void setFastIdMapping(FastIdMapping fastIdMapping) {
        FastdfsClientFactory.fastIdMapping = fastIdMapping;
    }
}
