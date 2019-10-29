package com.choco.ithink.interfaces;

import com.alibaba.fastjson.JSONObject;
import org.springframework.lang.Nullable;

public interface CreativeIdeaInterface {
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
    JSONObject search(String keyword);


    // 请求地址 idea/load
    // param number: 数量（可选，默认为10）
    // do: 返回指定数量的创意主题
    // return: 创意主题，格式如下
    // {
    // count: 10 （实际返回的条数）
    // data:
    //  [
    //      {
    //          id: 0,
    //          title: "创意名称",
    //          content: "创意内容",
    //          time: "时间",
    //          publisherId: 0,（发布者id）
    //          publisher: "发布者",
    //          like: 收藏数
    //      },
    //      {
    //          同上
    //      },
    //      ......
    //  ]
    // }
    JSONObject load(@Nullable Integer number);
}
