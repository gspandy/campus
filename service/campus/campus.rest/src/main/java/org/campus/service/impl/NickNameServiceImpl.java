package org.campus.service.impl;

import java.util.Random;

import org.campus.cache.RedisCache;
import org.campus.core.exception.CampusException;
import org.campus.model.NickName;
import org.campus.model.enums.DisplayModel;
import org.campus.repository.NickNameMapper;
import org.campus.service.NickNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NickNameServiceImpl implements NickNameService {

    @Autowired
    private NickNameMapper nickNameMapper;

    @Autowired
    private RedisCache redis;
    
    private static final String PREFIX="NICKNAME_";
    
    private static Random random = new Random(1);
    
    @Override
    public void insert(NickName nickName) {
        nickNameMapper.insert(nickName);
    }

    @Override
    public void deleteAll() {
        nickNameMapper.deleteAll();
    }

    @Override
    public String findRandomNickName(DisplayModel model,String sessionId) {
    	//太阳键,不予理睬
    	if(DisplayModel.SUN==model) return null;
    	
    	String key = PREFIX + sessionId;
    	String name = (String) redis.checkCahce(key);
    	 
    	//缓存已存在，直接使用;
    	if(name == null){ 
	        //随机获取昵称，并缓存
	    	int count = nickNameMapper.count();
	        
	        int uid = random.nextInt(count);
	        
	        NickName nickName = nickNameMapper.selectByPrimaryKey(uid);
	        if (nickName == null) {
	        	throw new CampusException(100204,"获取昵称出错.");
	        }
	        
	        name = nickName.getNickname();
	        redis.cache(key, name);
    	}
    	return name;
    }

}
