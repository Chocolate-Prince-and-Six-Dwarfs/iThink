package com.choco.ithink.DAO.mapper;

import com.choco.ithink.pojo.GroupMember;
import com.choco.ithink.pojo.GroupMemberExample;
import com.choco.ithink.pojo.GroupMemberKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupMemberMapper {
    int countByExample(GroupMemberExample example);

    int deleteByExample(GroupMemberExample example);

    int deleteByPrimaryKey(GroupMemberKey key);

    int insert(GroupMember record);

    int insertSelective(GroupMember record);

    List<GroupMember> selectByExample(GroupMemberExample example);

    GroupMember selectByPrimaryKey(GroupMemberKey key);

    int updateByExampleSelective(@Param("record") GroupMember record, @Param("example") GroupMemberExample example);

    int updateByExample(@Param("record") GroupMember record, @Param("example") GroupMemberExample example);

    int updateByPrimaryKeySelective(GroupMember record);

    int updateByPrimaryKey(GroupMember record);
}