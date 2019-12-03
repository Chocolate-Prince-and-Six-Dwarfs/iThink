package com.choco.ithink.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.choco.ithink.DAO.mapper.ChatRoomMapper;
import com.choco.ithink.DAO.mapper.PrivateChatMapper;
import com.choco.ithink.interfaces.ChatInterface;
import com.choco.ithink.pojo.PrivateChat;
import com.choco.ithink.pojo.PrivateChatExample;
import com.choco.ithink.service.ChatService;
import com.choco.ithink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/chat")
public class ChatController implements ChatInterface {
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;
    @Resource
    private PrivateChatMapper privateChatMapper;

    private List<Integer> connectionList = new ArrayList<Integer>();
    private JSONObject connectionNum = new JSONObject();
    private JSONObject canUpdate = new JSONObject();

    // 请求地址 /chat/connect
    // param userId: 用户id
    // do: 建立聊天连接
    // return: 持续推送聊天信息
    @RequestMapping(value = "/connect", produces = "text/event-stream;charset=UTF-8")
    @ResponseBody
    public String connect(@NotNull Integer userId)
    {
        JSONObject jsonObject = new JSONObject();

        // 获取上次更新时间
        Date lastUpdateTIme = chatService.lastUpdateTime(userId);

        // 休眠一秒防止重复获取消息
        //TimeUnit.SECONDS.sleep(2);

        // 获取数据

        JSONArray groupChatRecord = new JSONArray();
        do
        {
            if(!connectionList.contains(userId))
            {
                return "data:" + "{}" + "\n\n";
            }
            groupChatRecord = chatService.getGroupChatRecordAfter(userId, lastUpdateTIme);
        }while (groupChatRecord.size()==0);

        // 拼接json
        jsonObject.put("userId", userId);
        jsonObject.put("groupChatRecord", groupChatRecord);

        // 刷新时间
        chatService.flushUpdateTime(userId);

        return "data:" +  jsonObject.toJSONString() + "\n\n";
    }


    // 请求地址 /chat/toGroup
    // param userId: 用户id
    // param groupId: 团组id
    // param content:
    // do: 发送聊天消息
    // return: 1|0 成功|失败
    @RequestMapping("/toGroup")
    @ResponseBody
    public Integer toGroup(Integer userId, Integer groupId, String content)
    {
        return chatService.toGroup(userId, groupId, content);
    }

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
    @RequestMapping("/getGroupListByUserId")
    @ResponseJSONP
    public JSONArray getGroupListByUserId(Integer userId)
    {
        JSONArray groupList =  chatService.getGroupListByUserId(userId);

        return groupList;
    }

    // 请求地址 /chat/close
    // param userId: 用户id
    // do: 关闭连接
    @RequestMapping("/close")
    @ResponseJSONP
    public void close(Integer userId)
    {
        if(!connectionList.contains(userId))
        {
            return;
        }
        JSONArray groupList =  chatService.getGroupListByUserId(userId);
        for(int i=0; i<groupList.size(); ++i)
        {
            String key = ((JSONObject)(groupList.get(i))).getInteger("id").toString();
            if (connectionNum.containsKey(key))
            {
                Integer old = connectionNum.getInteger(key);
                Integer updated = old - 1;
                connectionNum.put(key, updated);
            } else {
                connectionNum.put(key, 0);
            }
            canUpdate.put(key, true);
        }
        connectionList.remove(userId);
    }

    // 请求地址 /chat/getGroupChatRecord
    // param id: 团组id
    // do: 全部聊天记录
    @RequestMapping("/getGroupChatRecord")
    @ResponseJSONP
    public JSONObject getGroupChatRecord(Integer id)
    {
        JSONObject jsonObject = new JSONObject();

        JSONArray groupChatRecord = chatService.getGroupChatRecord(id);

        // 拼接json
        jsonObject.put("groupChatRecord", groupChatRecord);

        return jsonObject;
    }

    // 请求地址 /chat/auth
    // param userId: 用户id
    // do: 登记聊天室推送
    @RequestMapping("/auth")
    @ResponseJSONP
    public void auth(Integer userId)
    {
        if(connectionList.contains(userId))
        {
            return;
        }
        JSONArray groupList =  chatService.getGroupListByUserId(userId);
        for(int i=0; i<groupList.size(); ++i)
        {
            String key = ((JSONObject)(groupList.get(i))).getInteger("id").toString();
            if (connectionNum.containsKey(key)) {
                Integer old = connectionNum.getInteger(key);
                Integer updated = old + 1;
                connectionNum.put(key, updated);
            } else {
                connectionNum.put(key, 1);
            }
        }
        connectionList.add(userId);
    }

