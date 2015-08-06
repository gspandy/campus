package org.campus.cache;

import org.campus.BaseTest;
import org.campus.cache.RedisCache;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisCacheTest extends BaseTest {

    @Autowired
    private RedisCache cache;

    @Test
    public void testGetValue() {
        for (int i = 0; i < 10; i++) {
            cache.getValue("test_redisTemplate", "Test");
        }
    }

}
