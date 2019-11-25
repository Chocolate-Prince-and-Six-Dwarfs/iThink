package com.choco.ithink.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.choco.ithink.interfaces.AchievementInterface;
import com.choco.ithink.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ach")
public class AchievementController implements AchievementInterface {
    @Autowired
    private AchievementService achievementService;

    // 请求地址: /ach/like
    // param id: 创意实现id
    // param userId：用户id
    // param type: false|true 踩|赞
    // do: 点赞或点踩（重复请求会取消点赞或点踩）
    // return:
    // {
    // id:xxx,
    // data:
    //  {
    //      like:xxx（点赞数量）, dislike:xxx(点踩数量), status:-1|0|1|-400 用户点赞/点踩状态 没赞没踩|踩|赞|发生错误
    //  }
    // }
    @RequestMapping("/like")
    @ResponseJSONP
    public JSONObject like(Integer id, Integer userId, Boolean type)
    {
        JSONObject jsonObject = new JSONObject();

        // 搜索与统计
        JSONObject data = achievementService.like(id, userId, type);


        // 拼接字符串
        jsonObject.put("id", id);
        jsonObject.put("data", data);
        return jsonObject;
    }


    // 请求地址: /ach/getLike
    // param id: 创意实现id
    // param userId：用户id
    // do: 获取点赞数据和点赞状态
    // return:
    // {
    // id:xxx,
    // data:
    //  {
    //      like:xxx（点赞数量）, dislike:xxx(点踩数量), status:-1|0|1|-400 用户点赞/点踩状态 没赞没踩|踩|赞|发生错误
    //  }
    // }
    @RequestMapping("/getLike")
    @ResponseJSONP
    public JSONObject getLike(Integer id, Integer userId)
    {
        JSONObject jsonObject = new JSONObject();

        // 搜索与统计
        JSONObject data = achievementService.getLike(id, userId);


        // 拼接字符串
        jsonObject.put("id", id);
        jsonObject.put("data", data);
        return jsonObject;
    }


    // 请求地址: /ach/collect
    // param id: 创意实现id
    // param userId：用户id
    // do: 收藏（重复请求会取消收藏）
    // return:
    // {
    // id:xxx,
    // data:
    //  {
    //      collect:xxx（收藏数量）, status:0|1|-400 用户收藏状态 未收藏|已收藏|发生错误
    //  }
    // }
    @RequestMapping("/collect")
    @ResponseJSONP
    public JSONObject collect(Integer id, Integer userId)
    {
        JSONObject jsonObject = new JSONObject();

        // 搜索与统计
        JSONObject data = achievementService.collect(id, userId);


        // 拼接字符串
        jsonObject.put("id", id);
        jsonObject.put("data", data);
        return jsonObject;
    }


    // 请求地址: /ach/getCollect
    // param id: 创意实现id
    // param userId：用户id
    // do: 获取收藏数据及收藏状态
    // return:
    // {
    // id:xxx,
    // data:
    //  {
    //      collect:xxx（收藏数量）, status:0|1|-400 用户收藏状态 未收藏|已收藏|发生错误
    //  }
    // }
    @RequestMapping("/getCollect")
    @ResponseJSONP
    public JSONObject getCollect(Integer id, Integer userId)
    {
        JSONObject jsonObject = new JSONObject();

        // 搜索与统计
        JSONObject data = achievementService.getCollect(id, userId);


        // 拼接字符串
        jsonObject.put("id", id);
        jsonObject.put("data", data);
        return jsonObject;
    }


    // 请求地址: /ach/getById
    // param id: 创意实现id
    // do: 获取指定创意实现的数据
    // return:
    // {
    //    id: 1 (创意实现id),
    //    userId: 1 (创意实现发布者id),
    //    topicName: "创意实现对应的主题名",
    //    topicId: 1 (创意实现对应的主题id),
    //    content: "创意实现内容",
    //    time: "创意实现时间",
    //    like: 20 (创意实现点赞数),
    //    dislike: 2 (创意实现的点踩数),
    //    collect: 2 （收藏数）,
    //    comment: 3（评论数）
    // }
    @RequestMapping("/getById")
    @ResponseJSONP
    public JSONObject getById(Integer id)
    {
        return achievementService.getById(id);
    }


    // 请求地址: /ach/publish
    // param userId: 用户id
    // param topicId: 对应的创意主题id
    // param content: 创意实现内容
    // do: 发布创意实现
    // return: 创意实现id, 失败返回null
    @RequestMapping("/publish")
    @ResponseJSONP
    public Integer publish(Integer userId, Integer topicId, String content)
    {
        return achievementService.publish(userId, topicId, content);
    }

    // 请求地址: /ach/delete
    // param id: 创意实现id
    // do: 删除创意实现
    // return: 1|0 成功|失败
    @RequestMapping("/delete")
    @ResponseBody
    public Integer delete(Integer id)
    {
        return achievementService.delete(id);
    }
}
