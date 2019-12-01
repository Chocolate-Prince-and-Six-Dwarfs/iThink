package com.choco.ithink.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.choco.ithink.interfaces.CapsuleInterface;
import com.choco.ithink.service.CapsuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/capsule")
public class CapsuleController implements CapsuleInterface {
    @Autowired
    private CapsuleService capsuleService;

    // 请求地址: capsule/save
    // param name: 胶囊名
    // param content: 胶囊内容
    // param userId: 用户id
    // param buildTime: 创建时间（网页端不填，默认为和上传时间一致）
    // do: 将胶囊保存到服务器
    // return: 保存的胶囊id, 失败返回null
    @RequestMapping("/save")
    @ResponseBody
    public Integer save(String name, String content, Integer userId, @Nullable Date buildTime)
    {
        Integer id = capsuleService.save(name, content, userId, buildTime);

        return id;
    }


    // 请求地址: capsule/update
    // param name: 胶囊名
    // param content: 胶囊内容
    // param id: 胶囊id
    // do: 更新胶囊
    // return: {id: xxx, status: 0|1 失败|成功}
    @RequestMapping("/update")
    @ResponseJSONP
    public JSONObject update(String name, String content, Integer id)
    {
        JSONObject jsonObject =  new JSONObject();

        // 更新
        Integer status = capsuleService.update(name, content, id);

        // 拼接json
        jsonObject.put("id", id);
        jsonObject.put("status", status);

        return jsonObject;
    }


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
    @RequestMapping("/getById")
    @ResponseJSONP
    public JSONObject getById(Integer id)
    {
        JSONObject jsonObject =  new JSONObject();

        // 更新
        JSONObject data = capsuleService.getById(id);

        // 拼接json
        jsonObject.put("id", id);
        jsonObject.put("data", data);

        return jsonObject;
    }
}
