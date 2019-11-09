package com.choco.ithink.service;

import com.choco.ithink.DAO.mapper.CreativeCapsuleMapper;
import com.choco.ithink.pojo.CreativeCapsule;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CapsuleService {
    @Resource
    private CreativeCapsuleMapper creativeCapsuleMapper;

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
}
