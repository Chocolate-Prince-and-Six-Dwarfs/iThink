package com.choco.ithink.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.choco.ithink.interfaces.CreativeIdeaInterface;
import com.choco.ithink.service.AchievementService;
import com.choco.ithink.service.CommentService;
import com.choco.ithink.service.CreativeIdeaService;
import com.choco.ithink.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/idea")
public class CreativeIdeaController implements CreativeIdeaInterface {
    @Autowired
    private CreativeIdeaService creativeIdeaService;
    @Autowired
    private AchievementService achievementService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private CommentService commentService;


    // 请求地址 idea/search
    // param keyword: 搜索关键词
    // do: 搜索匹配的创意并返回
    // return: 匹配到的创意，格式如下
    // {
    // count: 2 （查找到的数据条数）
    // data:
    //  [
    //      {
    //          id: 0,(创意主题id)
    //          title: "创意名称",
    //          content: "创意内容",
    //          time: "时间",
    //          publisherId: 0,（发布者id）
    //          publisher: "发布者",
    //          like: 点赞数,
    //          dislike: 点踩数
    //          collect: 收藏数
    //      },
    //      {
    //          同上
    //      },
    //      ......
    //  ]
    // }
    @RequestMapping("/search")
    @ResponseJSONP
    public JSONObject search(@RequestBody String keyword)
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
    @RequestMapping("/load")
    @ResponseJSONP
    public JSONObject load(@Nullable Integer number)
    {
        JSONObject jsonObject = new JSONObject();

        // 搜索与统计
        JSONArray data = creativeIdeaService.loadN(number);
        Integer count = data.size();


        // 拼接字符串
        jsonObject.put("count", count);
        jsonObject.put("data", data);
        return jsonObject;
    }


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
    //      dislike: 点踩数,
    //      collect: 收藏数,
    //      achievement: 实现数
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
    //                  like: 20 (创意实现点赞数),
    //                  dislike: 2 (创意实现的点踩数),
    //                  collect: 2 （收藏数）,
    //                  comment: 3（评论数）
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
    //                          like: 点赞数,
    //                          dislike: 点踩数,
    //                          reply: 回复数
    //                      }
    //                      replyCount: 8, (回复条数)
    //                      replies:
    //                      [
    //                          {
    //                              replyId: 0,(回复id)
    //                              commentId: 0,(创意实现id)
    //                              content: "回复内容",
    //                              time: "回复时间",
    //                              fromId: 0,(回复者id)
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
    @RequestMapping("/detail")
    @ResponseJSONP
    public JSONObject detail(Integer id)
    {
        JSONObject jsonObject = new JSONObject();

        // 搜索与统计
        JSONObject topic = creativeIdeaService.getById(id);
        JSONArray achievementArray = achievementService.getByTopicId(topic.getInteger("id"));
        JSONArray achievements = new JSONArray();
        Integer achievementCount = achievementArray.size();

        // 遍历创意实现
        for(int i=0; i<achievementCount; ++i)
        {
            JSONObject tmpAchievement = new JSONObject();
            tmpAchievement.put("achievement", achievementArray.getJSONObject(i));
            JSONArray commentArray = commentService.getByAchievementId(achievementArray.getJSONObject(i).getInteger("id"));
            JSONArray comments = new JSONArray();
            Integer commentCount = commentArray.size();
            tmpAchievement.put("commentCount", commentCount);

            // 遍历评论
            for(int j=0; j<commentCount; ++j)
            {
                JSONObject tmpComment = new JSONObject();
                tmpComment.put("comment", commentArray.getJSONObject(j));
                JSONArray replies = replyService.getByCommentId(commentArray.getJSONObject(j).getInteger("commentId"));
                Integer replyCount =replies.size();

                tmpComment.put("replyCount", replyCount);
                tmpComment.put("replies", replies);

                comments.add(tmpComment);
            }

            tmpAchievement.put("comments", comments);

            achievements.add(tmpAchievement);
        }


        // 拼接字符串
        jsonObject.put("topic", topic);
        jsonObject.put("achievementCount", achievementCount);
        jsonObject.put("achievements", achievements);
        return jsonObject;
    }

