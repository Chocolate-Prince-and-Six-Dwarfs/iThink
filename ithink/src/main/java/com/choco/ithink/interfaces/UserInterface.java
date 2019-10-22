package com.choco.ithink.interfaces;

import com.alibaba.fastjson.JSONObject;
import org.springframework.lang.Nullable;

public interface UserInterface {
    // 请求地址： userInfo/isMatch
    // param email: 登录键(邮箱)
    // param pwd: 用户密码
    // do: 检查邮箱和密码是否匹配
    // return: 匹配返回json格式{status: 1}, 若不匹配但用户已存在则返回{status: 0}, 若用户不存在则返回{status: -1}, 输入不符合规范返回{status: -2}, 未知错误返回{status: -400}
    JSONObject isMatch(String email, String pwd);

    // 请求地址： userInfo/checkEmail
    // param email: 登录键(邮箱)
    // do: 检查邮箱是否符合规范
    // return: 符合返回true, 不符合返回false
    Boolean checkEmail(String email);

    // 请求地址： userInfo/checkPwd
    // param pwd: 密码
    // do: 检查密码是否符合规范
    // return: 符合返回true, 不符合返回false
    Boolean checkPwd(String pwd);

    // 请求地址： userInfo/register
    // param username: 用户名称
    // param pwd: 用户密码
    // param sex: 性别
    // param email: 邮箱
    // param birthday: 生日
    // param phone: 手机号(可选)
    // do: 用户注册
    // return: 注册成功返回json格式{status: 1}, 若用户已存在则返回{status: 0}, 输入不符合规范返回{status: -2}, 未知错误返回{status: -400}
    JSONObject register(String username, String pwd, String sex, String email, String birthday, @Nullable String phone);
}
