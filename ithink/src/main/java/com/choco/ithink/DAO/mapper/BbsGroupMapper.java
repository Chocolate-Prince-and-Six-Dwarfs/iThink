package com.choco.ithink.DAO.mapper;

import com.choco.ithink.pojo.BbsGroup;
import com.choco.ithink.pojo.BbsGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BbsGroupMapper {
    int countByExample(BbsGroupExample example);

    int deleteByExample(BbsGroupExample example);

    int deleteByPrimaryKey(Integer groupId);

    int insert(BbsGroup record);

    int insertSelective(BbsGroup record);

    List<BbsGroup> selectByExample(BbsGroupExample example);

    BbsGroup selectByPrimaryKey(Integer groupId);

    int updateByExampleSelective(@Param("record") BbsGroup record, @Param("example") BbsGroupExample example);

    int updateByExample(@Param("record") BbsGroup record, @Param("example") BbsGroupExample example);

    int updateByPrimaryKeySelective(BbsGroup record);

    int updateByPrimaryKey(BbsGroup record);
}