package com.choco.ithink.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.choco.ithink.interfaces.CreativeIdeaInterface;
import com.choco.ithink.service.CreativeIdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/idea")
public class CreativeIdeaController implements CreativeIdeaInterface {
    @Autowired
    private CreativeIdeaService creativeIdeaService;


    // 请求地址 idea/search
    // param keyword: 搜索关键词
    // do: 搜索匹配的创意并返回
    // return: 匹配到的创意，格式如下
    // {
    // count: 2 （查找到的数据条数）
    // data:
    //  [
    //      {
    //          id: 0,
    //          title: "创意名称",
    //          content: "创意内容",
    //          like: 收藏数
    //      },
    //      {
    //          同上
    //      },
    //      ......
    //  ]
    // }
    @RequestMapping("/search")
    @ResponseJSONP
    public JSONObject search(String keyword)
    {
        JSONObject jsonObject = new JSONObject();

        // 搜索与统计
        JSONArray data = creativeIdeaService.search(keyword);
        Integer count = data.size();


        // 拼接字符串
        jsonObject.put("count", count);
        jsonObject.put("data", data);
        return jsonObject;
    }
}
