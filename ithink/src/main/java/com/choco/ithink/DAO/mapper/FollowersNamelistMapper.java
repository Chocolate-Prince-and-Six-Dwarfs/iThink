package com.choco.ithink.DAO.mapper;

import com.choco.ithink.pojo.FollowersNamelist;
import com.choco.ithink.pojo.FollowersNamelistExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FollowersNamelistMapper {
    int countByExample(FollowersNamelistExample example);

    int deleteByExample(FollowersNamelistExample example);

    int deleteByPrimaryKey(Integer followId);

    int insert(FollowersNamelist record);

    int insertSelective(FollowersNamelist record);

    List<FollowersNamelist> selectByExample(FollowersNamelistExample example);

    FollowersNamelist selectByPrimaryKey(Integer followId);

    int updateByExampleSelective(@Param("record") FollowersNamelist record, @Param("example") FollowersNamelistExample example);

    int updateByExample(@Param("record") FollowersNamelist record, @Param("example") FollowersNamelistExample example);

    int updateByPrimaryKeySelective(FollowersNamelist record);

    int updateByPrimaryKey(FollowersNamelist record);
}