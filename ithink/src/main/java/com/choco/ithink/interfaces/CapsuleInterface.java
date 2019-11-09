package com.choco.ithink.interfaces;

import com.alibaba.fastjson.JSONObject;
import org.springframework.lang.Nullable;

import java.util.Date;

public interface CapsuleInterface {
    // 请求地址: capsule/save
    // param name: 胶囊名
    // param content: 胶囊内容
    // param userId: 用户id
    // param buildTime: 创建时间（网页端不填，默认为和上传时间一致）
    // do: 将胶囊保存到服务器
    // return: 保存的胶囊id, 失败返回null
    Integer save(String name, String content, Integer userId, @Nullable Date buildTime);


    // 请求地址: capsule/update
    // param name: 胶囊名
    // param content: 胶囊内容
    // param id: 胶囊id
    // do: 更新胶囊
    // return: {id: xxx, status: 0|1 失败|成功}
    JSONObject update(String name, String content, Integer id);


    // 请求地址: capsule/getById
    // param id: 胶囊id
    // do: 获取胶囊
    // return:
    // {
    // id: xxx,
    // data:
    // {
    //  id: ,
    //  name: ,
    //  content: ,
    //  userId: ,
    //  userName: ,
    //  buildTime: ,
    //  uploadTime: 上传时间/最后一次更新时间
    // }
    // }
    JSONObject getById(Integer id);
}
