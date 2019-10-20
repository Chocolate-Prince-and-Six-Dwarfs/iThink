package com.choco.ithink.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.choco.ithink.interfaces.UserInterface;
import com.choco.ithink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userInfo")
public class UserController implements UserInterface {
    @Autowired
    UserService userService;

    // 请求地址： userInfo/isMatch
    // param email: 登录键(邮箱)
    // param pwd: 用户密码
    // do: 检查邮箱和密码是否匹配
    // return: 匹配返回json格式{status: 1}, 若不匹配但用户已存在则返回{status: 0}, 若用户不存在则返回{status: -1}, 输入不符合规范返回{status: -2}, 未知错误返回{status: -400}
    @RequestMapping("/isMatch")
    @ResponseJSONP
    public JSONObject isMatch(String email, String pwd)
    {
        // 检查有效性
        Boolean emailAvailability = checkEmail(email);
        Boolean pwdAvailability = checkPwd(pwd);

        // 预定义状态
        Integer status = -400;
        String keyName = "status";

        try
        {
            if (emailAvailability && pwdAvailability) {
                // 如果符合规范

                // 检查匹配
                status = userService.checMatching(email, pwd);
            } else {
                // 如果不符合规范
                status = -2;
            }
        }
        catch(Exception e)
        {
            status = -400;
        }

        // 拼接json
        JSONObject result = new JSONObject();
        result.put(keyName, status);

        // 返回结果
        return result;
    }


    // 请求地址： userInfo/checkEmail
    // param email: 登录键(邮箱)
    // do: 检查邮箱是否符合规范
    // return: 符合返回true, 不符合返回false
    @RequestMapping("/checkEmail")
    public Boolean checkEmail(String email)
    {
        return userService.checkString(email, userService.getEmailPattern());
    }


    // 请求地址： userInfo/checkPwd
    // param pwd: 密码
    // do: 检查密码是否符合规范
    // return: 符合返回true, 不符合返回false
    @RequestMapping("/checkPwd")
    public Boolean checkPwd(String pwd)
    {
        return userService.checkString(pwd, userService.getPwdPattern());
    }

    // 请求地址： userInfo/register
    // param username: 用户名称
    // param pwd: 用户密码
    // param sex: 性别
    // param email: 邮箱
    // param birthday: 生日
    // param phone: 手机号(可选)
    // do: 用户注册
    // return: 注册成功返回json格式{status: 1}, 若用户已存在则返回{status: 0}, 输入不符合规范返回{status: -2}, 未知错误返回{status: -400}
    @RequestMapping("/register")
    @ResponseJSONP
    public JSONObject register(String username, String pwd, String sex, String email, String birthday, @Nullable String phone)
    {
        // 检查有效性
        Boolean emailAvailability = checkEmail(email);
        Boolean pwdAvailability = checkPwd(pwd);

        // 预定义状态
        Integer status = -400;
        String keyName = "status";

        try
        {
            if (emailAvailability && pwdAvailability) {
                // 如果符合规范

                // 注册
                status = userService.userRegister(username, pwd, sex, email, birthday, phone);
            } else {
                // 如果不符合规范
                status = -2;
            }
        }
        catch(Exception e)
        {
            status = -400;
        }

        // 拼接json
        JSONObject result = new JSONObject();
        result.put(keyName, status);

        // 返回结果
        return result;
    }
}
