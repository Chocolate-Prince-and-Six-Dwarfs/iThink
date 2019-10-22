package com.choco.ithink.interfaces;

import com.alibaba.fastjson.JSONObject;

public interface CreativeIdeaInterface {
    // 请求地址 idea/search
    // param keyword: 搜索关键词
    // do: 搜索匹配的创意并返回
    // return: 匹配到的创意，格式如下
    // {
    // count: 2 （查找到的数据条数）
    // data:
    //  [
    //      id: 0
    //      title: "创意名称",
    //      content: "创意内容",
    //      like: 收藏数 （待定）
    //  ]
    // }
    JSONObject search(String keyword);
}
