package org.campus.fastdfs;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import org.campus.fastdfs.client.TrackerClient;
import org.campus.fastdfs.client.TrackerClientImpl;
import org.campus.fastdfs.data.GroupInfo;
import org.campus.fastdfs.data.Result;
import org.campus.fastdfs.data.StorageInfo;
import org.campus.fastdfs.data.UploadStorage;
import org.junit.Test;

public class TrackerClientTest {

	@Test
	public void testGetUploadStorageAddr() throws NumberFormatException, UnknownHostException, IOException {
		TrackerClient trackerClient = new TrackerClientImpl("172.16.129.26:22122");
		Result<UploadStorage> result = trackerClient.getUploadStorage();
		assertEquals(0, result.getCode());
		assertEquals("172.16.129.26:23000",result.getData().getAddress());
		trackerClient.close();
	}
	
	@Test
	public void testGetDownloadStorageAddr() throws IOException {
		TrackerClient trackerClient = new TrackerClientImpl("172.16.129.26:22122");
		Result<String> result = trackerClient.getDownloadStorageAddr("group1","M00/00/00/Cn2wilM00puAa0xSAANVQ4eIxAM143.jpg");
		assertEquals(0, result.getCode());
		assertEquals("172.16.129.26:23000",result.getData());
		trackerClient.close();
	}
	
	@Test
	public void testGetUpdateStorageAddr() throws IOException {
		TrackerClient trackerClient = new TrackerClientImpl("172.16.129.26:22122");
		Result<String> result = trackerClient.getUpdateStorageAddr("group1","M00/00/00/Cn2wilM00puAa0xSAANVQ4eIxAM143.jpg");
		assertEquals(0, result.getCode());
		assertEquals("172.16.129.26:23000",result.getData());
		trackerClient.close();
	}
	
	@Test
	public void testGetGroupInfos() throws NumberFormatException, UnknownHostException, IOException{
		TrackerClient trackerClient = new TrackerClientImpl("172.16.129.26:22122");
		Result<List<GroupInfo>> groupInfos = trackerClient.getGroupInfos();
		assertNotNull(groupInfos);
		trackerClient.close();
	}
	
	@Test
	public void testGetStorageInfo() throws Exception {
		TrackerClient trackerClient = new TrackerClientImpl("172.16.129.26:22122");
		Result<List<StorageInfo>>  result2= trackerClient.getStorageInfos("group1");
		assertNotNull(result2);
		trackerClient.close();
	}
}
