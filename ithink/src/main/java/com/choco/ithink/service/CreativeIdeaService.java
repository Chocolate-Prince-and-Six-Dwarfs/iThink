package com.choco.ithink.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.choco.ithink.DAO.mapper.*;
import com.choco.ithink.pojo.*;
import com.choco.ithink.tool.Tool;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.Delete;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;


@Service
public class CreativeIdeaService {
    // mapper类，用于数据库查询
    @Resource
    private BbsTopicMapper bbsTopicMapper;
    @Resource
    private UserMapper userMapper;
    // 用于搜索的jest客户端
    @Resource
    private JestClient jestClient;
    @Resource
    private TopicLikeMapper topicLikeMapper;
    @Resource
    private TopicCollectionMapper topicCollectionMapper;
    @Resource
    private BbsAchievementMapper bbsAchievementMapper;
//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private AchievementService achievementService;


    // param bbsTopicList: mybatis查询数据库得到的实体列表
    // do： 将实体列表保存到ElasticSearch搜索仓库中
    public void saveBbsTopic(List<BbsTopic> bbsTopicList) {
        // 构建保存器
        Bulk.Builder bulk = new Bulk.Builder();
        for(BbsTopic bbsTopic : bbsTopicList) {
            Index index = new Index.Builder(bbsTopic).index(BbsTopic.INDEX).type(BbsTopic.TYPE).refresh(true).build();
            bulk.addAction(index);
        }
        try {
            // 执行保存
            JestResult result = jestClient.execute(bulk.build());
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // param bbsTopicList: mybatis查询数据库得到的实体列表
    // do： 将实体列表从搜索仓库中删除
    public void delBbsTopic(List<BbsTopic> bbsTopicList) {
        // 构建删除器
        Bulk.Builder bulk = new Bulk.Builder();
        for(BbsTopic bbsTopic : bbsTopicList) {
            Delete delete = new Delete.Builder(bbsTopic.getTopicId().toString()).index(BbsTopic.INDEX).type(BbsTopic.TYPE).refresh(true).build();
            bulk.addAction(delete);
        }
        try {
            // 执行删除
            JestResult result = jestClient.execute(bulk.build());
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // param keyword: 搜索关键字
    // do： 根据关键字搜索创意
    // return: 匹配到的结果，没有则返回null
    public JSONArray search(String keyword)
    {
        // 查询数据库
        List<BbsTopic> bbsTopics = bbsTopicMapper.selectByExample(new BbsTopicExample());
        if(bbsTopics.size()<=0)
        {
            return null;
        }

        // 将数据存入搜索仓库
        saveBbsTopic(bbsTopics);

        // 搜索器构建
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 搜索关键字
        searchSourceBuilder.query(QueryBuilders.queryStringQuery(keyword));
        // size
        searchSourceBuilder.size(10000);
        // 去重
        CardinalityAggregationBuilder cardinalityAggregationBuilder = AggregationBuilders.cardinality("id").field("topicId");
        searchSourceBuilder.aggregation(cardinalityAggregationBuilder);
        // 搜索字段范围
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery(keyword, "topicTitle", "topicContent"));
        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(BbsTopic.INDEX).addType(BbsTopic.TYPE).build();
        try {
            // 执行搜索
            JestResult result = jestClient.execute(search);
            List<BbsTopic> resultList =  result.getSourceAsObjectList(BbsTopic.class);

//            if(resultList.size()==0)
//            {
//                result = jestClient.execute(search);
//                resultList =  result.getSourceAsObjectList(BbsTopic.class);
//            }

            return list2JSON(resultList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


    // param bbsReplyList: 实体列表
    // do： 将列表构建为JSON数组对象
    // return: 构建的json数组对象
    public JSONArray list2JSON(List<BbsTopic> bbsTopicList)
    {
        JSONArray jsonArray = new JSONArray();

        // 循环处理实体
        for(int i=0; i<bbsTopicList.size(); ++i)
        {
            // 获取publisher昵称
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUserIdEqualTo(bbsTopicList.get(i).getUserId());
            List<User> userList = userMapper.selectByExample(userExample);
            String publisher = "";
            if(userList.size()==1)
            {
                publisher = userList.get(0).getUserName();
            }

            Integer topicId = bbsTopicList.get(i).getTopicId();

            // 统计点赞点踩数量
            TopicLikeExample topicLikeExample = new TopicLikeExample();
            topicLikeExample.createCriteria().andTopicIdEqualTo(topicId).andTypeEqualTo(true);
            Integer like = topicLikeMapper.countByExample(topicLikeExample);
            topicLikeExample.clear();
            topicLikeExample.createCriteria().andTopicIdEqualTo(topicId).andTypeEqualTo(false);
            Integer dislike = topicLikeMapper.countByExample(topicLikeExample);

            // 统计收藏数量
            TopicCollectionExample topicCollectionExample = new TopicCollectionExample();
            topicCollectionExample.createCriteria().andTopicIdEqualTo(topicId);
            Integer collect = topicCollectionMapper.countByExample(topicCollectionExample);

            // 统计实现数量
            BbsAchievementExample bbsAchievementExample = new BbsAchievementExample();
            bbsAchievementExample.createCriteria().andTopicIdEqualTo(topicId);
            Integer achievement = bbsAchievementMapper.countByExample(bbsAchievementExample);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", bbsTopicList.get(i).getTopicId());
            jsonObject.put("title", bbsTopicList.get(i).getTopicTitle());
            jsonObject.put("content", bbsTopicList.get(i).getTopicContent());
            jsonObject.put("time", bbsTopicList.get(i).getTopicBuildtime());
            jsonObject.put("publisherId", bbsTopicList.get(i).getUserId());
            jsonObject.put("publisher", publisher);
            jsonObject.put("like", like);
            jsonObject.put("dislike", dislike);
            jsonObject.put("collect", collect);
            jsonObject.put("achievement", achievement);
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }

    // param userId: 用户id
    // do: 查找用户发布的创意主题
    // return: 创意主题JSONArray数组
    public JSONArray getByUserId(Integer userId)
    {
        // 查找数据库
        BbsTopicExample bbsTopicExample = new BbsTopicExample();
        bbsTopicExample.createCriteria().andUserIdEqualTo(userId);
        List<BbsTopic> bbsTopicList = bbsTopicMapper.selectByExample(bbsTopicExample);
        return list2JSON(bbsTopicList);
    }


    // param id: 创意主题id
    // do: 查找对应的创意主题
    // return: 创意主题
    public JSONObject getById(Integer id)
    {
        // 查找数据库
        BbsTopicExample bbsTopicExample = new BbsTopicExample();
        bbsTopicExample.createCriteria().andTopicIdEqualTo(id);
        List<BbsTopic> bbsTopicList = bbsTopicMapper.selectByExample(bbsTopicExample);

        if(bbsTopicList.size()!=1)
        {
            return null;
        }

        return list2JSON(bbsTopicList).getJSONObject(0);
    }


    // param n: 数量（可选，默认为10）
    // do: 返回指定数量的创意主题
    // return: 创意主题，格式如下
    //  [
    //      {
    //          id: 0,
    //          title: "创意名称",
    //          content: "创意内容",
    //          time: "时间",
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
    public JSONArray loadN(@Nullable Integer n)
    {
        Integer number = 0;
        if(n==null)
        {
            number = 10;
        }
        else
        {
            number = n;
        }
        BbsTopicExample bbsTopicExample = new BbsTopicExample();
        bbsTopicExample.setOrderByClause("Rand()");
        List<BbsTopic> bbsTopicList = bbsTopicMapper.selectByExample(bbsTopicExample);

        if(bbsTopicList.size()>=number)
        {
            return list2JSON(bbsTopicList.subList(0, number));
        }
        else
        {
            return list2JSON(bbsTopicList);
        }

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
        TopicLikeExample topicLikeExample = new TopicLikeExample();
        topicLikeExample.createCriteria().andTopicIdEqualTo(id).andUserIdEqualTo(userId);
        List<TopicLike> topicLikeList = topicLikeMapper.selectByExample(topicLikeExample);


        TopicLike topicLike = new TopicLike();

        try
        {
            switch (topicLikeList.size()) {
                case 0:
                    // 构造关系
                    topicLike.setType(type);
                    topicLike.setTopicId(id);
                    topicLike.setUserId(userId);

                    // 插入关系
                    if (topicLikeMapper.insertSelective(topicLike) == 1) {
                        status = type==true ? 1 : 0;
                    } else {
                        status = -400;
                    }
                    break;
                case 1:
                    // 根据请求类型和已有关系进行操作
                    // 若请求的是已有关系则删除关系
                    if (topicLikeList.get(0).getType() == type) {
                        topicLikeMapper.deleteByExample(topicLikeExample);
                        status = -1;
                    }
                    // 相反关系则更新关系
                    else {
                        topicLike.setType(type);
                        topicLikeMapper.updateByExampleSelective(topicLike, topicLikeExample);
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
            topicLikeExample.clear();
            topicLikeExample.createCriteria().andTopicIdEqualTo(id).andTypeEqualTo(true);
            like = topicLikeMapper.countByExample(topicLikeExample);

            topicLikeExample.clear();
            topicLikeExample.createCriteria().andTopicIdEqualTo(id).andTypeEqualTo(false);
            dislike = topicLikeMapper.countByExample(topicLikeExample);
        }

        // 拼接json
        jsonObject.put("like", like);
        jsonObject.put("dislike", dislike);
        jsonObject.put("status", status);

        return jsonObject;
    }


    // param id: 创意主题id
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
        TopicLikeExample topicLikeExample = new TopicLikeExample();
        topicLikeExample.createCriteria().andTopicIdEqualTo(id).andUserIdEqualTo(userId);
        List<TopicLike> topicLikeList = topicLikeMapper.selectByExample(topicLikeExample);

        try
        {
            switch (topicLikeList.size()) {
                case 0:
                    status = -1;
                    break;
                case 1:
                    // 根据请求类型和已有关系进行操作
                    status = topicLikeList.get(0).getType() == true ? 1 : 0;
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
            topicLikeExample.clear();
            topicLikeExample.createCriteria().andTopicIdEqualTo(id).andTypeEqualTo(true);
            like = topicLikeMapper.countByExample(topicLikeExample);

            topicLikeExample.clear();
            topicLikeExample.createCriteria().andTopicIdEqualTo(id).andTypeEqualTo(false);
            dislike = topicLikeMapper.countByExample(topicLikeExample);
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
        TopicCollectionExample topicCollectionExample = new TopicCollectionExample();
        topicCollectionExample.createCriteria().andTopicIdEqualTo(id).andUserIdEqualTo(userId);
        List<TopicCollection> topicCollectionList = topicCollectionMapper.selectByExample(topicCollectionExample);

        TopicCollection topicCollection = new TopicCollection();

        try
        {
            switch (topicCollectionList.size()) {
                case 0:
                    // 构造关系
                    topicCollection.setTopicId(id);
                    topicCollection.setUserId(userId);

                    // 插入关系
                    if (topicCollectionMapper.insertSelective(topicCollection) == 1) {
                        status = 1;
                    } else {
                        status = -400;
                    }
                    break;
                case 1:
                    // 若请求的是已有关系则删除关系
                    topicCollectionMapper.deleteByExample(topicCollectionExample);
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
            topicCollectionExample.clear();
            topicCollectionExample.createCriteria().andTopicIdEqualTo(id);
            collect = topicCollectionMapper.countByExample(topicCollectionExample);
        }

        // 拼接json
        jsonObject.put("collect", collect);
        jsonObject.put("status", status);

        return jsonObject;
    }

    // param id: 创意主题id
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
        TopicCollectionExample topicCollectionExample = new TopicCollectionExample();
        topicCollectionExample.createCriteria().andTopicIdEqualTo(id).andUserIdEqualTo(userId);
        List<TopicCollection> topicCollectionList = topicCollectionMapper.selectByExample(topicCollectionExample);

        TopicCollectionKey topicCollectionKey = new TopicCollectionKey();

        try
        {
            switch (topicCollectionList.size()) {
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
            topicCollectionExample.clear();
            topicCollectionExample.createCriteria().andTopicIdEqualTo(id);
            collect = topicCollectionMapper.countByExample(topicCollectionExample);
        }

        // 拼接json
        jsonObject.put("collect", collect);
        jsonObject.put("status", status);

        return jsonObject;
    }


    // param topicTitle: 对应的创意主题id
    // param userId: 用户id
    // param content: 创意主题内容
    // do: 发布创意主题
    // return: 创意主题id, 失败返回null
    public Integer publish(String topicTitle, Integer userId, String content)
    {
        // 构建实体
        BbsTopic bbsTopic = new BbsTopic();
        bbsTopic.setTopicTitle(Tool.delS(topicTitle));
        bbsTopic.setUserId(userId);
        bbsTopic.setTopicContent(Tool.delS(content));
        // 待修改
        bbsTopic.setTopicCreativecapsule(1);
        bbsTopic.setTopicBuildtime(new Date());

        // 插入数据库
        if(bbsTopicMapper.insertSelective(bbsTopic) == 1)
        {
            List<BbsTopic> tmpList = new ArrayList<BbsTopic>();
            tmpList.add(bbsTopic);
            saveBbsTopic(tmpList);
            return bbsTopic.getTopicId();
        }
        else
        {
            return null;
        }
    }


    // param n: 数量（可选，默认为10）
    // do: 返回指定数量的热度靠前的创意主题
    // return: 创意主题，格式如下
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
    public JSONArray getHot(@Nullable Integer n)
    {
        Integer number = 0;
        if(n==null)
        {
            number = 10;
        }
        else
        {
            number = n;
        }
        BbsTopicExample bbsTopicExample = new BbsTopicExample();
        List<BbsTopic> bbsTopicList = bbsTopicMapper.selectByExample(bbsTopicExample);

        // 排序
        JSONArray jsonArray = list2JSON(bbsTopicList);
        List<JSONObject> list = JSONArray.parseArray(jsonArray.toJSONString(), JSONObject.class);
        list.sort((o1, o2) -> {
            int a = o1.getInteger("collect") * 5 + o1.getInteger("like") + o1.getInteger("dislike");
            int b = o2.getInteger("collect") * 5 + o2.getInteger("like") + o2.getInteger("dislike");
            if (a < b) {
                return 1;
            } else if (a == b) {
                return 0;
            } else
                return -1;
        });
        jsonArray = JSONArray.parseArray(list.toString());

        if(jsonArray.size()>=number)
        {
            JSONArray tmp = new JSONArray();
            for(int i=0; i<number; ++i)
            {
                 tmp.add(jsonArray.get(i));
            }
            return tmp;
        }
        else
        {
            return jsonArray;
        }
    }


    // param id: 创意主题id
    // do: 删除创意主题
    // return: 1|0 成功|失败
    public Integer delete(Integer id)
    {
        Integer status = 0;

        try
        {
            // 删除创意主题点赞和点踩
            TopicLikeExample topicLikeExample = new TopicLikeExample();
            topicLikeExample.createCriteria().andTopicIdEqualTo(id);
            topicLikeMapper.deleteByExample(topicLikeExample);

            // 删除创意主题的收藏
            TopicCollectionExample topicCollectionExample = new TopicCollectionExample();
            topicCollectionExample.createCriteria().andTopicIdEqualTo(id);
            topicCollectionMapper.deleteByExample(topicCollectionExample);

            // 删除创意实现
            BbsAchievementExample bbsAchievementExample1 = new BbsAchievementExample();
            bbsAchievementExample1.createCriteria().andAchievementIdEqualTo(id);
            List<BbsAchievement> bbsAchievementList = bbsAchievementMapper.selectByExample(bbsAchievementExample1);
            for (BbsAchievement bbsAchievement: bbsAchievementList)
            {
                achievementService.delete(bbsAchievement.getAchievementId());
            }

            // 删除搜索仓库的文档
            List<BbsTopic> tmpList = new ArrayList<BbsTopic>();
            BbsTopic bbsTopic = new BbsTopic();
            bbsTopic.setTopicId(id);
            tmpList.add(bbsTopic);
            delBbsTopic(tmpList);

            // 删除创意主题
            BbsTopicExample bbsTopicExample = new BbsTopicExample();
            bbsTopicExample.createCriteria().andTopicIdEqualTo(id);
            status = bbsTopicMapper.deleteByExample(bbsTopicExample);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return status;
    }


    // param id: 创意主题id
    // param topicTitle: 对应的创意标题
    // param userId: 用户id
    // param content: 创意主题内容
    // do: 更新创意主题
    // return: 创意主题id, 失败返回null
    public Integer update(Integer id,String topicTitle, Integer userId, String content)
    {
        // 构建实体
        BbsTopic bbsTopic = new BbsTopic();
        bbsTopic.setTopicId(id);
        bbsTopic.setTopicTitle(Tool.delS(topicTitle));
        bbsTopic.setUserId(userId);
        bbsTopic.setTopicContent(Tool.delS(content));
        // 待修改
        bbsTopic.setTopicCreativecapsule(1);
        bbsTopic.setTopicBuildtime(new Date());

        // 插入数据库
        if(bbsTopicMapper.updateByPrimaryKeySelective(bbsTopic) == 1)
        {
            List<BbsTopic> tmpList = new ArrayList<BbsTopic>();
            tmpList.add(bbsTopic);
            delBbsTopic(tmpList);
            saveBbsTopic(tmpList);
            return bbsTopic.getTopicId();
        }
        else
        {
            return null;
        }
    }
}
