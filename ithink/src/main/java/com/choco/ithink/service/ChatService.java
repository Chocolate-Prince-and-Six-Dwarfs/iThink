package com.choco.ithink.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.choco.ithink.DAO.mapper.*;
import com.choco.ithink.pojo.*;
import com.choco.ithink.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
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
    @Autowired
    private UserService userService;

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

    // param id: 团组id
    // do: 全部聊天记录
    // return: {groupId: xxx, userId: xxx, time: }
    public JSONArray getGroupChatRecord(Integer id)
    {
        JSONArray jsonArray = new JSONArray();

        // 获取聊天记录
        GroupChatRecordExample groupChatRecordExample = new GroupChatRecordExample();
        groupChatRecordExample.setOrderByClause("time");
        groupChatRecordExample.createCriteria().andToIdEqualTo(id);
        List<GroupChatRecord> groupChatRecordList = groupChatRecordMapper.selectByExample(groupChatRecordExample);
        if(groupChatRecordList.size()!=0)
        {
            jsonArray.add(groupChatRecordList2JSON(groupChatRecordList));
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
        groupChatRecord.setContent(Tool.delS(content));

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
            String topicTitle = "";
            if(chatRoomList.get(i).getTopicId()!=null)
            {
                BbsTopicExample bbsTopicExample = new BbsTopicExample();
                bbsTopicExample.createCriteria().andTopicIdEqualTo(chatRoomList.get(i).getTopicId());
                List<BbsTopic> bbsTopicList = bbsTopicMapper.selectByExample(bbsTopicExample);
                if (bbsTopicList.size() == 1) {
                    topicTitle = bbsTopicList.get(0).getTopicTitle();
                }
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
    public Integer addToGroup(@Nullable Integer topicId, @Nullable String name, Integer[] userIdList, @Nullable Integer ownerId)
    {
        Integer status = 0;
        if(userIdList.length<2)
        {
            return status;
        }

        try
        {
            Integer chatRoomId = null;
            Date now = new Date();
            if(topicId!=null)
            {
                // 检查聊天室是否已经存在
                ChatRoomExample chatRoomExample = new ChatRoomExample();
                chatRoomExample.createCriteria().andTopicIdEqualTo(topicId);

                    // 若不存在
                    List<ChatRoom> chatRoomList = chatRoomMapper.selectByExample(chatRoomExample);
                    if (chatRoomList.size() != 1)
                    {
                        ChatRoom chatRoom = new ChatRoom();
                        chatRoom.setName(Tool.delS(name) == null ? now.toString() : Tool.delS(name));
                        chatRoom.setOwnerId(ownerId == null ? userIdList[0] : ownerId);
                        chatRoom.setTopicId(topicId);
                        chatRoom.setTime(now);

                        // 创建聊天室
                        status = chatRoomMapper.insertSelective(chatRoom);

                        chatRoomId = chatRoom.getId();

                        // 添加用户
                        for (Integer userId : userIdList)
                        {
                            GroupMember groupMember = new GroupMember();
                            groupMember.setTime(now);
                            groupMember.setChatRoomId(chatRoomId);
                            groupMember.setUserId(userId);
                            status = groupMemberMapper.insertSelective(groupMember);
                            if(status!=1)
                            {
                                // 删除成员
                                GroupMemberExample groupMemberExample = new GroupMemberExample();
                                groupMemberExample.createCriteria().andChatRoomIdEqualTo(chatRoomId);
                                groupMemberMapper.deleteByExample(groupMemberExample);

                                // 删除聊天室
                                chatRoomMapper.deleteByPrimaryKey(chatRoomId);
                                break;
                            }
                        }
                    }
                    // 若已存在
                    else
                    {
                        chatRoomId = chatRoomList.get(0).getId();

                        // 添加用户
                        for (Integer userId : userIdList)
                        {
                            // 检查是否已经存在于团组中
                            GroupMemberExample groupMemberExample = new GroupMemberExample();
                            groupMemberExample.createCriteria().andChatRoomIdEqualTo(chatRoomId).andUserIdEqualTo(userId);
                            if(groupMemberMapper.selectByExample(groupMemberExample).size()==1)
                            {
                                continue;
                            }

                            GroupMember groupMember = new GroupMember();
                            groupMember.setTime(now);
                            groupMember.setChatRoomId(chatRoomId);
                            groupMember.setUserId(userId);
                            status = groupMemberMapper.insertSelective(groupMember);
                            if(status!=1)
                            {
                                break;
                            }
                        }
                    }
            }
            else
            {
                ChatRoom chatRoom = new ChatRoom();
                chatRoom.setName(Tool.delS(name) == null ? now.toString() : Tool.delS(name));
                chatRoom.setOwnerId(ownerId == null ? userIdList[0] : ownerId);
                chatRoom.setTopicId(topicId);
                chatRoom.setTime(now);

                // 创建聊天室
                status = chatRoomMapper.insertSelective(chatRoom);

                chatRoomId = chatRoom.getId();

                // 添加用户
                for (Integer userId : userIdList)
                {
                    GroupMember groupMember = new GroupMember();
                    groupMember.setTime(now);
                    groupMember.setChatRoomId(chatRoomId);
                    groupMember.setUserId(userId);
                    status = groupMemberMapper.insertSelective(groupMember);
                    if(status!=1)
                    {
                        // 删除成员
                        GroupMemberExample groupMemberExample = new GroupMemberExample();
                        groupMemberExample.createCriteria().andChatRoomIdEqualTo(chatRoomId);
                        groupMemberMapper.deleteByExample(groupMemberExample);

                        // 删除聊天室
                        chatRoomMapper.deleteByPrimaryKey(chatRoomId);
                        break;
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return status;
    }


    // param requestId: 发起踢人请求的id
    // param kickId: 被踢人id
    // param chatRoomId: 聊天室id
    // do: 检查权限，若有权限，则踢人（自己发起踢出自己的请求则是退群, 群主退群会直接解散群）
    // return: 1|0 成功|失败
    public Integer kickFromGroup(Integer requestId, Integer kickId, Integer chatRoomId)
    {
        Integer status = 0;
        // 检查请求者是否是群主
        ChatRoom chatRoom = chatRoomMapper.selectByPrimaryKey(chatRoomId);
        Integer ownerId = chatRoom.getOwnerId();
        // 是群主
        try {
            if (requestId == ownerId)
            {
                // 检查是否是主动退群
                // 主动退群
                if (requestId == kickId)
                {
                    // 删除所有群成员
                    GroupMemberExample groupMemberExample = new GroupMemberExample();
                    groupMemberExample.createCriteria().andChatRoomIdEqualTo(chatRoomId);
                    groupMemberMapper.deleteByExample(groupMemberExample);

                    // 删除群
                    chatRoomMapper.deleteByPrimaryKey(chatRoomId);
                    status = 1;
                }
                // 踢人
                else
                {
                    // 踢人
                    GroupMemberExample groupMemberExample = new GroupMemberExample();
                    groupMemberExample.createCriteria().andChatRoomIdEqualTo(chatRoomId).andUserIdEqualTo(kickId);
                    groupMemberMapper.deleteByExample(groupMemberExample);
                    status = 1;
                }
            }
            // 不是群主
            else {
                // 检查是否是主动退群
                // 主动退群
                if (requestId == kickId)
                {
                    // 退群
                    GroupMemberExample groupMemberExample = new GroupMemberExample();
                    groupMemberExample.createCriteria().andChatRoomIdEqualTo(chatRoomId).andUserIdEqualTo(kickId);
                    groupMemberMapper.deleteByExample(groupMemberExample);
                    status = 1;
                }
                // 踢人
                else
                    {
                    // 非群主没有权限踢出其他成员
                    status = 0;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status = 0;
        }
        finally
        {
            return  status;
        }
    }


    // param chatRoomId: 聊天室id
    // do: 查找聊天室成员信息
    // return
    // 成功:
    // [
    //  {
    //      id: ,
    //      name: ,
    //      sex: ,
    //      email: ,
    //      birthday: ,
    //      phone: ,
    //      credit: 信誉积分,
    //      head: 头像
    //      address: ,
    //      industry: 职业,
    //      school: ,
    //      introduction
    //  },
    //  ...
    // ]
    public JSONArray getUserListByChatRoomId(Integer chatRoomId)
    {
        JSONArray jsonArray = new JSONArray();
        // 查找组内用户id
        GroupMemberExample groupMemberExample = new GroupMemberExample();
        groupMemberExample.createCriteria().andChatRoomIdEqualTo(chatRoomId);
        List<GroupMember> groupMemberList = groupMemberMapper.selectByExample(groupMemberExample);
        for(GroupMember groupMember: groupMemberList)
        {
            jsonArray.add(userService.getById(groupMember.getUserId()));
        }

        return jsonArray;
    }
}
