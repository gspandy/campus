package org.campus.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class RedisCache {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Object getValue(String key, Object value) {
        Object cacheValue = checkCahce(key);
        if (cacheValue != null) {
            return cacheValue;
        } else {
            cache(key, value);
            return value;
        }
    }

    public void cache(final String cacheKey, final Object value) {
        redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            @SuppressWarnings("unchecked")
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<Object> serializer = (RedisSerializer<Object>) redisTemplate.getDefaultSerializer();
                byte[] key = redisTemplate.getStringSerializer().serialize(cacheKey);
                connection.set(key, serializer.serialize(value));
                connection.expire(key, 600);
                return true;
            }
        });
    }

    public Object checkCahce(final String cacheKey) {
        Object resultValue = redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            @SuppressWarnings("unchecked")
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] key = redisTemplate.getStringSerializer().serialize(cacheKey);
                byte[] cacheValue = connection.get(key);
                if (cacheValue != null) {
                    RedisSerializer<Object> serializer = (RedisSerializer<Object>) redisTemplate.getDefaultSerializer();
                    return serializer.deserialize(cacheValue);
                }
                return null;
            }
        });
        return resultValue;
    }
}
