package org.campus.fastdfs.client;

import java.io.IOException;
import java.util.List;

import org.campus.fastdfs.data.GroupInfo;
import org.campus.fastdfs.data.Result;
import org.campus.fastdfs.data.StorageInfo;
import org.campus.fastdfs.data.UploadStorage;

public interface TrackerClient {

	public Result<UploadStorage> getUploadStorage() throws IOException;
	public Result<String> getUpdateStorageAddr(String group,String fileName) throws IOException;
	public Result<String> getDownloadStorageAddr(String group,String fileName) throws IOException;
	public Result<List<GroupInfo>> getGroupInfos() throws IOException;
	public Result<List<StorageInfo>> getStorageInfos(String group) throws IOException;
	public void close() throws IOException;
	
}
