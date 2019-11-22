package com.choco.ithink.interfaces;

public interface NotifyInterface {
    // 请求地址: /notify (EventSource请求, onmessage接收, 例子请见notify.html)
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
    String notify(Integer id) throws InterruptedException;


    // 请求地址: /stopNotify
    // param id: 用户id
    // do: 关闭推送，请在页面关闭或者刷新时调用(beforeunload事件)
    void stopNotify(Integer id) throws InterruptedException;
}
