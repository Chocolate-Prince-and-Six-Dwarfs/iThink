package com.choco.ithink.controller;

import com.alibaba.fastjson.JSONObject;
import com.choco.ithink.interfaces.UserInterface;
import com.choco.ithink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController implements UserInterface {
    @Autowired
    UserService userService;

    // 请求地址： isMatch
    // param username: 登录键
    // param pwd: 用户密码
    // do: 检查用户名是否匹配
    // return: 匹配返回json格式{status: 1}, 若不匹配但用户已存在则返回{status: 0}, 若用户不存在则返回{status: -1}
    @RequestMapping("/isMatch")
    public JSONObject isMatch(String username, String pwd)
    {
        // 检查匹配
        Integer status = userService.checkUserPwd(username, pwd);
        String keyName = "status";
        // 拼接json
        JSONObject result = new JSONObject();
        result.put(keyName, status);
        return result;
    }
}
