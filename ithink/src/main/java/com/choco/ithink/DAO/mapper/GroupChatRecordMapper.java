package com.choco.ithink.DAO.mapper;

import com.choco.ithink.pojo.GroupChatRecord;
import com.choco.ithink.pojo.GroupChatRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupChatRecordMapper {
    int countByExample(GroupChatRecordExample example);

    int deleteByExample(GroupChatRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GroupChatRecord record);

    int insertSelective(GroupChatRecord record);

    List<GroupChatRecord> selectByExample(GroupChatRecordExample example);

    GroupChatRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GroupChatRecord record, @Param("example") GroupChatRecordExample example);

    int updateByExample(@Param("record") GroupChatRecord record, @Param("example") GroupChatRecordExample example);

    int updateByPrimaryKeySelective(GroupChatRecord record);

    int updateByPrimaryKey(GroupChatRecord record);
}