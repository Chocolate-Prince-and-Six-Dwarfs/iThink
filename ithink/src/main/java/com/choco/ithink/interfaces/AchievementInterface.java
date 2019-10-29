package com.choco.ithink.interfaces;

public interface AchievementInterface {
    // 请求地址: /ach/getById
    // param id: 创意实现id
    // do: 查找指定创意实现的信息
    // return:
    //      data:
    //      [
    //          {
    //              id: 1 (创意实现id),
    //              userId: 1 (创意实现发布者id),
    //              topicName: "创意实现对应的主题名",
    //              topicId: 1 (创意实现对应的主题id),
    //              content: "创意实现内容",
    //              time: "创意实现时间",
    //              good: 20 (创意实现点赞数),
    //              bad: 2 (创意实现的点灭数),
    //              reply: 3 （创意实现的回复数）
    //          }
    //      ]
}
