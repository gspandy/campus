package org.campus.util;

import java.util.UUID;

public class ToolUtil {

	private static SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1);
	
	/**
	 * ID生成器
	 * @return
	 */
	public static synchronized long getId() {
		return idGenerator.nextId();
	}
	
    public static String getUUid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
