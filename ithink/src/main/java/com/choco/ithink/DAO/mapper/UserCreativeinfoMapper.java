package com.choco.ithink.DAO.mapper;

import com.choco.ithink.pojo.UserCreativeinfo;
import com.choco.ithink.pojo.UserCreativeinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserCreativeinfoMapper {
    int countByExample(UserCreativeinfoExample example);

    int deleteByExample(UserCreativeinfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserCreativeinfo record);

    int insertSelective(UserCreativeinfo record);

    List<UserCreativeinfo> selectByExample(UserCreativeinfoExample example);

    UserCreativeinfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserCreativeinfo record, @Param("example") UserCreativeinfoExample example);

    int updateByExample(@Param("record") UserCreativeinfo record, @Param("example") UserCreativeinfoExample example);

    int updateByPrimaryKeySelective(UserCreativeinfo record);

    int updateByPrimaryKey(UserCreativeinfo record);
}