    // 请求地址: /idea/like
    // param id: 创意实现id
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
    @RequestMapping("/like")
    @ResponseJSONP
    public JSONObject like(Integer id, Integer userId, Boolean type)
    {
        JSONObject jsonObject = new JSONObject();

        // 搜索与统计
        JSONObject data = creativeIdeaService.like(id, userId, type);


        // 拼接字符串
        jsonObject.put("id", id);
        jsonObject.put("data", data);
        return jsonObject;
    }


    // 请求地址: /idea/like
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
    @RequestMapping("/getLike")
    @ResponseJSONP
    public JSONObject getLike(Integer id, Integer userId)
    {
        JSONObject jsonObject = new JSONObject();

        // 搜索与统计
        JSONObject data = creativeIdeaService.getLike(id, userId);


        // 拼接字符串
        jsonObject.put("id", id);
        jsonObject.put("data", data);
        return jsonObject;
    }


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
    @RequestMapping("/collect")
    @ResponseJSONP
    public JSONObject collect(Integer id, Integer userId)
    {
        JSONObject jsonObject = new JSONObject();

        // 搜索与统计
        JSONObject data = creativeIdeaService.collect(id, userId);


        // 拼接字符串
        jsonObject.put("id", id);
        jsonObject.put("data", data);
        return jsonObject;
    }


    // 请求地址: /idea/getCollect
    // param id: 创意主题id
    // param userId：用户id
    // do: 获取收藏数据及收藏状态
    // return:
    // {
    // id:xxx,
    // data:
    //  {
    //      collect:xxx（收藏数量）, status:0|1|-400 用户收藏状态 未收藏|已收藏|发生错误
    //  }
    // }
    @RequestMapping("/getCollect")
    @ResponseJSONP
    public JSONObject getCollect(Integer id, Integer userId)
    {
        JSONObject jsonObject = new JSONObject();

        // 搜索与统计
        JSONObject data = creativeIdeaService.getCollect(id, userId);


        // 拼接字符串
        jsonObject.put("id", id);
        jsonObject.put("data", data);
        return jsonObject;
    }


    // 请求地址: /idea/publish
    // param topicTitle: 对应的创意主题id
    // param userId: 用户id
    // param content: 创意主题内容
    // do: 发布创意主题
    // return: 创意主题id, 失败返回null
    @RequestMapping("/publish")
    @ResponseJSONP
    public Integer publish(@Nullable Integer capsuleId, String topicTitle, Integer userId, String content)
    {
        return creativeIdeaService.publish(capsuleId, topicTitle, userId, content);
    }


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
    @RequestMapping("/getHot")
    @ResponseJSONP
    public JSONObject getHot(@Nullable Integer number)
    {
        JSONObject jsonObject = new JSONObject();

        // 搜索与统计
        JSONArray data = creativeIdeaService.getHot(number);
        Integer count = data.size();


        // 拼接字符串
        jsonObject.put("count", count);
        jsonObject.put("data", data);
        return jsonObject;
    }

    // 请求地址: /idea/delete
    // param id: 创意主题id
    // do: 删除创意主题
    // return: 1|0 成功|失败
    @RequestMapping("/delete")
    @ResponseBody
    public Integer delete(Integer id)
    {
        return creativeIdeaService.delete(id);
    }

    // 请求地址: /idea/update
    // param id: 创意主题id
    // param topicTitle: 对应的创意标题
    // param userId: 用户id
    // param content: 创意主题内容
    // do: 更新创意主题
    // return: 创意主题id, 失败返回null
    @RequestMapping("/update")
    @ResponseBody
    public Integer update(Integer id,String topicTitle, Integer userId, String content)
    {
        return creativeIdeaService.update(id, topicTitle, userId, content);
    }


    // 请求地址: /idea/getAchievementUserList
    // param id: 创意主题id
    // do: 查找该创意主题下创意实现的作者列表
    // return:
    //  {
    //      id: 1,
    //      count: 2 （查找到的数据条数）,
    //      data:
    //      [
    //          {
    //              id: 1
    //              head: 头像,
    //              name: "用户名"
    //          },
    //          {
    //              同上
    //          },
    //          ......
    //      ]
    //  }
    @RequestMapping("/getAchievementUserList")
    @ResponseBody
    public JSONObject getAchievementUserList(Integer id)
    {
        // 初始化
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        Integer count = 0;
        JSONArray data = new JSONArray();

        // 获取数据
        data = creativeIdeaService.getAchievementUserList(id);
        count = data.size();

        // 拼接JSON
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count", count);
        jsonObject.put("data", data);
        return jsonObject;
    }
}
