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
import java.util.Date;
import java.util.List;

@Service
public class ReplyService {
    @Resource
    BbsReplyMapper bbsReplyMapper;
    @Resource
    UserMapper userMapper;

    // param commentId: 评论id
    // do: 查找指定评论的回复
    // return: 回复JSONArray数组
    public JSONArray getByCommentId(Integer commentId)
    {
        // 查找数据库
        BbsReplyExample bbsReplyExample = new BbsReplyExample();
        bbsReplyExample.createCriteria().andCommentIdEqualTo(commentId);
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
            jsonObject.put("commentId", bbsReplyList.get(i).getCommentId());
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


    // param commentId: 创意评论id
    // param content: 回复内容
    // param fromId: 回复者id
    // param toId: 被回复者id
    // do: 发表回复
    // return:
    // 成功: {status: 1, id: xxx},
    // 失败: {status: 0, id: null}
    public JSONObject doReply(Integer commentId, String content, Integer fromId, Integer toId)
    {
        JSONObject jsonObject = new JSONObject();
        Integer status = 0;
        Integer id = null;

        // 构建实体
        BbsReply bbsReply = new BbsReply();
        bbsReply.setCommentId(commentId);
        bbsReply.setReplyContent(content);
        bbsReply.setFromUid(fromId);
        bbsReply.setReplyBuildtime(new Date());
        bbsReply.setToUid(toId);

        // 添加到表内
        try
        {
            bbsReplyMapper.insertSelective(bbsReply);
            id = bbsReply.getReplyId();
            status = 1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status = 0;
        }


        // 拼接字符串
        jsonObject.put("status", status);
        jsonObject.put("id", id);
        return jsonObject;
    }


    // param id: 回复id
    // do: 获取回复内容
    // return:
    // 成功:
    // {
    //      replyId: 0,(回复id)
    //      commentId: 0,(创意实现id)
    //      content: "回复内容",
    //      time: "回复时间",
    //      fromId: 0,(回复者id)
    //      fromName: "回复者昵称"
    //      toId: 0,(被回复者id)
    //      toName: "被回复者昵称"
    // }
    // 失败: null
    public JSONObject getById(Integer id)
    {
        BbsReplyExample bbsReplyExample = new BbsReplyExample();
        bbsReplyExample.createCriteria().andReplyIdEqualTo(id);

        List<BbsReply> bbsReplyList = bbsReplyMapper.selectByExample(bbsReplyExample);

        if(bbsReplyList.size()!=1)
        {
            return null;
        }

        return list2JSON(bbsReplyList).getJSONObject(0);
    }
}
