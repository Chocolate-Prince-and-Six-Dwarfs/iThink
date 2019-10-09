package com.choco.ithink.interfaces;

import com.alibaba.fastjson.JSONObject;

public interface UserInterface {
    // 请求地址： isMatch
    // param username: 登录键
    // param pwd: 用户密码
    // do: 检查用户名是否匹配
    // return: 匹配返回json格式{status: 1}, 若不匹配但用户已存在则返回{status: 0}, 若用户不存在则返回{status: -1}, 错误则返回{status: -100}
    JSONObject isMatch(String username, String pwd);
}
