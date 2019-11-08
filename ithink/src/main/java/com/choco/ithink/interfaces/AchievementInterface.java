package com.choco.ithink.interfaces;

import com.alibaba.fastjson.JSONObject;

public interface AchievementInterface {

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
    JSONObject like(Integer id, Integer userId, Boolean type);


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
    JSONObject getLike(Integer id, Integer userId);


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
    JSONObject collect(Integer id, Integer userId);


    // 请求地址: /ach/getCollect
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
    JSONObject getCollect(Integer id, Integer userId);
}
