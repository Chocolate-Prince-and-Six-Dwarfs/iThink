package com.choco.ithink.DAO.mapper;

import com.choco.ithink.pojo.AchievementCollection;
import com.choco.ithink.pojo.AchievementCollectionExample;
import com.choco.ithink.pojo.AchievementCollectionKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AchievementCollectionMapper {
    int countByExample(AchievementCollectionExample example);

    int deleteByExample(AchievementCollectionExample example);

    int deleteByPrimaryKey(AchievementCollectionKey key);

    int insert(AchievementCollection record);

    int insertSelective(AchievementCollection record);

    List<AchievementCollection> selectByExample(AchievementCollectionExample example);

    AchievementCollection selectByPrimaryKey(AchievementCollectionKey key);

    int updateByExampleSelective(@Param("record") AchievementCollection record, @Param("example") AchievementCollectionExample example);

    int updateByExample(@Param("record") AchievementCollection record, @Param("example") AchievementCollectionExample example);

    int updateByPrimaryKeySelective(AchievementCollection record);

    int updateByPrimaryKey(AchievementCollection record);
}