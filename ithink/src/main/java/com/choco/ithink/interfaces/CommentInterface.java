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
}
