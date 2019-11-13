package com.choco.ithink.service;

import com.alibaba.fastjson.JSONArray;
import com.choco.ithink.DAO.mapper.ChatUpdateTimeMapper;
import com.choco.ithink.DAO.mapper.GroupChatRecordMapper;
import com.choco.ithink.DAO.mapper.GroupMemberMapper;
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

        ChatUpdateTime updateTime = new ChatUpdateTime();
        updateTime.setUserId(id);
        updateTime.setTime(new Date());

        chatUpdateTimeMapper.updateByExampleSelective(updateTime, chatUpdateTimeExample);
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
                jsonArray.add(groupChatRecordList);
            }
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
}
