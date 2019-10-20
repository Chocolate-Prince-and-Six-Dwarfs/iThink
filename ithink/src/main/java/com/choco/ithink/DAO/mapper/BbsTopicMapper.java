package com.choco.ithink.DAO.mapper;

import com.choco.ithink.pojo.BbsTopic;
import com.choco.ithink.pojo.BbsTopicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BbsTopicMapper {
    int countByExample(BbsTopicExample example);

    int deleteByExample(BbsTopicExample example);

    int deleteByPrimaryKey(Integer topicId);

    int insert(BbsTopic record);

    int insertSelective(BbsTopic record);

    List<BbsTopic> selectByExample(BbsTopicExample example);

    BbsTopic selectByPrimaryKey(Integer topicId);

    int updateByExampleSelective(@Param("record") BbsTopic record, @Param("example") BbsTopicExample example);

    int updateByExample(@Param("record") BbsTopic record, @Param("example") BbsTopicExample example);

    int updateByPrimaryKeySelective(BbsTopic record);

    int updateByPrimaryKey(BbsTopic record);
}