package com.choco.ithink.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.choco.ithink.DAO.mapper.BbsReplyMapper;
import com.choco.ithink.DAO.mapper.UserMapper;
import com.choco.ithink.pojo.BbsReply;
import com.choco.ithink.pojo.BbsReplyExample;
import com.choco.ithink.pojo.User;
import com.choco.ithink.pojo.UserExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReplyService {
    @Resource
    BbsReplyMapper bbsReplyMapper;
    @Resource
    UserMapper userMapper;

    // param achievementId: 创意实现id
    // do: 查找指定创意实现的活肤
    // return: 回复JSONArray数组
    public JSONArray getByAchievementId(Integer achievementId)
    {
        // 查找数据库
        BbsReplyExample bbsReplyExample = new BbsReplyExample();
        bbsReplyExample.createCriteria().andCommentIdEqualTo(achievementId);
        List<BbsReply> bbsTopicList = bbsReplyMapper.selectByExample(bbsReplyExample);
        return list2JSON(bbsTopicList);
    }

    // param bbsReplyList: 实体列表
    // do： 将列表构建为JSON数组对象
    // return: 构建的json数组对象
    public JSONArray list2JSON(List<BbsReply> bbsReplyList)
    {
        JSONArray jsonArray = new JSONArray();

        // 循环处理实体
        for(int i=0; i<bbsReplyList.size(); ++i)
        {
            // 获取昵称
            // 回复者
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUserIdEqualTo(bbsReplyList.get(i).getFromUid());
            List<User> fromList = userMapper.selectByExample(userExample);
            String fromName = "";
            if(fromList.size()==1)
            {
                fromName = fromList.get(0).getUserName();
            }
            // 被回复者
            userExample.clear();
            userExample.createCriteria().andUserIdEqualTo(bbsReplyList.get(i).getToUid());
            List<User> toList = userMapper.selectByExample(userExample);
            String toName = "";
            if(toList.size()==1)
            {
                toName = toList.get(0).getUserName();
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("replyId", bbsReplyList.get(i).getReplyId());
            jsonObject.put("achievementId", bbsReplyList.get(i).getCommentId());
            jsonObject.put("content", bbsReplyList.get(i).getReplyContent());
            jsonObject.put("time", bbsReplyList.get(i).getReplyBuildtime());
            jsonObject.put("fromUid", bbsReplyList.get(i).getFromUid());
            jsonObject.put("fromName", fromName);
            jsonObject.put("toUid", bbsReplyList.get(i).getToUid());
            jsonObject.put("toName", toName);
            jsonObject.put("like", bbsReplyList.get(i).getReplyLikenum());
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }
}
