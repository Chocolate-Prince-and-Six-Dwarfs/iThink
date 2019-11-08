package com.choco.ithink.DAO.mapper;

import com.choco.ithink.pojo.CommentLike;
import com.choco.ithink.pojo.CommentLikeExample;
import com.choco.ithink.pojo.CommentLikeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommentLikeMapper {
    int countByExample(CommentLikeExample example);

    int deleteByExample(CommentLikeExample example);

    int deleteByPrimaryKey(CommentLikeKey key);

    int insert(CommentLike record);

    int insertSelective(CommentLike record);

    List<CommentLike> selectByExample(CommentLikeExample example);

    CommentLike selectByPrimaryKey(CommentLikeKey key);

    int updateByExampleSelective(@Param("record") CommentLike record, @Param("example") CommentLikeExample example);

    int updateByExample(@Param("record") CommentLike record, @Param("example") CommentLikeExample example);

    int updateByPrimaryKeySelective(CommentLike record);

    int updateByPrimaryKey(CommentLike record);
}