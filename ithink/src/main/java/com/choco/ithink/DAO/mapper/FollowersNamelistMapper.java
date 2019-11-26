package com.choco.ithink.DAO.mapper;

import com.choco.ithink.pojo.FollowersNamelistExample;
import com.choco.ithink.pojo.FollowersNamelistKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FollowersNamelistMapper {
    int countByExample(FollowersNamelistExample example);

    int deleteByExample(FollowersNamelistExample example);

    int deleteByPrimaryKey(FollowersNamelistKey key);

    int insert(FollowersNamelistKey record);

    int insertSelective(FollowersNamelistKey record);

    List<FollowersNamelistKey> selectByExample(FollowersNamelistExample example);

    int updateByExampleSelective(@Param("record") FollowersNamelistKey record, @Param("example") FollowersNamelistExample example);

    int updateByExample(@Param("record") FollowersNamelistKey record, @Param("example") FollowersNamelistExample example);
}