    // 请求地址 /chat/getOnlineNum
    // param id: 团组id
    // do: 建立聊天连接
    // return: 持续推送聊天信息
    @RequestMapping(value = "/getOnlineNum", produces = "text/event-stream;charset=UTF-8")
    @ResponseBody
    public String getOnlineNum(@NotNull Integer userId) throws InterruptedException
    {
        TimeUnit.SECONDS.sleep(3);

        return "data:" +  connectionNum.toString() + "\n\n";
    }


    // 请求地址 /chat/addToGroup
    // param topicId: id
    // param name: 聊天室名(可选。)
    // param userIdList: 用户id列表
    // do:
    // 若对应创意主题的聊天室不存在:
    //      创建名为name的聊天室，如果没有传入name，则默认为当前时间戳, 最后将列表中用户加入聊天室
    // 若对应创意主题的聊天室存在:
    //      将列表中的用户加入聊天室，若已加入则会被忽略。传入的name会被忽略
    // return: id|0 成功|失败
    @RequestMapping("/addToGroup")
    @ResponseJSONP
    public Integer addToGroup(@Nullable Integer topicId, @Nullable String name, @RequestParam(value = "userIdList[]") Integer[] userIdList, @Nullable Integer ownerId)
    {
        return chatService.addToGroup(topicId, name, userIdList, ownerId);
    }

    // 请求地址 /chat/addToPrivate
    // param userId1: 用户1的id
    // param userId2: 用户2的id
    // do: 添加一个私聊群组(即固定两人的群组)
    // return: id|0 成功|失败
    @RequestMapping("/addToPrivate")
    @ResponseJSONP
    public Integer addToPrivate(Integer userId1, Integer userId2)
    {
        if(userId1 == userId2)
        {
            return 0;
        }
        // 检查是否已经存在私聊
        try
        {
            PrivateChatExample privateChatExample = new PrivateChatExample();
            privateChatExample.createCriteria().andUserId1EqualTo(userId1).andUserId2EqualTo(userId2);
            PrivateChatExample.Criteria addon = privateChatExample.createCriteria().andUserId1EqualTo(userId2).andUserId2EqualTo(userId1);
            privateChatExample.or(addon);
            List<PrivateChat> privateChatList = privateChatMapper.selectByExample(privateChatExample);
            // 已经存在
            if (privateChatList.size() > 0)
            {
                return privateChatList.get(0).getChatRoomId();
            }
            // 不存在
            else
            {
                // 创建聊天室
                String userName1 = userService.getById(userId1).getString("name");
                String userName2 = userService.getById(userId2).getString("name");
                String chatRoomName = (userName1 + "/" + userName2).length() > 20 ? (userName1 + "/" + userName2).substring(0, 20) : (userName1 + "/" + userName2);
                Integer[] userIdList = {userId1, userId2};
                Integer chatRoomId = chatService.addToGroup(null, chatRoomName, userIdList, null);

                // 创建私聊记录
                PrivateChat privateChat = new PrivateChat();
                privateChat.setChatRoomId(chatRoomId);
                privateChat.setUserId1(userId1);
                privateChat.setUserId2(userId2);
                privateChatMapper.insertSelective(privateChat);

                return chatRoomId;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }


    // 请求地址 /chat/kickFromGroup
    // param requestId: 发起踢人请求的id
    // param kickId: 被踢人id
    // param chatRoomId: 聊天室id
    // do: 检查权限，若有权限，则踢人（自己发起踢出自己的请求则是退群, 群主退群会直接解散群）
    // return: 1|0 成功|失败
    @RequestMapping("/kickFromGroup")
    @ResponseJSONP
    public Integer kickFromGroup(Integer requestId, Integer kickId, Integer chatRoomId)
    {
        return chatService.kickFromGroup(requestId, kickId, chatRoomId);
    }


    // 请求地址 /chat/getUserListByChatRoomId
    // param chatRoomId: 聊天室id
    // do: 查找聊天室成员信息
    // return
    // 成功:
    // chatRoomId: ,
    // userList:
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
    @RequestMapping("/getUserListByChatRoomId")
    @ResponseJSONP
    public JSONObject getUserListByChatRoomId(Integer chatRoomId)
    {
        JSONObject jsonObject = new JSONObject();

        JSONArray userList = chatService.getUserListByChatRoomId(chatRoomId);

        // 拼接json
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("chatRoomId", chatRoomId);
        jsonObject.put("userList", userList);

        return jsonObject;
    }
}
