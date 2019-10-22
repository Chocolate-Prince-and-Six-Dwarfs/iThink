package com.choco.ithink.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.choco.ithink.DAO.mapper.BbsTopicMapper;
import com.choco.ithink.pojo.BbsTopic;
import com.choco.ithink.pojo.BbsTopicExample;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import org.elasticsearch.index.mapper.ParseContext;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Service
public class CreativeIdeaService {
    // mapper类，用于数据库查询
    @Resource
    private BbsTopicMapper bbsTopicMapper;
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


    // param resultList: jest搜索得到的列表
    // do： 将列表构建为JSON数组对象
    // return: 构建的json数组对象
    public JSONArray list2JSON(List<BbsTopic> resultList)
    {
        JSONArray jsonArray = new JSONArray();

        // 循环处理实体
        for(int i=0; i<resultList.size(); ++i)
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", resultList.get(i).getTopicId());
            jsonObject.put("tittle", resultList.get(i).getTopicTitle());
            jsonObject.put("content", resultList.get(i).getTopicContent());
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }
}
