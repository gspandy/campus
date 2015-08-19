package org.campus.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.campus.model.PostsCheck;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsCheckMapper {

    int insert(PostsCheck record);

    int insertSelective(PostsCheck record);

    List<PostsCheck> findIntradayByUserId(@Param("userId") String userId, @Param("date") Date date);

    int findIntradayCountByUserId(@Param("userId") String userId, @Param("date") Date date);

}