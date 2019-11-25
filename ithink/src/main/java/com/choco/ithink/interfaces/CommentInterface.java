package com.choco.ithink.interfaces;

import com.alibaba.fastjson.JSONObject;

public interface CommentInterface {
    // 请求地址: comment/do
    // param achievementId: 创意实现id
    // param content: 评论内容
    // param fromId: 评论者id
    // param toId: 被评论者id
    // do: 发表评论
    // return:
    // 成功: {status: 1, id: xxx},
    // 失败: {status: 0, id: null}
    JSONObject doReply(Integer achievementId, String content, Integer fromId, Integer toId);

    // 请求地址: comment/getById
    // param id: 评论id
    // do: 获取评论内容
    // return:
    //{
    //      id: xxx,
    //      content: "评论内容",
    //      time: 评论时间,
    //      fromId: 评论者id,
    //      fromName: 评论者昵称,
    //      toId: 被评论者id,
    //      toName: 被评论者昵称
    // }
    // 失败: null
    JSONObject getById(Integer id);

    // 请求地址: /comment/like
    // param id: 评论id
    // param userId：用户id
    // param type: false|true 踩|赞
    // do: 点赞或点踩（重复请求会取消点赞或点踩）
    // return:
    // {
    // id:xxx,
    // data:
    //  {
    //      like:xxx（点赞数量）, dislike:xxx(点踩数量), status:-1|0|1|-400 用户点赞/点踩状态 没赞没踩|踩|赞|发生错误
    //  }
    // }
    JSONObject like(Integer id, Integer userId, Boolean type);


    // 请求地址: /comment/getLike
    // param id: 评论id
    // param userId：用户id
    // do: 获取点赞数据和点赞状态
    // return:
    // {
    // id:xxx,
    // data:
    //  {
    //      like:xxx（点赞数量）, dislike:xxx(点踩数量), status:-1|0|1|-400 用户点赞/点踩状态 没赞没踩|踩|赞|发生错误
    //  }
    // }
    JSONObject getLike(Integer id, Integer userId);

    // 请求地址: /comment/delete
    // param id: 评论id
    // do: 删除评论
    // return: 1|0 成功|失败
    Integer delete(Integer id);
}
