package com.choco.ithink.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.choco.ithink.DAO.mapper.BbsTopicMapper;
import com.choco.ithink.DAO.mapper.UserMapper;
import com.choco.ithink.pojo.BbsTopic;
import com.choco.ithink.pojo.BbsTopicExample;
import com.choco.ithink.pojo.User;
import com.choco.ithink.pojo.UserExample;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;


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

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", bbsTopicList.get(i).getTopicId());
            jsonObject.put("title", bbsTopicList.get(i).getTopicTitle());
            jsonObject.put("content", bbsTopicList.get(i).getTopicContent());
            jsonObject.put("time", bbsTopicList.get(i).getTopicBuildtime());
            jsonObject.put("publisherId", bbsTopicList.get(i).getUserId());
            jsonObject.put("publisher", publisher);
            jsonObject.put("like", bbsTopicList.get(i).getTopicCollectionnum());
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
    //          like: 收藏数
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
}
