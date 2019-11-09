package com.choco.ithink.DAO.mapper;

import com.choco.ithink.pojo.TopicCollection;
import com.choco.ithink.pojo.TopicCollectionExample;
import com.choco.ithink.pojo.TopicCollectionKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopicCollectionMapper {
    int countByExample(TopicCollectionExample example);

    int deleteByExample(TopicCollectionExample example);

    int deleteByPrimaryKey(TopicCollectionKey key);

    int insert(TopicCollection record);

    int insertSelective(TopicCollection record);

    List<TopicCollection> selectByExample(TopicCollectionExample example);

    TopicCollection selectByPrimaryKey(TopicCollectionKey key);

    int updateByExampleSelective(@Param("record") TopicCollection record, @Param("example") TopicCollectionExample example);

    int updateByExample(@Param("record") TopicCollection record, @Param("example") TopicCollectionExample example);

    int updateByPrimaryKeySelective(TopicCollection record);

    int updateByPrimaryKey(TopicCollection record);
}