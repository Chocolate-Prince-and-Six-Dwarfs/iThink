package com.choco.ithink.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.choco.ithink.interfaces.NotifyInterface;
import com.choco.ithink.service.NotifyService;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Controller
public class NotifyController implements NotifyInterface {
    @Autowired
    private NotifyService notifyService;

    // 请求地址: /notify
    // param id: 用户id
    // do: sse推送用户通知
    // return:
    // {
    //      id: ,
    //      commentLike:
    //      [
    //          {commentId: , time: , type: , userId: }
    //      ]
    // }
    @RequestMapping(value = "/notify", produces = "text/event-stream;charset=UTF-8")
    @ResponseBody
    public String notify(Integer id) throws InterruptedException {
        JSONObject jsonObject = new JSONObject();

        // 获取上次更新时间
        Date lastUpdateTIme = notifyService.lastUpdateTime(id);

        // 休眠一秒防止重复获取消息
        TimeUnit.SECONDS.sleep(1);

        // 获取数据
        JSONArray commentLike = null;
        do
        {
            commentLike = notifyService.getCommentLikeAfter(id, lastUpdateTIme);
        }while (commentLike.size()==0);

        // 拼接json
        jsonObject.put("id", id);
        jsonObject.put("commentLike", commentLike);

        // 刷新时间
        notifyService.flushUpdateTime(id);

        return "data:" + "id:" + jsonObject.toJSONString() + "\n\n";
    }
}
