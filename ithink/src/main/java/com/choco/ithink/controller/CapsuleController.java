package com.choco.ithink.controller;

import com.choco.ithink.DAO.mapper.CreativeCapsuleMapper;
import com.choco.ithink.interfaces.CapsuleInterface;
import com.choco.ithink.pojo.CreativeCapsule;
import com.choco.ithink.service.CapsuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/capsule")
public class CapsuleController implements CapsuleInterface {
    @Autowired
    private CapsuleService capsuleService;

    // 请求地址: capsule/save
    // param name: 胶囊名
    // param content: 胶囊内容
    // param userId: 用户id
    // param buildTime: 创建时间（网页端不填，默认为和上传时间一致）
    // do: 将胶囊保存到服务器
    // return: 保存的胶囊id, 失败返回null
    @RequestMapping("/save")
    @ResponseBody
    public Integer save(String name, String content, Integer userId, @Nullable Date buildTime)
    {
        Integer id = capsuleService.save(name, content, userId, buildTime);

        return id;
    }
}
