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
    //          like: 点赞数,
    //          dislike: 点踩数,
    //          collect: 收藏
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
    //      like: 点赞数,
    //      dislike: 点踩数
    //      collect: 收藏数
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
    //                  collect: 收藏数
    //              },
    //              commentCount: 8, (评论条数)
    //              comments:
    //              [
    //                  {
    //                      comment:
    //                      {
    //                          commentId: 评论id,
    //                          achievementId: 创意实现id,
    //                          content: "评论内容",
    //                          time: 评论时间,
    //                          fromId: 评论者id,
    //                          fromName: 评论者昵称,
    //                          toId: 被评论者id,
    //                          toName: 被评论者昵称
    //                          like: 点赞数
    //                      }
    //                      replyCount: 8, (回复条数)
    //                      replies:
    //                      [
    //                          {
    //                              replyId: 0,(回复id)
    //                              commentId: 0,(创意实现id)
    //                              content: "回复内容",
    //                              time: "回复时间",
    //                              fromUid: 0,(回复者id)
    //                              fromName: "回复者昵称"
    //                              toId: 0,(被回复者id)
    //                              toName: "被回复者昵称"
    //                          },
    //                          {
    //                              同上
    //                          },
    //                          ......
    //                      ]
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

    // 请求地址: /idea/like
    // param id: 创意主题id
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


    // 请求地址: /idea/getLike
    // param id: 创意主题id
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


    // 请求地址: /idea/collect
    // param id: 创意主题id
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


    // 请求地址: /idea/getCollect
    // param id: 创意主题id
    // param userId：用户id
    // do: 获取收藏数据及状态
    // return:
    // {
    // id:xxx,
    // data:
    //  {
    //      like:xxx（点赞数量）, dislike:xxx(点踩数量), status:-1|0|1|-400 用户点赞/点踩状态 没赞没踩|踩|赞|发生错误
    //  }
    // }
    JSONObject getCollect(Integer id, Integer userId);


    // 请求地址: /idea/publish
    // param topicTitle: 对应的创意标题
    // param userId: 用户id
    // param content: 创意主题内容
    // do: 发布创意主题
    // return: 创意主题id, 失败返回null
    Integer publish(String topicTitle, Integer userId, String content);


    // 请求地址 idea/getHot
    // param number: 数量（可选，默认为10）
    // do: 返回指定数量的热度靠前的创意主题
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
    //          like: 点赞数,
    //          dislike: 点踩数,
    //          collect: 收藏
    //      },
    //      {
    //          同上
    //      },
    //      ......
    //  ]
    // }
    JSONObject getHot(@Nullable Integer number);


    // 请求地址: /idea/delete
    // param id: 创意主题id
    // do: 删除创意主题
    // return: 1|0 成功|失败
    Integer delete(Integer id);
}
