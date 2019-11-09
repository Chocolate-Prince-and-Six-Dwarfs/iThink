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
    //  （以下时间都是时间戳的形式(即一个长整型数字)）
    // {
    //      id: ,
    //      topicLike:
    //      [
    //          {topicId: , time: , type: , userId: }
    //      ]
    //      achievementLike:
    //      [
    //          {achievementId: , time: , type: , userId: }
    //      ]
    //      commentLike:
    //      [
    //          {commentId: , time: , type: , userId: }
    //      ]
    //      topicCollect:
    //      [
    //          {topicId: , time: ,  userId: }
    //      ]
    //      achievementCollect:
    //      [
    //          {achievementId: , time: ,  userId: }
    //      ]
    // }
    @RequestMapping(value = "/notify", produces = "text/event-stream;charset=UTF-8")
    @ResponseBody
    public String notify(Integer id) throws InterruptedException {
        JSONObject jsonObject = new JSONObject();

        // 获取上次更新时间
        Date lastUpdateTIme = notifyService.lastUpdateTime(id);

        // 休眠一秒防止重复获取消息
        //TimeUnit.SECONDS.sleep(2);

        // 获取数据

        JSONArray topicLike = new JSONArray();
        JSONArray achievementLike = new JSONArray();
        JSONArray commentLike = new JSONArray();
        JSONArray topicCollect = new JSONArray();
        JSONArray achievementCollect = new JSONArray();
        do
        {
            topicLike = notifyService.getTopicLikeAfter(id, lastUpdateTIme);
            achievementLike = notifyService.getAchievementLikeAfter(id, lastUpdateTIme);
            commentLike = notifyService.getCommentLikeAfter(id, lastUpdateTIme);

            topicCollect = notifyService.getTopicCollectAfter(id, lastUpdateTIme);
            achievementCollect = notifyService.getAchievementCollectAfter(id, lastUpdateTIme);
        }while (topicLike.size()==0 && achievementLike.size()==0 && commentLike.size()==0 && topicCollect.size()==0 && achievementCollect.size()==0);

        // 拼接json
        jsonObject.put("id", id);
        jsonObject.put("topicLike", topicLike);
        jsonObject.put("achievementLike", achievementLike);
        jsonObject.put("commentLike", commentLike);
        jsonObject.put("topicCollect", topicCollect);
        jsonObject.put("achievementCollect", achievementCollect);

        // 刷新时间
        notifyService.flushUpdateTime(id);

        return "data:" + "id:" + jsonObject.toJSONString() + "\n\n";
    }
}
