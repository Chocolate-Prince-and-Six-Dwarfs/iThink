package com.choco.ithink.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.choco.ithink.interfaces.ChatInterface;
import com.choco.ithink.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController implements ChatInterface {
    @Autowired
    private ChatService chatService;

    private List<Integer> unhandledConnection = new ArrayList<Integer>();

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
            if(unhandledConnection.contains(userId))
            {
                unhandledConnection.remove(userId);
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
        return chatService.getGroupListByUserId(userId);
    }

    // 请求地址 /chat/close
    // param userId: 用户id
    // do: 关闭连接
    @RequestMapping("/close")
    @ResponseJSONP
    public void close(Integer userId)
    {
        unhandledConnection.add(userId);
    }

}
