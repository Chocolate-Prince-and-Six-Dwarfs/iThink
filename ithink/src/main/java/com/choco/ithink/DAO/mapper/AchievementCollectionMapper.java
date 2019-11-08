package com.choco.ithink.DAO.mapper;

import com.choco.ithink.pojo.AchievementCollectionExample;
import com.choco.ithink.pojo.AchievementCollectionKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AchievementCollectionMapper {
    int countByExample(AchievementCollectionExample example);

    int deleteByExample(AchievementCollectionExample example);

    int deleteByPrimaryKey(AchievementCollectionKey key);

    int insert(AchievementCollectionKey record);

    int insertSelective(AchievementCollectionKey record);

    List<AchievementCollectionKey> selectByExample(AchievementCollectionExample example);

    int updateByExampleSelective(@Param("record") AchievementCollectionKey record, @Param("example") AchievementCollectionExample example);

    int updateByExample(@Param("record") AchievementCollectionKey record, @Param("example") AchievementCollectionExample example);
}