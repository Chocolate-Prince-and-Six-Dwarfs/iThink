package com.choco.ithink.interfaces;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.lang.Nullable;

import javax.servlet.http.HttpServletRequest;

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
    //      name: 聊天组name,
    //      time: 聊天组创建时间
    //      ownerId: 群主id
    //      ownerName: 群主昵称
    //      topicId: 所属主题id
    //      topicTitle: 所属主题标题
    //  }
    // ]
    JSONArray getGroupListByUserId(Integer userId);

    // 请求地址 /chat/close
    // param userId: 用户id
    // do: 关闭连接
    void close(Integer userId);

    // 请求地址 /chat/getGroupChatRecord
    // param id: 团组id
    // do: 全部聊天记录
    // return:
    //  （以下时间都是时间戳的形式(即一个长整型数字)）
    // {
    //      groupChatRecord:
    //      [
    //          {id:聊天记录id, fromId: 发送消息的用户id, fromName: , toId: 接收消息的团组id, time: , content: ,}
    //      ]
    // }
    JSONObject getGroupChatRecord(Integer id);


    // param topicId: 创意主题id(可选)
    // param name: 聊天室名(可选。)
    // param userIdList: 用户id列表
    // param ownerId: 群主id (可选，默认为列表中第一个用户id)
    // do:
    // 若对应创意主题的聊天室不存在:
    //      创建名为name的聊天室，如果没有传入name，则默认为当前时间戳, 最后将列表中用户加入聊天室
    // 若对应创意主题的聊天室存在:
    //      将列表中的用户加入聊天室，若已加入则会被忽略。传入的name会被忽略
    // 若没有指定topicId, 则始终创建新聊天室
    // return: 1|0 成功|失败
    Integer addToGroup(@Nullable Integer topicId, @Nullable String name, Integer[] userIdList, @Nullable Integer ownerId);
}
