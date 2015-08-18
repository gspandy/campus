package org.campus.repository;

import org.apache.ibatis.annotations.Param;
import org.campus.model.FavoriteFreshNews;
import org.campus.model.UserFavorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFavoriteMapper {
	
    int deleteByPrimaryKey(String uid);

    int insert(UserFavorite record);

    int insertSelective(UserFavorite record);

    UserFavorite selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(UserFavorite record);

    int updateByPrimaryKey(UserFavorite record);
    
    Page<FavoriteFreshNews> selectByUserId(@Param("userId") String userId, Pageable pageable);
    
}