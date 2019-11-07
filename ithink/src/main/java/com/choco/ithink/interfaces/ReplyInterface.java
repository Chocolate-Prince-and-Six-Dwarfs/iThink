package com.choco.ithink.interfaces;

import com.alibaba.fastjson.JSONObject;

public interface ReplyInterface {
    // 请求地址: reply/do
    // param commentId: 创意实现id
    // param content: 回复内容
    // param fromId: 回复者id
    // param toId: 被回复者id
    // do: 发表回复
    // return:
    // 成功: {status: 1, id: xxx},
    // 失败: {status: 0, id: null}
    JSONObject doReply(Integer achievementId, String content, Integer fromId, Integer toId);

    // 请求地址: reply/getById
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

    JSONObject getById(Integer id);
}
