package com.choco.ithink.DAO.mapper;

import com.choco.ithink.pojo.AchievementLike;
import com.choco.ithink.pojo.AchievementLikeExample;
import com.choco.ithink.pojo.AchievementLikeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AchievementLikeMapper {
    int countByExample(AchievementLikeExample example);

    int deleteByExample(AchievementLikeExample example);

    int deleteByPrimaryKey(AchievementLikeKey key);

    int insert(AchievementLike record);

    int insertSelective(AchievementLike record);

    List<AchievementLike> selectByExample(AchievementLikeExample example);

    AchievementLike selectByPrimaryKey(AchievementLikeKey key);

    int updateByExampleSelective(@Param("record") AchievementLike record, @Param("example") AchievementLikeExample example);

    int updateByExample(@Param("record") AchievementLike record, @Param("example") AchievementLikeExample example);

    int updateByPrimaryKeySelective(AchievementLike record);

    int updateByPrimaryKey(AchievementLike record);
}