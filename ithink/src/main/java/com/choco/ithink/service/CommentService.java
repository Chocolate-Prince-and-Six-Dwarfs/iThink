package com.choco.ithink.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.choco.ithink.DAO.mapper.BbsCommentMapper;
import com.choco.ithink.DAO.mapper.CommentLikeMapper;
import com.choco.ithink.DAO.mapper.UserMapper;
import com.choco.ithink.pojo.BbsComment;
import com.choco.ithink.pojo.BbsCommentExample;
import com.choco.ithink.pojo.CommentLike;
import com.choco.ithink.pojo.CommentLikeExample;
import com.choco.ithink.pojo.UserExample;
import com.choco.ithink.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    @Resource
    private BbsCommentMapper bbsCommentMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private CommentLikeMapper commentLikeMapper;

    // param achievementId: 创意实现id
    // do: 查找指定创意实现的评论
    // return: 评论JSONArray数组
    public JSONArray getByAchievementId(Integer achievementId)
    {
        // 查找数据库
        BbsCommentExample bbsCommentExample = new BbsCommentExample();
        bbsCommentExample.createCriteria().andAchievementIdEqualTo(achievementId);
        List<BbsComment> bbsCommentList = bbsCommentMapper.selectByExample(bbsCommentExample);
        return list2JSON(bbsCommentList);
    }


    // param bbsCommentList: 实体列表
    // do： 将列表构建为JSON数组对象
    // return: 构建的json数组对象
    public JSONArray list2JSON(List<BbsComment> bbsCommentList)
    {
        JSONArray jsonArray = new JSONArray();

        // 循环处理实体
        for(int i=0; i<bbsCommentList.size(); ++i)
        {
            // 获取昵称
            // 回复者
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUserIdEqualTo(bbsCommentList.get(i).getFromUid());
            List<User> fromList = userMapper.selectByExample(userExample);
            String fromName = "";
            if(fromList.size()==1)
            {
                fromName = fromList.get(0).getUserName();
            }
            // 被回复者
            userExample.clear();
            userExample.createCriteria().andUserIdEqualTo(bbsCommentList.get(i).getToUid());
            List<User> toList = userMapper.selectByExample(userExample);
            String toName = "";
            if(toList.size()==1)
            {
                toName = toList.get(0).getUserName();
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("commentId", bbsCommentList.get(i).getCommentId());
            jsonObject.put("achievementId", bbsCommentList.get(i).getAchievementId());
            jsonObject.put("content", bbsCommentList.get(i).getCommentContent());
            jsonObject.put("time", bbsCommentList.get(i).getCommentBuildtime());
            jsonObject.put("fromUid", bbsCommentList.get(i).getFromUid());
            jsonObject.put("fromName", fromName);
            jsonObject.put("toUid", bbsCommentList.get(i).getToUid());
            jsonObject.put("toName", toName);
            jsonObject.put("like", bbsCommentList.get(i).getCommentLikenum());
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }


    // param achievementId: 创意实现id
    // param content: 评论内容
    // param fromId: 评论者id
    // param toId: 被评论者id
    // do: 发表评论
    // return:
    // 成功: {status: 1, id: xxx},
    // 失败: {status: 0, id: null}
    public JSONObject doComment(Integer achievementId, String content, Integer fromId, Integer toId)
    {
        JSONObject jsonObject = new JSONObject();
        Integer status = 0;
        Integer id = null;

        // 构建实体
        BbsComment bbsComment = new BbsComment();
        bbsComment.setAchievementId(achievementId);
        bbsComment.setCommentContent(content);
        bbsComment.setFromUid(fromId);
        bbsComment.setCommentBuildtime(new Date());
        bbsComment.setToUid(toId);

        // 添加到表内
        try
        {
            bbsCommentMapper.insertSelective(bbsComment);
            id = bbsComment.getCommentId();
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


    // param id: 评论id
    // do: 获取评论内容
    // return:
    //{
    //      commentId: 评论id,
    //      achievementId: 创意实现id,
    //      content: "评论内容",
    //      time: 评论时间,
    //      fromId: 评论者id,
    //      fromName: 评论者昵称,
    //      toId: 被评论者id,
    //      toName: 被评论者昵称
    //      like: 点赞数
    // }
    // 失败: null
    public JSONObject getById(Integer id)
    {
        BbsCommentExample bbsCommentExample = new BbsCommentExample();
        bbsCommentExample.createCriteria().andCommentIdEqualTo(id);

        List<BbsComment> bbsCommentList = bbsCommentMapper.selectByExample(bbsCommentExample);

        if(bbsCommentList.size()!=1)
        {
            return null;
        }

        return list2JSON(bbsCommentList).getJSONObject(0);
    }


    // param id: 评论id
    // param userId：用户id
    // param type: false|true 踩|赞
    // do: 点赞或点踩（重复请求会取消点赞或点踩）
    // return:
    //  {
    //      like:xxx（点赞数量）, dislike:xxx(点踩数量), status:-1|0|1|-400 用户点赞/点踩状态 没赞没踩|踩|赞|发生错误
    //  }
    public JSONObject like(Integer id, Integer userId, Boolean type)
    {
        JSONObject jsonObject = new JSONObject();
        Integer status = -400;
        Integer like = 0;
        Integer dislike = -400;

        // 查询是否已经点赞或者点踩
        CommentLikeExample commentLikeExample = new CommentLikeExample();
        commentLikeExample.createCriteria().andCommentIdEqualTo(id).andUserIdEqualTo(userId);
        List<CommentLike> commentLikeList = commentLikeMapper.selectByExample(commentLikeExample);


        CommentLike commentLike = new CommentLike();

        try
        {
            switch (commentLikeList.size()) {
                case 0:
                    // 构造关系
                    commentLike.setType(type);
                    commentLike.setCommentId(id);
                    commentLike.setUserId(userId);

                    // 插入关系
                    if (commentLikeMapper.insertSelective(commentLike) == 1) {
                        status = type==true ? 1 : 0;
                    } else {
                        status = -400;
                    }
                    break;
                case 1:
                    // 根据请求类型和已有关系进行操作
                    // 若请求的是已有关系则删除关系
                    if (commentLikeList.get(0).getType() == type) {
                        commentLikeMapper.deleteByExample(commentLikeExample);
                        status = -1;
                    }
                    // 相反关系则更新关系
                    else {
                        commentLike.setType(type);
                        commentLikeMapper.updateByExampleSelective(commentLike, commentLikeExample);
                        status = type==true ? 1 : 0;
                    }
                    break;
                default:
                    status = -400;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            status = -400;
        }
        finally
        {
            commentLikeExample.clear();
            commentLikeExample.createCriteria().andCommentIdEqualTo(id).andTypeEqualTo(true);
            like = commentLikeMapper.countByExample(commentLikeExample);

            commentLikeExample.clear();
            commentLikeExample.createCriteria().andCommentIdEqualTo(id).andTypeEqualTo(false);
            dislike = commentLikeMapper.countByExample(commentLikeExample);
        }

        // 拼接json
        jsonObject.put("like", like);
        jsonObject.put("dislike", dislike);
        jsonObject.put("status", status);

        return jsonObject;
    }

    // param id: 创意实现id
    // param userId：用户id
    // do: 获取点赞数据和点赞状态
    // return:
    // {
    //      like:xxx（点赞数量）, dislike:xxx(点踩数量), status:-1|0|1|-400 用户点赞/点踩状态 没赞没踩|踩|赞|发生错误
    // }
    public JSONObject getLike(Integer id, Integer userId)
    {
        JSONObject jsonObject = new JSONObject();
        Integer status = -1;
        Integer like = 0;
        Integer dislike = -400;

        // 查询是否已经点赞或者点踩
        CommentLikeExample commentLikeExample = new CommentLikeExample();
        commentLikeExample.createCriteria().andCommentIdEqualTo(id).andUserIdEqualTo(userId);
        List<CommentLike> commentLikeList = commentLikeMapper.selectByExample(commentLikeExample);

        try
        {
            switch (commentLikeList.size()) {
                case 0:
                    status = -1;
                    break;
                case 1:
                    // 根据请求类型和已有关系进行操作
                    status = commentLikeList.get(0).getType() == true ? 1 : 0;
                    break;
                default:
                    status = -400;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            status = -400;
        }
        finally
        {
            commentLikeExample.clear();
            commentLikeExample.createCriteria().andCommentIdEqualTo(id).andTypeEqualTo(true);
            like = commentLikeMapper.countByExample(commentLikeExample);

            commentLikeExample.clear();
            commentLikeExample.createCriteria().andCommentIdEqualTo(id).andTypeEqualTo(false);
            dislike = commentLikeMapper.countByExample(commentLikeExample);
        }

        // 拼接json
        jsonObject.put("like", like);
        jsonObject.put("dislike", dislike);
        jsonObject.put("status", status);

        return jsonObject;
    }
}
