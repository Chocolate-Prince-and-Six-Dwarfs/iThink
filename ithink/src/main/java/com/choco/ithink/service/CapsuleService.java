package com.choco.ithink.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.choco.ithink.DAO.mapper.CreativeCapsuleMapper;
import com.choco.ithink.DAO.mapper.UserMapper;
import com.choco.ithink.pojo.CreativeCapsule;
import com.choco.ithink.pojo.CreativeCapsuleExample;
import com.choco.ithink.pojo.User;
import com.choco.ithink.pojo.UserExample;
import com.choco.ithink.tool.Tool;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CapsuleService {
    @Resource
    private CreativeCapsuleMapper creativeCapsuleMapper;
    @Resource
    private UserMapper userMapper;

    // param name: 胶囊名
    // param content: 胶囊内容
    // param userId: 用户id
    // param buildTime: 创建时间（网页端不填，默认为和上传时间一致）
    // do: 将胶囊保存到服务器
    // return: 保存的胶囊id, 失败返回null
    public Integer save(String name, String content, Integer userId, @Nullable Date buildTime)
    {
        // 构建实体
        CreativeCapsule creativeCapsule = new CreativeCapsule();
        creativeCapsule.setCreativeName(name);
        creativeCapsule.setCreativeContent(content);
        creativeCapsule.setUserId(userId);
        Date date = new Date();
        creativeCapsule.setCreativeUploadtime(date);
        if(buildTime == null)
        {
            creativeCapsule.setCreativeBuildtime(date);
        }
        else
        {
            creativeCapsule.setCreativeBuildtime(buildTime);
        }

        // 插入数据库
        Integer id = null;
        try
        {
            creativeCapsuleMapper.insertSelective(creativeCapsule);
            id = creativeCapsule.getCreativeId();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            id = null;
        }

        return id;
    }


    // 请求地址: capsule/update
    // param name: 胶囊名
    // param content: 胶囊内容
    // param id: 胶囊id
    // do: 将胶囊保存到服务器
    // return: 0|1 失败|成功
    public Integer update(String name, String content, Integer id)
    {
        Integer status = 0;

        // 构建实体
        CreativeCapsule creativeCapsule = new CreativeCapsule();
        creativeCapsule.setCreativeName(name);
        creativeCapsule.setCreativeContent(Tool.delS(content));
        creativeCapsule.setCreativeId(id);
        Date date = new Date();
        creativeCapsule.setCreativeUploadtime(date);

        // 插入数据库
        try
        {
            Integer count = creativeCapsuleMapper.updateByPrimaryKeySelective(creativeCapsule);
            if(count == 1)
            {
                status = 1;
            }
            else
            {
                status = 0;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            status = 0;
        }

        return status;
    }


    // param creativeCapsuleList: 创意实现实体列表
    // do: 将实体列表转化为JSONArray
    // return: 转化后的JSONArray
    public JSONArray list2JSON(List<CreativeCapsule> creativeCapsuleList)
    {
        JSONArray jsonArray = new JSONArray();

        // 查找对应User昵称的example
        UserExample userExample = new UserExample();

        // 循环处理实体
        for(int i=0; i<creativeCapsuleList.size(); ++i)
        {
            JSONObject jsonObject = new JSONObject();

            // 查找对应用户的昵称
            userExample.clear();
            userExample.createCriteria().andUserIdEqualTo(creativeCapsuleList.get(i).getUserId());
            String userName = userMapper.selectByExample(userExample).get(0).getUserName();


            // 拼接Json
            jsonObject.put("id", creativeCapsuleList.get(i).getCreativeId());
            jsonObject.put("name", creativeCapsuleList.get(i).getCreativeName());
            jsonObject.put("content", creativeCapsuleList.get(i).getCreativeContent());
            jsonObject.put("userId", creativeCapsuleList.get(i).getUserId());
            jsonObject.put("userName", userName);
            jsonObject.put("buildTime", creativeCapsuleList.get(i).getCreativeBuildtime());
            jsonObject.put("uploadTime", creativeCapsuleList.get(i).getCreativeUploadtime());
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }


    // param id: 胶囊id
    // do: 获取胶囊
    // return:
    // {
    //  id: ,
    //  name: ,
    //  content: ,
    //  userId: ,
    //  userName: ,
    //  buildTime: ,
    //  uploadTime: 上传时间/最后一次更新时间
    // }
    public JSONObject getById(Integer id)
    {
        CreativeCapsuleExample creativeCapsuleExample = new CreativeCapsuleExample();
        creativeCapsuleExample.createCriteria().andCreativeIdEqualTo(id);

        List<CreativeCapsule> creativeCapsuleList = creativeCapsuleMapper.selectByExample(creativeCapsuleExample);

        if(creativeCapsuleList.size()!=1)
        {
            return null;
        }

        return list2JSON(creativeCapsuleList).getJSONObject(0);
    }


    // param userId: 用户id
    // do: 获取胶囊
    // return:
    // [
    //  {
    //  id: ,
    //  name: ,
    //  content: ,
    //  userId: ,
    //  userName: ,
    //  buildTime: ,
    //  uploadTime: 上传时间/最后一次更新时间
    //  },
    //  {
    //      同上
    //  },
    //  ......
    // ]
    public JSONArray getByUserId(Integer userId)
    {
        CreativeCapsuleExample creativeCapsuleExample = new CreativeCapsuleExample();
        creativeCapsuleExample.createCriteria().andUserIdEqualTo(userId);

        List<CreativeCapsule> creativeCapsuleList = creativeCapsuleMapper.selectByExample(creativeCapsuleExample);

        return list2JSON(creativeCapsuleList);
    }
}
