package com.choco.ithink.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.choco.ithink.interfaces.AchievementInterface;
import com.choco.ithink.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
