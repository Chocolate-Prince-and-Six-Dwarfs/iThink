package com.choco.ithink.service;

import com.alibaba.fastjson.JSONArray;
import com.choco.ithink.DAO.mapper.*;
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
    private BbsTopicMapper bbsTopicMapper;
    @Resource
    private TopicLikeMapper topicLikeMapper;
    @Resource
    private TopicCollectionMapper topicCollectionMapper;
    @Resource
    private BbsAchievementMapper bbsAchievementMapper;
    @Resource
    private AchievementLikeMapper achievementLikeMapper;
    @Resource
    private AchievementCollectionMapper achievementCollectionMapper;
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
            commentLikeExample.createCriteria().andCommentIdEqualTo(bbsCommentList.get(i).getCommentId()).andTimeGreaterThan(date);
            List<CommentLike> commentLikeList = commentLikeMapper.selectByExample(commentLikeExample);
            if(commentLikeList.size()!=0)
            {
                jsonArray.add(commentLikeList);
            }
        }

        return jsonArray;
    }

    // param id: 用户id
    // param date: 上次更新时间
    // do: 返回自上次更新时间之后的主题点赞通知信息
    // return: {topicId: xxx, userId: xxx, type: 点赞, time: }
    public JSONArray getTopicLikeAfter(Integer id, Date date)
    {
        JSONArray jsonArray = new JSONArray();

        // 获取用户的全部创意主题id
        BbsTopicExample bbsTopicExample = new BbsTopicExample();
        bbsTopicExample.createCriteria().andUserIdEqualTo(id);
        List<BbsTopic> bbsTopicList = bbsTopicMapper.selectByExample(bbsTopicExample);
        if(bbsTopicList.size()==0)
        {
            return null;
        }

        // 遍历主题
        for(int i=0; i<bbsTopicList.size(); ++i)
        {
            // 获取主题点赞
            TopicLikeExample topicLikeExample = new TopicLikeExample();
            topicLikeExample.createCriteria().andTopicIdEqualTo(bbsTopicList.get(i).getTopicId()).andTimeGreaterThan(date);
            List<TopicLike> topicLikeList = topicLikeMapper.selectByExample(topicLikeExample);
            if(topicLikeList.size()!=0)
            {
                jsonArray.add(topicLikeList);
            }
        }

        return jsonArray;
    }


    // param id: 用户id
    // param date: 上次更新时间
    // do: 返回自上次更新时间之后的实现点赞通知信息
    // return: {achievementId: xxx, userId: xxx, type: 点赞, time: }
    public JSONArray getAchievementLikeAfter(Integer id, Date date)
    {
        JSONArray jsonArray = new JSONArray();

        // 获取用户的全部创意实现id
        BbsAchievementExample bbsAchievementExample = new BbsAchievementExample();
        bbsAchievementExample.createCriteria().andUserIdEqualTo(id);
        List<BbsAchievement> bbsAchievementList = bbsAchievementMapper.selectByExample(bbsAchievementExample);
        if(bbsAchievementList.size()==0)
        {
            return null;
        }

        // 遍历主题
        for(int i=0; i<bbsAchievementList.size(); ++i)
        {
            // 获取创意实现点赞
            AchievementLikeExample achievementLikeExample = new AchievementLikeExample();
            achievementLikeExample.createCriteria().andAchievementIdEqualTo(bbsAchievementList.get(i).getAchievementId()).andTimeGreaterThan(date);
            List<AchievementLike> achievementLikeList = achievementLikeMapper.selectByExample(achievementLikeExample);
            if(achievementLikeList.size()!=0)
            {
                jsonArray.add(achievementLikeList);
            }
        }

        return jsonArray;
    }


    // param id: 用户id
    // param date: 上次更新时间
    // do: 返回自上次更新时间之后的主题收藏通知信息
    // return: {topicId: xxx, userId: xxx, time: }
    public JSONArray getTopicCollectAfter(Integer id, Date date)
    {
        JSONArray jsonArray = new JSONArray();

        // 获取用户的全部创意主题id
        BbsTopicExample bbsTopicExample = new BbsTopicExample();
        bbsTopicExample.createCriteria().andUserIdEqualTo(id);
        List<BbsTopic> bbsTopicList = bbsTopicMapper.selectByExample(bbsTopicExample);
        if(bbsTopicList.size()==0)
        {
            return null;
        }

        // 遍历主题
        for(int i=0; i<bbsTopicList.size(); ++i)
        {
            // 获取主题收藏
            TopicCollectionExample topicCollectionExample = new TopicCollectionExample();
            topicCollectionExample.createCriteria().andTopicIdEqualTo(bbsTopicList.get(i).getTopicId()).andTimeGreaterThan(date);
            List<TopicCollection> topicCollectionList = topicCollectionMapper.selectByExample(topicCollectionExample);
            if(topicCollectionList.size()!=0)
            {
                jsonArray.add(topicCollectionList);
            }
        }

        return jsonArray;
    }


    // param id: 用户id
    // param date: 上次更新时间
    // do: 返回自上次更新时间之后的实现点赞通知信息
    // return: {achievementId: xxx, userId: xxx, time: }
    public JSONArray getAchievementCollectAfter(Integer id, Date date)
    {
        JSONArray jsonArray = new JSONArray();

        // 获取用户的全部创意实现id
        BbsAchievementExample bbsAchievementExample = new BbsAchievementExample();
        bbsAchievementExample.createCriteria().andUserIdEqualTo(id);
        List<BbsAchievement> bbsAchievementList = bbsAchievementMapper.selectByExample(bbsAchievementExample);
        if(bbsAchievementList.size()==0)
        {
            return null;
        }

        // 遍历主题
        for(int i=0; i<bbsAchievementList.size(); ++i)
        {
            // 获取创意实现收藏
            AchievementCollectionExample achievementCollectionExample = new AchievementCollectionExample();
            achievementCollectionExample.createCriteria().andAchievementIdEqualTo(bbsAchievementList.get(i).getAchievementId()).andTimeGreaterThan(date);
            List<AchievementCollection> achievementCollectionList = achievementCollectionMapper.selectByExample(achievementCollectionExample);
            if(achievementCollectionList.size()!=0)
            {
                jsonArray.add(achievementCollectionList);
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
