package com.choco.ithink.DAO.mapper;

import com.choco.ithink.pojo.BbsComment;
import com.choco.ithink.pojo.BbsCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BbsCommentMapper {
    int countByExample(BbsCommentExample example);

    int deleteByExample(BbsCommentExample example);

    int deleteByPrimaryKey(Integer commentId);

    int insert(BbsComment record);

    int insertSelective(BbsComment record);

    List<BbsComment> selectByExample(BbsCommentExample example);

    BbsComment selectByPrimaryKey(Integer commentId);

    int updateByExampleSelective(@Param("record") BbsComment record, @Param("example") BbsCommentExample example);

    int updateByExample(@Param("record") BbsComment record, @Param("example") BbsCommentExample example);

    int updateByPrimaryKeySelective(BbsComment record);

    int updateByPrimaryKey(BbsComment record);
}