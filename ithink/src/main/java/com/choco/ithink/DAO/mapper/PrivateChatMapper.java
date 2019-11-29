package com.choco.ithink.DAO.mapper;

import com.choco.ithink.pojo.PrivateChat;
import com.choco.ithink.pojo.PrivateChatExample;
import com.choco.ithink.pojo.PrivateChatKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PrivateChatMapper {
    int countByExample(PrivateChatExample example);

    int deleteByExample(PrivateChatExample example);

    int deleteByPrimaryKey(PrivateChatKey key);

    int insert(PrivateChat record);

    int insertSelective(PrivateChat record);

    List<PrivateChat> selectByExample(PrivateChatExample example);

    PrivateChat selectByPrimaryKey(PrivateChatKey key);

    int updateByExampleSelective(@Param("record") PrivateChat record, @Param("example") PrivateChatExample example);

    int updateByExample(@Param("record") PrivateChat record, @Param("example") PrivateChatExample example);

    int updateByPrimaryKeySelective(PrivateChat record);

    int updateByPrimaryKey(PrivateChat record);
}