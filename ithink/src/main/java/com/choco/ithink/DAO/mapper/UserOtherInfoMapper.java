package com.choco.ithink.DAO.mapper;

import com.choco.ithink.pojo.UserOtherInfo;
import com.choco.ithink.pojo.UserOtherInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserOtherInfoMapper {
    int countByExample(UserOtherInfoExample example);

    int deleteByExample(UserOtherInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserOtherInfo record);

    int insertSelective(UserOtherInfo record);

    List<UserOtherInfo> selectByExample(UserOtherInfoExample example);

    UserOtherInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserOtherInfo record, @Param("example") UserOtherInfoExample example);

    int updateByExample(@Param("record") UserOtherInfo record, @Param("example") UserOtherInfoExample example);

    int updateByPrimaryKeySelective(UserOtherInfo record);

    int updateByPrimaryKey(UserOtherInfo record);
}