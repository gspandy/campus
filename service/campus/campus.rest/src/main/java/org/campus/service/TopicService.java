package org.campus.service;

import org.campus.model.FreshNews;
import org.campus.model.enums.TopicType;
import org.campus.repository.FreshNewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
	
	@Autowired
	FreshNewsMapper freshMapper;
	
	/**
	 * 查询帖子列表
	 * 
	 * @param topicType 话题类型
	 * @param isShield  是否隐藏标识:隐藏贴只提供注册用户浏览
	 * @param pageable  分页参数
	 * @return
	 */
	private Page<FreshNews> getTopicByTypeAndShield(String userId,TopicType topicType,int isShield,Pageable pageable){
		//根据不同的话题类型分发不同的SQL查询
		switch (topicType){
		case HOT:
			return freshMapper.selectHotPosts(pageable);
		case ATTENTION:
			return freshMapper.selectAttentionPosts(userId, pageable);
		case RELAXATION:
		case NOVELTY:
		case PRIVACY:
		case SPEECH:
			return freshMapper.selectByNewTypeAndShield(topicType.getCode(), isShield, pageable);
		default:
			return null;
		}
	}
	
	/**
	 * 为普通用户查询帖子列表
	 * 
	 * @param topicType 话题类型
	 * @param pageable
	 * @return
	 */
	public Page<FreshNews> getPostsForAnonymous(TopicType topicType,Pageable pageable){
		return this.getTopicByTypeAndShield(null,topicType, FreshNews.VIEW_ANONYMOUSE, pageable);
	}
	
	/**
	 * 为登录用户查询帖子列表
	 * 
	 * @param userId 登录用户编号
	 * @param topicType 话题类型
	 * @param pageable
	 * @return
	 */
	public Page<FreshNews> getPostsForRegister(String userId,TopicType topicType,Pageable pageable){
		return this.getTopicByTypeAndShield(userId,topicType, FreshNews.VIEW_REGISTER, pageable);
	}
	
	/**
	 * 帖子详情
	 * @param postsId 帖子编号
	 * @return
	 */
	public FreshNews getPostsDetail(String postsId){
		return this.freshMapper.selectByPrimaryKey(postsId);
	}
}