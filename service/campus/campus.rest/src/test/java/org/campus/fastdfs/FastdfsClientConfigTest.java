package org.campus.fastdfs;

import static org.junit.Assert.*;

import javax.naming.ConfigurationException;

import org.junit.Test;

public class FastdfsClientConfigTest {

	@Test
	public void testFastdfsClientConfigString() throws ConfigurationException {
		FastdfsClientConfig fastdfsClientConfig = new FastdfsClientConfig();
		assertEquals(5*1000, fastdfsClientConfig.getConnectTimeout());
		assertEquals(30*1000,fastdfsClientConfig.getNetworkTimeout());
	}

}
