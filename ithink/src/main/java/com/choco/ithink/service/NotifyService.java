package com.choco.ithink.service;

import com.alibaba.fastjson.JSONArray;
import com.choco.ithink.DAO.mapper.BbsCommentMapper;
import com.choco.ithink.DAO.mapper.CommentLikeMapper;
import com.choco.ithink.DAO.mapper.UpdateTimeMapper;
import com.choco.ithink.pojo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class NotifyService {
    @Resource
    private BbsCommentMapper bbsCommentMapper;
    @Resource
    private CommentLikeMapper commentLikeMapper;
    @Resource
    private UpdateTimeMapper updateTimeMapper;

    // param id: 用户id
    // param date: 上次更新时间
    // do: 返回自上次更新时间之后的评论点赞通知信息
    // return: {commentId: xxx, userId: xxx, type: 点赞}
    public JSONArray getCommentLikeAfter(Integer id, Date date)
    {
        JSONArray jsonArray = new JSONArray();

        // 获取用户的全部评论id
        BbsCommentExample bbsCommentExample = new BbsCommentExample();
        bbsCommentExample.createCriteria().andFromUidEqualTo(id);
        List<BbsComment> bbsCommentList = bbsCommentMapper.selectByExample(bbsCommentExample);
        if(bbsCommentList.size()==0)
        {
            return null;
        }

        // 遍历评论
        for(int i=0; i<bbsCommentList.size(); ++i)
        {
            // 获取评论点赞
            CommentLikeExample commentLikeExample = new CommentLikeExample();
            commentLikeExample.createCriteria().andCommentIdEqualTo(bbsCommentList.get(i).getCommentId()).andTimeGreaterThanOrEqualTo(date);
            List<CommentLike> commentLikeList = commentLikeMapper.selectByExample(commentLikeExample);
            if(commentLikeList.size()!=0)
            {
                jsonArray.add(commentLikeList);
            }
        }

        return jsonArray;
    }


    // param id: 用户id
    // do: 返回上次更新时间
    // return: 上次更新时间
    public Date lastUpdateTime(Integer id)
    {
        UpdateTimeExample updateTimeExample = new UpdateTimeExample();
        updateTimeExample.createCriteria().andUserIdEqualTo(id);

        return updateTimeMapper.selectByExample(updateTimeExample).get(0).getTime();
    }


    // param id: 用户id
    // do: 刷新更新时间
    public void flushUpdateTime(Integer id)
    {
        UpdateTimeExample updateTimeExample = new UpdateTimeExample();
        updateTimeExample.createCriteria().andUserIdEqualTo(id);

        UpdateTime updateTime = new UpdateTime();
        updateTime.setUserId(id);
        updateTime.setTime(new Date());

        updateTimeMapper.updateByExampleSelective(updateTime, updateTimeExample);
    }
}
