package com.choco.ithink.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.choco.ithink.interfaces.ChatInterface;
import com.choco.ithink.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    private List<Integer> waitingForClosingConnection = new ArrayList<Integer>();
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
            if(waitingForClosingConnection.contains(userId))
            {
                waitingForClosingConnection.remove(userId);
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
        waitingForClosingConnection.add(userId);
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
            canUpdate.put(key, true);
        }
        connectionList.add(userId);
    }

    // 请求地址 /chat/getOnlineNum
    // param id: 团组id
    // do: 建立聊天连接
    // return: 持续推送聊天信息
    @RequestMapping(value = "/getOnlineNum", produces = "text/event-stream;charset=UTF-8")
    @ResponseBody
    public String getOnlineNum(@NotNull Integer id) throws InterruptedException {
        JSONObject jsonObject = new JSONObject();

        String key = id.toString();
        Integer num = 0;
        while(!canUpdate.getBoolean(key));
        num = connectionNum.getInteger(key);

        // 更新flag置否
        TimeUnit.SECONDS.sleep(3);
        canUpdate.put(key, false);

        return "data:" +  num.toString() + "\n\n";
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
    // return: 1|0 成功|失败
    @RequestMapping("/addToGroup")
    @ResponseJSONP
    public Integer addToGroup(@Nullable Integer topicId, @Nullable String name, @RequestParam(value = "userIdList[]") Integer[] userIdList, @Nullable Integer ownerId)
    {
        return chatService.addToGroup(topicId, name, userIdList, ownerId);
    }
}
