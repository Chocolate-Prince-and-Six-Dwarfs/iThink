package com.choco.ithink.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.choco.ithink.DAO.mapper.*;
import com.choco.ithink.pojo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AchievementService {
    @Resource
    private BbsAchievementMapper bbsAchievementMapper;
    @Resource
    private BbsTopicMapper bbsTopicMapper;
    @Resource
    private AchievementLikeMapper achievementLikeMapper;
    @Resource
    private AchievementCollectionMapper achievementCollectionMapper;
    @Resource
    private BbsCommentMapper bbsCommentMapper;


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

            Integer achievementId = bbsAchievementList.get(i).getAchievementId();

            // 统计点赞点踩数量
            AchievementLikeExample achievementLikeExample = new AchievementLikeExample();
            achievementLikeExample.createCriteria().andAchievementIdEqualTo(achievementId).andTypeEqualTo(true);
            Integer like = achievementLikeMapper.countByExample(achievementLikeExample);
            achievementLikeExample.clear();
            achievementLikeExample.createCriteria().andAchievementIdEqualTo(achievementId).andTypeEqualTo(false);
            Integer dislike = achievementLikeMapper.countByExample(achievementLikeExample);

            // 统计收藏数量
            AchievementCollectionExample achievementCollectionExample = new AchievementCollectionExample();
            achievementCollectionExample.createCriteria().andAchievementIdEqualTo(achievementId);
            Integer collect = achievementCollectionMapper.countByExample(achievementCollectionExample);

            // 统计评论数量
            BbsCommentExample bbsCommentExample = new BbsCommentExample();
            bbsCommentExample.createCriteria().andAchievementIdEqualTo(achievementId);
            Integer comment = bbsCommentMapper.countByExample(bbsCommentExample);

            // 拼接Json
            jsonObject.put("id", bbsAchievementList.get(i).getTopicId());
            jsonObject.put("userId", bbsAchievementList.get(i).getUserId());
            jsonObject.put("topicName", topicName);
            jsonObject.put("topicId", bbsAchievementList.get(i).getTopicId());
            jsonObject.put("content", bbsAchievementList.get(i).getAchievementContent());
            jsonObject.put("time", bbsAchievementList.get(i).getAchievementBulidtime());
            jsonObject.put("like", like);
            jsonObject.put("dislike", dislike);
            jsonObject.put("collect", collect);
            jsonObject.put("comment", comment);
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }

    // param id: 创意id
    // do: 查找指定的创意实现
    // return: 指定的创意实现
    public JSONObject getById(Integer id)
    {
        // 查找数据库创意实现
        BbsAchievementExample bbsAchievementExample = new BbsAchievementExample();
        bbsAchievementExample.createCriteria().andAchievementIdEqualTo(id);
        List<BbsAchievement> bbsAchievementList = bbsAchievementMapper.selectByExample(bbsAchievementExample);

        if(bbsAchievementList.size()!=1)
        {
            return null;
        }

        return list2JSON(bbsAchievementList).getJSONObject(0);
    }

    // param topicId: 创意主题id
    // do: 查找指定创意主题的所有的创意实现
    // return: 创意实现Json数组
    public JSONArray getByTopicId(Integer topicId)
    {
        // 查找数据库创意实现
        BbsAchievementExample bbsAchievementExample = new BbsAchievementExample();
        bbsAchievementExample.createCriteria().andTopicIdEqualTo(topicId);
        List<BbsAchievement> bbsAchievementList = bbsAchievementMapper.selectByExample(bbsAchievementExample);

        return list2JSON(bbsAchievementList);
    }


    // param id: 创意实现id
    // param userId：用户id
    // param type: false|true 踩|赞
    // do: 点赞或点踩（重复请求会取消点赞或点踩）
    // return:
    //  {
    //      like:xxx（点赞数量）, dislike:xxx(点踩数量), status:-1|0|1|-400 用户点赞/点踩状态 没赞没踩|踩|赞|发生错误
    //  }
    public JSONObject like(Integer id, Integer userId, Boolean type)
    {
        JSONObject jsonObject = new JSONObject();
        Integer status = -400;
        Integer like = 0;
        Integer dislike = 0;

        // 查询是否已经点赞或者点踩
        AchievementLikeExample achievementLikeExample = new AchievementLikeExample();
        achievementLikeExample.createCriteria().andAchievementIdEqualTo(id).andUserIdEqualTo(userId);
        List<AchievementLike> achievementLikeList = achievementLikeMapper.selectByExample(achievementLikeExample);

        AchievementLike achievementLike = new AchievementLike();

        try
        {
            switch (achievementLikeList.size()) {
                case 0:
                    // 构造关系
                    achievementLike.setType(type);
                    achievementLike.setAchievementId(id);
                    achievementLike.setUserId(userId);

                    // 插入关系
                    if (achievementLikeMapper.insertSelective(achievementLike) == 1) {
                        status = type==true ? 1 : 0;
                    } else {
                        status = -400;
                    }
                    break;
                case 1:
                    // 根据请求类型和已有关系进行操作
                    // 若请求的是已有关系则删除关系
                    if (achievementLikeList.get(0).getType() == type) {
                        achievementLikeMapper.deleteByExample(achievementLikeExample);
                        status = -1;
                    }
                    // 相反关系则更新关系
                    else {
                        achievementLike.setType(type);
                        achievementLikeMapper.updateByExampleSelective(achievementLike, achievementLikeExample);
                        status = type==true ? 1 : 0;
                    }
                    break;
                default:
                    status = -400;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            status = -400;
        }
        finally
        {
            achievementLikeExample.clear();
            achievementLikeExample.createCriteria().andAchievementIdEqualTo(id).andTypeEqualTo(true);
            like = achievementLikeMapper.countByExample(achievementLikeExample);

            achievementLikeExample.clear();
            achievementLikeExample.createCriteria().andAchievementIdEqualTo(id).andTypeEqualTo(false);
            dislike = achievementLikeMapper.countByExample(achievementLikeExample);
        }

        // 拼接json
        jsonObject.put("like", like);
        jsonObject.put("dislike", dislike);
        jsonObject.put("status", status);

        return jsonObject;
    }


    // param id: 创意实现id
    // param userId：用户id
    // do: 获取点赞数据和点赞状态
    // return:
    // {
    //      like:xxx（点赞数量）, dislike:xxx(点踩数量), status:-1|0|1|-400 用户点赞/点踩状态 没赞没踩|踩|赞|发生错误
    // }
    public JSONObject getLike(Integer id, Integer userId)
    {
        JSONObject jsonObject = new JSONObject();
        Integer status = -400;
        Integer like = 0;
        Integer dislike = 0;

        // 查询是否已经点赞或者点踩
        AchievementLikeExample achievementLikeExample = new AchievementLikeExample();
        achievementLikeExample.createCriteria().andAchievementIdEqualTo(id).andUserIdEqualTo(userId);
        List<AchievementLike> achievementLikeList = achievementLikeMapper.selectByExample(achievementLikeExample);

        try
        {
            switch (achievementLikeList.size()) {
                case 0:
                    status = -1;
                    break;
                case 1:
                    // 根据请求类型和已有关系进行操作
                    status = achievementLikeList.get(0).getType() == true ? 1 : 0;
                    break;
                default:
                    status = -400;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            status = -400;
        }
        finally
        {
            achievementLikeExample.clear();
            achievementLikeExample.createCriteria().andAchievementIdEqualTo(id).andTypeEqualTo(true);
            like = achievementLikeMapper.countByExample(achievementLikeExample);

            achievementLikeExample.clear();
            achievementLikeExample.createCriteria().andAchievementIdEqualTo(id).andTypeEqualTo(false);
            dislike = achievementLikeMapper.countByExample(achievementLikeExample);
        }

        // 拼接json
        jsonObject.put("like", like);
        jsonObject.put("dislike", dislike);
        jsonObject.put("status", status);

        return jsonObject;
    }


    // param id: 创意实现id
    // param userId：用户id
    // do: 收藏（重复请求会取消收藏）
    // return:
    // {
    //      collect:xxx（收藏数量）, status:0|1|-400 用户收藏状态 未收藏|已收藏|发生错误
    // }
    public JSONObject collect(Integer id, Integer userId)
    {
        JSONObject jsonObject = new JSONObject();
        Integer status = -400;
        Integer collect = 0;

        // 查询是否已经收藏
        AchievementCollectionExample achievementCollectionExample = new AchievementCollectionExample();
        achievementCollectionExample.createCriteria().andAchievementIdEqualTo(id).andUserIdEqualTo(userId);
        List<AchievementCollectionKey> achievementCollectionKeyList = achievementCollectionMapper.selectByExample(achievementCollectionExample);

        AchievementCollectionKey achievementCollectionKey = new AchievementCollectionKey();

        try
        {
            switch (achievementCollectionKeyList.size()) {
                case 0:
                    // 构造关系
                    achievementCollectionKey.setAchievementId(id);
                    achievementCollectionKey.setUserId(userId);

                    // 插入关系
                    if (achievementCollectionMapper.insertSelective(achievementCollectionKey) == 1) {
                        status = 1;
                    } else {
                        status = -400;
                    }
                    break;
                case 1:
                    // 若请求的是已有关系则删除关系
                    achievementCollectionMapper.deleteByExample(achievementCollectionExample);
                    status = 0;
                    break;
                default:
                    status = -400;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            status = -400;
        }
        finally
        {
            achievementCollectionExample.clear();
            achievementCollectionExample.createCriteria().andAchievementIdEqualTo(id);
            collect = achievementCollectionMapper.countByExample(achievementCollectionExample);
        }

        // 拼接json
        jsonObject.put("collect", collect);
        jsonObject.put("status", status);

        return jsonObject;
    }


    // param id: 创意实现id
    // param userId：用户id
    // do: 获取收藏数据及收藏状态
    // return:
    // {
    //      collect:xxx（收藏数量）, status:0|1|-400 用户收藏状态 未收藏|已收藏|发生错误
    // }
    public JSONObject getCollect(Integer id, Integer userId)
    {
        JSONObject jsonObject = new JSONObject();
        Integer status = -400;
        Integer collect = 0;

        // 查询是否已经收藏
        AchievementCollectionExample achievementCollectionExample = new AchievementCollectionExample();
        achievementCollectionExample.createCriteria().andAchievementIdEqualTo(id).andUserIdEqualTo(userId);
        List<AchievementCollectionKey> achievementCollectionKeyList = achievementCollectionMapper.selectByExample(achievementCollectionExample);

        AchievementCollectionKey achievementCollectionKey = new AchievementCollectionKey();

        try
        {
            switch (achievementCollectionKeyList.size()) {
                case 0:
                    status = 0;
                    break;
                case 1:
                    status = 1;
                    break;
                default:
                    status = -400;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            status = -400;
        }
        finally
        {
            achievementCollectionExample.clear();
            achievementCollectionExample.createCriteria().andAchievementIdEqualTo(id);
            collect = achievementCollectionMapper.countByExample(achievementCollectionExample);
        }

        // 拼接json
        jsonObject.put("collect", collect);
        jsonObject.put("status", status);

        return jsonObject;
    }


    // param userId: 用户id
    // param topicId: 对应的创意主题id
    // param content: 创意实现内容
    // do: 发布创意实现
    // return: 创意实现id, 失败返回null
    public Integer publish(Integer userId, Integer topicId, String content)
    {
        // 构建实体
        BbsAchievement bbsAchievement = new BbsAchievement();
        bbsAchievement.setUserId(userId);
        bbsAchievement.setTopicId(topicId);
        bbsAchievement.setAchievementContent(content);
        bbsAchievement.setAchievementBulidtime(new Date());

        // 插入数据库
        if(bbsAchievementMapper.insertSelective(bbsAchievement) == 1)
        {
            return bbsAchievement.getAchievementId();
        }
        else
        {
            return null;
        }
    }
}
