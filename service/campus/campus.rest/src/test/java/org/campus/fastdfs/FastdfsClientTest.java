package org.campus.fastdfs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations="classpath*:/META-INF/spring/*.xml")
@ActiveProfiles("dev")
public class FastdfsClientTest {
	
	@Autowired
	private FastdfsClientFactory fastdfsClientFactory;
	
	@Test
	public void testFastdfsClient() throws Exception {
		FastdfsClient fastdfsClient = fastdfsClientFactory.getFastdfsClient();
		URL fileUrl = this.getClass().getResource("/Koala.jpg");
		File file = new File(fileUrl.getPath());
		String fileId = fastdfsClient.upload(file);
		System.out.println("fileId:"+fileId);
		
		assertNotNull(fileId);
		String url = fastdfsClient.getUrl(fileId);
		
		assertNotNull(url);
		System.out.println("url:"+url);
		Map<String,String> meta = new HashMap<String, String>();
		meta.put("fileName", file.getName());
		boolean result = fastdfsClient.setMeta(fileId, meta);
		assertTrue(result);
		Map<String,String> meta2 = fastdfsClient.getMeta(fileId);
		assertNotNull(meta2);
		System.out.println(meta2.get("fileName"));
		
		
		byte[] data = fastdfsClient.downLoad(fileId);
		
		
//		ByteArrayInputStream in = new ByteArrayInputStream(data);    //将b作为输入流；
//		BufferedImage image = ImageIO.read(in);   
//		ImageIO.write(image, "jpg", new File(meta2.get("fileName")));
//		
		result = fastdfsClient.delete(fileId);
		assertTrue(result);
//		fastdfsClient.close();
	}
	

    @Test
    public void testUploadSlave() throws Exception {
		FastdfsClient fastdfsClient = fastdfsClientFactory.getFastdfsClient();
        URL fileUrl = this.getClass().getResource("/Koala.jpg");
        File file = new File(fileUrl.getPath());
        String fileId = fastdfsClient.upload(file);
        System.out.println("fileId:"+fileId);
        assertNotNull(fileId);
        String result = fastdfsClient.uploadSlave(file,fileId,"_200_200","jpg");
        assertNotNull(result);
        System.out.println(result);
        assertTrue(fastdfsClient.delete(fileId));
        assertTrue(fastdfsClient.delete(result));
//        fastdfsClient.close();
    }

    @Test
    public void testUploadMeta() throws Exception {
		FastdfsClient fastdfsClient = fastdfsClientFactory.getFastdfsClient();
        URL fileUrl = this.getClass().getResource("/Koala.jpg");
        File file = new File(fileUrl.getPath());
        Map<String,String> meta = new HashMap<String, String>();
        meta.put("size","200x200");

        String fileId = fastdfsClient.upload(file,null,meta);
        System.out.println("fileId:"+fileId);
        assertNotNull(fileId);

        //set second meta
        meta.put("size","300x300");
        meta.put("nickname","nickname");
        fastdfsClient.setMeta(fileId,meta);

        Map<String,String> a = fastdfsClient.getMeta(fileId);
        assertNotNull(a);
        assertEquals(a.get("size"),"300x300");
        assertEquals(a.get("nickname"),"nickname");
//        fastdfsClient.close();
        assertTrue(fastdfsClient.delete(fileId));
    }

}
