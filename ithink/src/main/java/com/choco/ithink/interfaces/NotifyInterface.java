package com.choco.ithink.interfaces;

public interface NotifyInterface {
    // 请求地址: /notify
    // param id: 用户id
    // do: sse推送用户通知
    // return:
    // {
    //
    // }
    String notify(Integer id) throws InterruptedException;
}
