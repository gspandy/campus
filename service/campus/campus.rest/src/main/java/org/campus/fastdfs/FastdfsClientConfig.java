package org.campus.fastdfs;

import java.util.ArrayList;
import java.util.List;

import javax.naming.ConfigurationException;
import org.campus.config.SystemConfig;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;

public class FastdfsClientConfig {
	
	public static final int DEFAULT_CONNECT_TIMEOUT = 5; // second
	public static final int DEFAULT_NETWORK_TIMEOUT = 30; // second
	
	private int connectTimeout = DEFAULT_CONNECT_TIMEOUT * 1000;
	private int networkTimeout = DEFAULT_NETWORK_TIMEOUT * 1000;
	private List<String> trackerAddrs = new ArrayList<String>();
	
	public FastdfsClientConfig() throws ConfigurationException{
		super();
		this.connectTimeout = SystemConfig.getInt("fdfs.connect_timeout", DEFAULT_CONNECT_TIMEOUT)*1000;
		this.networkTimeout =  SystemConfig.getInt("fdfs.network_timeout", DEFAULT_NETWORK_TIMEOUT)*1000;
		List<Object> trackerServers = SystemConfig.getList("fdfs.tracker_server");
		if(trackerServers.isEmpty()) {
			throw new ConfigurationException("traker server is empty!");
		}
		for(Object trackerServer:trackerServers){
			trackerAddrs.add((String)trackerServer);
		}
	}
	
	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getNetworkTimeout() {
		return networkTimeout;
	}

	public void setNetworkTimeout(int networkTimeout) {
		this.networkTimeout = networkTimeout;
	}

	public List<String> getTrackerAddrs() {
		return trackerAddrs;
	}

	public void setTrackerAddrs(List<String> trackerAddrs) {
		this.trackerAddrs = trackerAddrs;
	}

	public GenericKeyedObjectPoolConfig getTrackerClientPoolConfig(){
		GenericKeyedObjectPoolConfig poolConfig = new GenericKeyedObjectPoolConfig();
//		poolConfig.setMaxIdlePerKey(maxIdlePerKey);
//		poolConfig.setMaxTotal(maxTotal);
//		poolConfig.setMaxTotalPerKey(maxTotalPerKey);
//		poolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
//		poolConfig.setma
		
		return poolConfig;
	}
	

	public GenericKeyedObjectPoolConfig getStorageClientPoolConfig(){
		GenericKeyedObjectPoolConfig poolConfig = new GenericKeyedObjectPoolConfig();
		return poolConfig;
	}

}
