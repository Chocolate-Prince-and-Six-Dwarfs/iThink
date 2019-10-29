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


    // 请求地址 idea/detail
    // param id: 创意主题id
    // do: 按id查找创意主题及其实现
    // return: 创意主题及其实现，格式如下
    // {
    //  topic:
    //  {
    //      id: 0,(创意主题id)
    //      title: "创意名称",
    //      content: "创意内容",
    //      time: "时间",
    //      publisherId: 0,（发布者id）
    //      publisher: "发布者",
    //      like: 收藏数
    //  },
    //  achievementCount: 10, (创意实现条数)
    //  achievements:
    //  [
    //      {
    //              achievement:
    //              {
    //                  id: 1 (创意实现id),
    //                  userId: 1 (创意实现发布者id),
    //                  topicName: "创意实现对应的主题名",
    //                  topicId: 1 (创意实现对应的主题id),
    //                  content: "创意实现内容",
    //                  time: "创意实现时间",
    //                  good: 20 (创意实现点赞数),
    //                  bad: 2 (创意实现的点灭数)
    //              },
    //              replyCount: 8, (回复条数)
    //              reply:
    //              [
    //                  {
    //                      replyId: 0,(回复id)
    //                      achievementId: 0,(创意实现id)
    //                      content: "回复内容",
    //                      time: "回复时间",
    //                      fromId: 0,(回复者id)
    //                      fromName: "回复者昵称"
    //                      toId: 0,(被回复者id)
    //                      toName: "被回复者昵称"
    //                  },
    //                  {
    //                      同上
    //                  },
    //                  ......
    //              ]
    //      },
    //      {
    //          同上
    //      },
    //      ......
    //  ]
    // }
    JSONObject detail(Integer id);
}
