package com.choco.ithink.interfaces;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface ChatInterface {
    // 请求地址 /chat/connect
    // param userId: 用户id
    // do: 建立聊天连接
    // return: 持续推送聊天信息
    //  （以下时间都是时间戳的形式(即一个长整型数字)）
    // {
    //      userId: ,
    //      groupChatRecord:
    //      [
    //          {id:聊天记录id, fromId: 发送消息的用户id, fromName: , toId: 接收消息的团组id, time: , content: ,}
    //      ]
    // }
    String connect(Integer userId);

    // 请求地址 /chat/toGroup
    // param userId: 用户id
    // param groupId: 团组id
    // param content:
    // do: 发送聊天消息
    // return: 1|0 成功|失败
    Integer toGroup(Integer userId, Integer groupId, String content);

    // 请求地址 /chat/getGroupListByUserId
    // param userId: 用户id
    // do: 查询用户所有的聊天组列表
    // return:
    // [
    //  {
    //      id: 聊天组id,
    //      name: 聊天组name
    //  }
    // ]
    JSONArray getGroupListByUserId(Integer userId);
}
