package com.choco.ithink.DAO.mapper;

import com.choco.ithink.pojo.UpdateTime;
import com.choco.ithink.pojo.UpdateTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpdateTimeMapper {
    int countByExample(UpdateTimeExample example);

    int deleteByExample(UpdateTimeExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(UpdateTime record);

    int insertSelective(UpdateTime record);

    List<UpdateTime> selectByExample(UpdateTimeExample example);

    UpdateTime selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") UpdateTime record, @Param("example") UpdateTimeExample example);

    int updateByExample(@Param("record") UpdateTime record, @Param("example") UpdateTimeExample example);

    int updateByPrimaryKeySelective(UpdateTime record);

    int updateByPrimaryKey(UpdateTime record);
}