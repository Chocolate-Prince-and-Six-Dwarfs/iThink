package com.choco.ithink.DAO.mapper;

import com.choco.ithink.pojo.CreativeCapsule;
import com.choco.ithink.pojo.CreativeCapsuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CreativeCapsuleMapper {
    int countByExample(CreativeCapsuleExample example);

    int deleteByExample(CreativeCapsuleExample example);

    int deleteByPrimaryKey(Integer creativeId);

    int insert(CreativeCapsule record);

    int insertSelective(CreativeCapsule record);

    List<CreativeCapsule> selectByExample(CreativeCapsuleExample example);

    CreativeCapsule selectByPrimaryKey(Integer creativeId);

    int updateByExampleSelective(@Param("record") CreativeCapsule record, @Param("example") CreativeCapsuleExample example);

    int updateByExample(@Param("record") CreativeCapsule record, @Param("example") CreativeCapsuleExample example);

    int updateByPrimaryKeySelective(CreativeCapsule record);

    int updateByPrimaryKey(CreativeCapsule record);
}