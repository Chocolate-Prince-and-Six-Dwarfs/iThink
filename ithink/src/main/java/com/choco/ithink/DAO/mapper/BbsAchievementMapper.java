package com.choco.ithink.DAO.mapper;

import com.choco.ithink.pojo.BbsAchievement;
import com.choco.ithink.pojo.BbsAchievementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BbsAchievementMapper {
    int countByExample(BbsAchievementExample example);

    int deleteByExample(BbsAchievementExample example);

    int deleteByPrimaryKey(Integer achievementId);

    int insert(BbsAchievement record);

    int insertSelective(BbsAchievement record);

    List<BbsAchievement> selectByExample(BbsAchievementExample example);

    BbsAchievement selectByPrimaryKey(Integer achievementId);

    int updateByExampleSelective(@Param("record") BbsAchievement record, @Param("example") BbsAchievementExample example);

    int updateByExample(@Param("record") BbsAchievement record, @Param("example") BbsAchievementExample example);

    int updateByPrimaryKeySelective(BbsAchievement record);

    int updateByPrimaryKey(BbsAchievement record);
}