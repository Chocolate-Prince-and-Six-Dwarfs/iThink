package com.choco.ithink.DAO.mapper;

import com.choco.ithink.pojo.TopicLike;
import com.choco.ithink.pojo.TopicLikeExample;
import com.choco.ithink.pojo.TopicLikeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopicLikeMapper {
    int countByExample(TopicLikeExample example);

    int deleteByExample(TopicLikeExample example);

    int deleteByPrimaryKey(TopicLikeKey key);

    int insert(TopicLike record);

    int insertSelective(TopicLike record);

    List<TopicLike> selectByExample(TopicLikeExample example);

    TopicLike selectByPrimaryKey(TopicLikeKey key);

    int updateByExampleSelective(@Param("record") TopicLike record, @Param("example") TopicLikeExample example);

    int updateByExample(@Param("record") TopicLike record, @Param("example") TopicLikeExample example);

    int updateByPrimaryKeySelective(TopicLike record);

    int updateByPrimaryKey(TopicLike record);
}