package com.choco.ithink.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.choco.ithink.interfaces.CreativeIdeaInterface;
import com.choco.ithink.service.AchievementService;
import com.choco.ithink.service.CreativeIdeaService;
import com.choco.ithink.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/idea")
public class CreativeIdeaController implements CreativeIdeaInterface {
    @Autowired
    private CreativeIdeaService creativeIdeaService;
    @Autowired
    private AchievementService achievementService;
    @Autowired
    private ReplyService replyService;


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
    //          like: 收藏数
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
    @RequestMapping("/detail")
    @ResponseJSONP
    public JSONObject detail(Integer id)
    {
        JSONObject jsonObject = new JSONObject();

        // 搜索与统计
        JSONObject topic = creativeIdeaService.getById(id);
        JSONArray achievementArrary = achievementService.getByTopicId(topic.getInteger("id"));
        JSONArray achievements = new JSONArray();
        Integer achievementCount = achievementArrary.size();
        for(int i=0; i<achievementCount; ++i)
        {
            JSONObject tmp = new JSONObject();
            tmp.put("achievement", achievementArrary.getJSONObject(i));
            JSONArray reply = replyService.getByAchievementId(achievementArrary.getJSONObject(i).getInteger("id"));
            Integer replyCount = reply.size();
            tmp.put("replyCount", replyCount);
            tmp.put("reply", reply);

            achievements.add(tmp);
        }


        // 拼接字符串
        jsonObject.put("topic", topic);
        jsonObject.put("achievementCount", achievementCount);
        jsonObject.put("achievements", achievements);
        return jsonObject;
    }
}
