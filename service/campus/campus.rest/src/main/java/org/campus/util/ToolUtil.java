package org.campus.util;

import java.util.Random;
import java.util.UUID;

public class ToolUtil {

	private static SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1);
	
	private static Random random = new Random(100000);
	
	/**
	 * ID生成器
	 * @return
	 */
	public static synchronized long getId() {
		return idGenerator.nextId();
	}
	
	/**
	 * 获取UUID
	 * @return
	 */
    public static String getUUid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * 生成6位随机数
     * @return
     */
    public static String getSmsCheckColde(){
    	int code = random.nextInt(999999);
    	return Integer.toString(code);
    }
    
}
