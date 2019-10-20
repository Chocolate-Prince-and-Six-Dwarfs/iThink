package com.choco.ithink.DAO.mapper;

import com.choco.ithink.pojo.BbsReply;
import com.choco.ithink.pojo.BbsReplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BbsReplyMapper {
    int countByExample(BbsReplyExample example);

    int deleteByExample(BbsReplyExample example);

    int deleteByPrimaryKey(Integer replyId);

    int insert(BbsReply record);

    int insertSelective(BbsReply record);

    List<BbsReply> selectByExample(BbsReplyExample example);

    BbsReply selectByPrimaryKey(Integer replyId);

    int updateByExampleSelective(@Param("record") BbsReply record, @Param("example") BbsReplyExample example);

    int updateByExample(@Param("record") BbsReply record, @Param("example") BbsReplyExample example);

    int updateByPrimaryKeySelective(BbsReply record);

    int updateByPrimaryKey(BbsReply record);
}