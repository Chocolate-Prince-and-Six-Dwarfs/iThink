package com.choco.ithink.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.choco.ithink.DAO.mapper.*;
import com.choco.ithink.pojo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ChatService {
    @Resource
    private ChatUpdateTimeMapper chatUpdateTimeMapper;
    @Resource
    private GroupMemberMapper groupMemberMapper;
    @Resource
    private GroupChatRecordMapper groupChatRecordMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private BbsTopicMapper bbsTopicMapper;
    @Resource
    private ChatRoomMapper chatRoomMapper;

    // param id: 用户id
    // do: 查询上次更新聊天记录的时间
    // return: 上次更新时间
    public Date lastUpdateTime(Integer id)
    {
        ChatUpdateTimeExample chatUpdateTimeExample = new ChatUpdateTimeExample();
        chatUpdateTimeExample.createCriteria().andUserIdEqualTo(id);

        return chatUpdateTimeMapper.selectByExample(chatUpdateTimeExample).get(0).getTime();
    }


    // param id: 用户id
    // do: 刷新更新时间
    public void flushUpdateTime(Integer id)
    {
        ChatUpdateTimeExample chatUpdateTimeExample = new ChatUpdateTimeExample();
        chatUpdateTimeExample.createCriteria().andUserIdEqualTo(id);

        ChatUpdateTime chatUpdateTime = new ChatUpdateTime();
        chatUpdateTime.setUserId(id);
        chatUpdateTime.setTime(new Date());

        chatUpdateTimeMapper.updateByExample(chatUpdateTime, chatUpdateTimeExample);
    }


    // param id: 用户id
    // param date: 上次更新时间
    // do: 返回自上次更新时间之后的团组聊天记录
    // return: {groupId: xxx, userId: xxx, time: }
    public JSONArray getGroupChatRecordAfter(Integer id, Date date)
    {
        JSONArray jsonArray = new JSONArray();

        // 获取用户的团组
        GroupMemberExample groupMemberExample = new GroupMemberExample();
        groupMemberExample.createCriteria().andUserIdEqualTo(id);
        List<GroupMember> groupMemberList = groupMemberMapper.selectByExample(groupMemberExample);
        if(groupMemberList.size()==0)
        {
            return null;
        }

        // 遍历团组
        for(int i=0; i<groupMemberList.size(); ++i)
        {
            // 获取聊天记录
            GroupChatRecordExample groupChatRecordExample = new GroupChatRecordExample();
            groupChatRecordExample.setOrderByClause("time");
            groupChatRecordExample.createCriteria().andToIdEqualTo(groupMemberList.get(i).getChatRoomId()).andTimeGreaterThan(date);
            List<GroupChatRecord> groupChatRecordList = groupChatRecordMapper.selectByExample(groupChatRecordExample);
            if(groupChatRecordList.size()!=0)
            {
                jsonArray.add(groupChatRecordList2JSON(groupChatRecordList));
            }
        }

        return jsonArray;
    }


    public JSONArray groupChatRecordList2JSON(List<GroupChatRecord> groupChatRecordList)
    {
        JSONArray jsonArray = new JSONArray();

        // 循环处理实体
        for(int i=0; i<groupChatRecordList.size(); ++i)
        {
            // 获取昵称
            // 发送消息者
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUserIdEqualTo(groupChatRecordList.get(i).getFromId());
            List<User> fromList = userMapper.selectByExample(userExample);
            String fromName = "";
            if(fromList.size()==1)
            {
                fromName = fromList.get(0).getUserName();
            }
            // 被回复团组
//
//            userExample.createCriteria().andUserIdEqualTo(groupChatRecordList.get(i).getToId());
//            List<User> toList = userMapper.selectByExample(userExample);
//            String toName = "";
//            if(toList.size()==1)
//            {
//                toName = toList.get(0).getUserName();
//            }



            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", groupChatRecordList.get(i).getId());
            jsonObject.put("content", groupChatRecordList.get(i).getContent());
            jsonObject.put("time", groupChatRecordList.get(i).getTime());
            jsonObject.put("fromId", groupChatRecordList.get(i).getFromId());
            jsonObject.put("fromName", fromName);
            jsonObject.put("toId", groupChatRecordList.get(i).getToId());
            //jsonObject.put("toName", toName);
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }


    // param userId: 用户id
    // param groupId: 团组id
    // param content:
    // do: 发送聊天消息
    // return: 0|1 成功|失败
    public Integer toGroup(Integer userId, Integer groupId, String content)
    {
        GroupChatRecord groupChatRecord = new GroupChatRecord();
        groupChatRecord.setFromId(userId);
        groupChatRecord.setToId(groupId);
        groupChatRecord.setContent(content);

        return groupChatRecordMapper.insertSelective(groupChatRecord);
    }


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
    public JSONArray getGroupListByUserId(Integer userId)
    {
        JSONArray jsonArray = new JSONArray();

        // 获取用户的团组
        GroupMemberExample groupMemberExample = new GroupMemberExample();
        groupMemberExample.createCriteria().andUserIdEqualTo(userId);
        List<GroupMember> groupMemberList = groupMemberMapper.selectByExample(groupMemberExample);
        if(groupMemberList.size()==0)
        {
            return null;
        }

        // 遍历团组
        for(int i=0; i<groupMemberList.size(); ++i)
        {
            // 获取聊天室
            ChatRoomExample chatRoomExample = new ChatRoomExample();
            chatRoomExample.createCriteria().andIdEqualTo(groupMemberList.get(i).getChatRoomId());
            jsonArray.add(chatRoomList2JSON(chatRoomMapper.selectByExample(chatRoomExample)).get(0));
        }

        return jsonArray;
    }

    // param chatRoomList: 实体列表
    // do： 将列表构建为JSON数组对象
    // return: 构建的json数组对象
    public JSONArray chatRoomList2JSON(List<ChatRoom> chatRoomList)
    {
        JSONArray jsonArray = new JSONArray();

        // 循环处理实体
        for(int i=0; i<chatRoomList.size(); ++i)
        {
            // 获取群主昵称
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUserIdEqualTo(chatRoomList.get(i).getOwnerId());
            List<User> fromList = userMapper.selectByExample(userExample);
            String ownerName = "";
            if(fromList.size()==1)
            {
                ownerName = fromList.get(0).getUserName();
            }

            // 获取主题标题
            BbsTopicExample bbsTopicExample = new BbsTopicExample();
            bbsTopicExample.createCriteria().andTopicIdEqualTo(chatRoomList.get(i).getTopicId());
            List<BbsTopic> bbsTopicList = bbsTopicMapper.selectByExample(bbsTopicExample);
            String topicTitle = "";
            if(bbsTopicList.size()==1)
            {
                topicTitle = bbsTopicList.get(0).getTopicTitle();
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", chatRoomList.get(i).getId());
            jsonObject.put("name", chatRoomList.get(i).getName());
            jsonObject.put("ownerId", chatRoomList.get(i).getOwnerId());
            jsonObject.put("ownerName", ownerName);
            jsonObject.put("topicId", chatRoomList.get(i).getTopicId());
            jsonObject.put("topicTitle", topicTitle);
            jsonObject.put("time", chatRoomList.get(i).getTime());
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }
}
