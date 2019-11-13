package com.choco.ithink.DAO.mapper;

import com.choco.ithink.pojo.ChatUpdateTime;
import com.choco.ithink.pojo.ChatUpdateTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChatUpdateTimeMapper {
    int countByExample(ChatUpdateTimeExample example);

    int deleteByExample(ChatUpdateTimeExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(ChatUpdateTime record);

    int insertSelective(ChatUpdateTime record);

    List<ChatUpdateTime> selectByExample(ChatUpdateTimeExample example);

    ChatUpdateTime selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") ChatUpdateTime record, @Param("example") ChatUpdateTimeExample example);

    int updateByExample(@Param("record") ChatUpdateTime record, @Param("example") ChatUpdateTimeExample example);

    int updateByPrimaryKeySelective(ChatUpdateTime record);

    int updateByPrimaryKey(ChatUpdateTime record);
}