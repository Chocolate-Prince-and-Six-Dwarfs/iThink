package com.choco.ithink.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.choco.ithink.DAO.mapper.BbsAchievementMapper;
import com.choco.ithink.DAO.mapper.BbsTopicMapper;
import com.choco.ithink.pojo.BbsAchievement;
import com.choco.ithink.pojo.BbsAchievementExample;
import com.choco.ithink.pojo.BbsTopicExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AchievementService {
    @Resource
    private BbsAchievementMapper bbsAchievementMapper;
    @Resource
    private BbsTopicMapper bbsTopicMapper;


    // param userId: 用户id
    // do: 查找用户的创意实现
    // return: 指定用户的创意实现
    public JSONArray getByUserId(Integer userId)
    {
        // 查找数据库创意实现
        BbsAchievementExample bbsAchievementExample = new BbsAchievementExample();
        bbsAchievementExample.createCriteria().andUserIdEqualTo(userId);
        List<BbsAchievement> bbsAchievementList = bbsAchievementMapper.selectByExample(bbsAchievementExample);

        return list2JSON(bbsAchievementList);
    }


    // param bbsAchievementList: 创意实现实体列表
    // do: 将实体列表转化为JSONArray
    // return: 转化后的JSONArray
    public JSONArray list2JSON(List<BbsAchievement> bbsAchievementList)
    {
        JSONArray jsonArray = new JSONArray();

        // 查找对应Topic标题的mapper
        BbsTopicExample bbsTopicExample = new BbsTopicExample();

        // 循环处理实体
        for(int i=0; i<bbsAchievementList.size(); ++i)
        {
            JSONObject jsonObject = new JSONObject();

            // 查找对应Topic的标题
            bbsTopicExample.clear();
            bbsTopicExample.createCriteria().andTopicIdEqualTo(bbsAchievementList.get(i).getTopicId());
            String topicName = bbsTopicMapper.selectByExample(bbsTopicExample).get(0).getTopicTitle();

            // 拼接Json
            jsonObject.put("id", bbsAchievementList.get(i).getTopicId());
            jsonObject.put("userId", bbsAchievementList.get(i).getUserId());
            jsonObject.put("topicName", topicName);
            jsonObject.put("topicId", bbsAchievementList.get(i).getTopicId());
            jsonObject.put("content", bbsAchievementList.get(i).getAchievementContent());
            jsonObject.put("time", bbsAchievementList.get(i).getAchievementBulidtime());
            jsonObject.put("good", bbsAchievementList.get(i).getAchievementLikenum());
            jsonObject.put("bad", bbsAchievementList.get(i).getAchievementUnlikenum());
            jsonObject.put("reply", bbsAchievementList.get(i).getAchievementCommentnum());
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }
}
