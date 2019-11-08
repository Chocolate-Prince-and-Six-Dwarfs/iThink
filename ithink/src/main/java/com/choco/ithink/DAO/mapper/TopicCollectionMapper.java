package com.choco.ithink.DAO.mapper;

import com.choco.ithink.pojo.TopicCollectionExample;
import com.choco.ithink.pojo.TopicCollectionKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopicCollectionMapper {
    int countByExample(TopicCollectionExample example);

    int deleteByExample(TopicCollectionExample example);

    int deleteByPrimaryKey(TopicCollectionKey key);

    int insert(TopicCollectionKey record);

    int insertSelective(TopicCollectionKey record);

    List<TopicCollectionKey> selectByExample(TopicCollectionExample example);

    int updateByExampleSelective(@Param("record") TopicCollectionKey record, @Param("example") TopicCollectionExample example);

    int updateByExample(@Param("record") TopicCollectionKey record, @Param("example") TopicCollectionExample example);
}