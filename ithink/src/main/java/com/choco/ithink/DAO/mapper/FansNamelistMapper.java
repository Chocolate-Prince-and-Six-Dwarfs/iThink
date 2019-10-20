package com.choco.ithink.DAO.mapper;

import com.choco.ithink.pojo.FansNamelist;
import com.choco.ithink.pojo.FansNamelistExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FansNamelistMapper {
    int countByExample(FansNamelistExample example);

    int deleteByExample(FansNamelistExample example);

    int deleteByPrimaryKey(Integer fansId);

    int insert(FansNamelist record);

    int insertSelective(FansNamelist record);

    List<FansNamelist> selectByExample(FansNamelistExample example);

    FansNamelist selectByPrimaryKey(Integer fansId);

    int updateByExampleSelective(@Param("record") FansNamelist record, @Param("example") FansNamelistExample example);

    int updateByExample(@Param("record") FansNamelist record, @Param("example") FansNamelistExample example);

    int updateByPrimaryKeySelective(FansNamelist record);

    int updateByPrimaryKey(FansNamelist record);
}