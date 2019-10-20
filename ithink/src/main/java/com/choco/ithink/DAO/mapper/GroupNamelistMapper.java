package com.choco.ithink.DAO.mapper;

import com.choco.ithink.pojo.GroupNamelist;
import com.choco.ithink.pojo.GroupNamelistExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupNamelistMapper {
    int countByExample(GroupNamelistExample example);

    int deleteByExample(GroupNamelistExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GroupNamelist record);

    int insertSelective(GroupNamelist record);

    List<GroupNamelist> selectByExample(GroupNamelistExample example);

    GroupNamelist selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GroupNamelist record, @Param("example") GroupNamelistExample example);

    int updateByExample(@Param("record") GroupNamelist record, @Param("example") GroupNamelistExample example);

    int updateByPrimaryKeySelective(GroupNamelist record);

    int updateByPrimaryKey(GroupNamelist record);
}