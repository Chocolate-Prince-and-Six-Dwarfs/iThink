package com.choco.ithink.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.choco.ithink.interfaces.UserInterface;
import com.choco.ithink.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController implements UserInterface {
    @Autowired
    private UserService userService;
    @Autowired
    private CreativeIdeaService creativeIdeaService;
    @Autowired
    private AchievementService achievementService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private CapsuleService capsuleService;

    // 请求地址： user/login
    // param email: 登录键(邮箱)
    // param pwd: 用户密码
    // do: 检查邮箱和密码是否匹配
    // return: 匹配返回json格式{status: 1}, 若不匹配但用户已存在则返回{status: 0}, 若用户不存在则返回{status: -1}, 输入不符合规范返回{status: -2}, 未知错误返回{status: -400}
    @RequestMapping("/login")
    @ResponseJSONP
    public JSONObject login(String email, String pwd, HttpServletRequest request)
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
                status = userService.checkMatching(email, pwd);
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

        // 设置session
        if(status == 1)
        {
            sessionService.setLoginUser(request.getSession(), email);
        }

        // 返回结果
        return result;
    }

    //请求地址： user/wxlogin
    // param email: 登录键(邮箱)
    // param pwd: 用户密码
    // do:
    /*@RequestMapping("/wxlogin")
    @ResponseBody
    public Integer wxlogin (String email, String pwd,HttpServletRequest request) {
        System.out.println("微信小程序调用接口！！！邮箱:" + email + "密码:" + pwd);
        boolean wxlogin = userService.wxlogin(email, pwd);
        if (wxlogin) {
            if(sessionService.isLogin(request.getSession()))
            {
                return sessionService.getLoginId(request.getSession());
            }
            else
            {
                return null;
            }
        }
        return null;
    }*/


    // 请求地址： user/logout
    // do: 退出登录
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request)
    {
        sessionService.logout(request.getSession());
        return "redirect:/";
    }


    // 请求地址： user/checkEmail
    // param email: 登录键(邮箱)
    // do: 检查邮箱是否符合规范
    // return: 符合返回true, 不符合返回false
    @RequestMapping("/checkEmail")
    @ResponseBody
    public Boolean checkEmail(String email)
    {
        return userService.checkString(email, userService.getEmailPattern());
    }


    // 请求地址： user/checkPwd
    // param pwd: 密码
    // do: 检查密码是否符合规范
    // return: 符合返回true, 不符合返回false
    @RequestMapping("/checkPwd")
    @ResponseBody
    public Boolean checkPwd(String pwd)
    {
        return userService.checkString(pwd, userService.getPwdPattern());
    }

    // 请求地址： user/checkName
    // param name: 用户名
    // do: 检查用户名是否符合规范
    // return: 符合返回true, 不符合返回false
    @RequestMapping("/checkName")
    @ResponseBody
    public Boolean checkName(String name)
    {
        return userService.checkString(name, userService.getNamePattern());
    }

    // 请求地址： user/register
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
        Boolean nameAvailability = checkName(username);

        // 预定义状态
        Integer status = -400;
        String keyName = "status";

        try
        {
            if (emailAvailability && pwdAvailability && nameAvailability) {
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


    // 请求地址： user/getInfoById
    // param id: 用户id
    // param opinion: 选项 ("topic"|"comment"|"fans"|"follows")
    // do: 查找用户的相关数据
    // return: 根据选项返回以下内容
    //  topic
    //  {
    //      userId: 1,
    //      opinion: "topic",
    //      count: 2 （查找到的数据条数）,
    //      data:
    //      [
    //          {
    //              id: 0
    //              title: "创意名称",
    //              content: "创意内容",
    //              like: 收藏数
    //          },
    //          {
    //              同上
    //          },
    //          ......
    //      ]
    //  }
    //  achievement
    //  {
    //      userId: 1,
    //      opinion: "achievement",
    //      count: 2 （查找到的数据条数）,
    //      data:
    //      [
    //          {
    //              id: 1 (创意实现id),
    //              userId: 1 (创意实现发布者id),
    //              topicName: "创意实现对应的主题名",
    //              topicId: 1 (创意实现对应的主题id),
    //              content: "创意实现内容",
    //              time: "创意实现时间",
    //              good: 20 (创意实现点赞数),
    //              bad: 2 (创意实现的点灭数),
    //              reply: 3 （创意实现的回复数）
    //          },
    //          {
    //              同上
    //          },
    //          ......
    //      ]
    //  }
    //  fans
    //  {
    //      userId: 1,
    //      opinion: "fans",
    //      count: 2 （查找到的数据条数）,
    //      data:
    //      [
    //          {
    //              id: 1
    //              head: 头像,
    //              name: "用户名"
    //          },
    //          {
    //              同上
    //          },
    //          ......
    //      ]
    //  }
    //  follows
    //  {
    //      userId: 1,
    //      opinion: "follows",
    //      count: 2 （查找到的数据条数）,
    //      data:
    //      [
    //          {
    //              id: 1
    //              head: 头像,
    //              name: "用户名"
    //          },
    //          {
    //              同上
    //          },
    //          ......
    //      ]
    //  }
    @RequestMapping("/getInfoByUserId")
    @ResponseJSONP
    public JSONObject getInfoByUserId(Integer id, String opinion)
    {
        // 初始化
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", id);
        jsonObject.put("opinion", opinion);
        Integer count = 0;
        JSONArray data = new JSONArray();

        // 根据选项获取数据
        switch(opinion)
        {
            case "topic":
                data = creativeIdeaService.getByUserId(id);
                count = data.size();
                break;
            case "achievement":
                data = achievementService.getByUserId(id);
                count = data.size();
                break;
            case "fans":
                data = userService.getFansById(id);
                count = data.size();
                break;
            case "follows":
                data = userService.getFollowsById(id);
                count = data.size();
                break;
            default:
                count = 0;
                data = null;
        }

        // 拼接JSON
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count", count);
        jsonObject.put("data", data);
        return jsonObject;
    }

    // 请求地址: user/updateInfo
    // param id: 用户id
    // param head: 头像（可选）
    // param name: 用户昵称（可选）
    // param sex: 用户性别（可选）
    // param birthday: 出生日期（可选）
    // param phone: 手机号（可选）
    // param address: 居住地（可选）
    // param industry: 行业（可选）
    // param school: 学校（可选）
    // param introduction: 介绍（可选）
    // do: 更新用户信息
    // return: 成功返回{id:用户id, status:1}, 失败返回{id:用户id, status:0}
    @RequestMapping("/updateInfo")
    @ResponseJSONP
    public JSONObject updateInfo(Integer id, @Nullable MultipartFile head, @Nullable String name, @Nullable String sex,
                          @Nullable String birthday, @Nullable String phone, @Nullable String address, @Nullable String industry,
                          @Nullable String school, @Nullable String introduction)
    {
        JSONObject jsonObject = new JSONObject();
        Integer status = 0;

        // 检查有效性
        Boolean nameAvailability;
        if(name!=null)
        {
            nameAvailability = checkName(name);
        }
        else
        {
            nameAvailability = true;
        }
        if(nameAvailability)
        {
            // 更新
            status = userService.updateInfoById(id, head, name, sex, birthday, phone,
                    address, industry, school, introduction);
        }

        // 拼接字符串
        jsonObject.put("id", id);
        jsonObject.put("status", status);
        return jsonObject;
    }

    // 请求地址: user/getById
    // param id: 用户id
    // do: 获取用户除了密码以外的信息
    // return:
    // 成功:
    // {
    //  id: ,
    //  name: ,
    //  sex: ,
    //  email: ,
    //  birthday: ,
    //  phone: ,
    //  credit: 信誉积分,
    //  head: 头像
    //  address: ,
    //  industry: 职业,
    //  school: ,
    //  introduction
    // }
    @RequestMapping("/getById")
    @ResponseJSONP
    public JSONObject getById(Integer id)
    {
        return userService.getById(id);
    }


    // 请求地址: user/getLoginId
    // do: 获取当前登录用户的id
    // return: 用户id
    @RequestMapping("/getLoginId")
    @ResponseBody
    public Integer getLoginId(HttpServletRequest request)
    {
        if(sessionService.isLogin(request.getSession()))
        {
            return sessionService.getLoginId(request.getSession());
        }
        else
        {
            return null;
        }
    }


    // 请求地址: user/getCollectById
    // param id: 用户id
    // do: 获取用户的创意主题和创意实现收藏列表(只返回id)
    // return:
    // {
    //      id: ,
    //      topic:  [0, 1, 3, 5]
    //      achievement: [0, 3, 8]
    // }
    @RequestMapping("/getCollectById")
    @ResponseJSONP
    public JSONObject getCollectById(Integer id)
    {
        JSONObject jsonObject = new JSONObject();

        // 搜索与统计
        JSONArray topic = userService.getCollectTopicById(id);
        JSONArray achievement = userService.getCollectAchievementById(id);

        // 拼接字符串
        jsonObject.put("id", id);
        jsonObject.put("topic", topic);
        jsonObject.put("achievement", achievement);
        return jsonObject;
    }


    // 请求地址: /user/getCapsuleById
    // param id: 用户id
    // do: 获取胶囊
    // return:
    // {
    // id: xxx,
    // count: ,
    // data:
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
    // }
    @RequestMapping("/getCapsuleById")
    @ResponseJSONP
    public JSONObject getCapsuleById(Integer id)
    {
        JSONObject jsonObject = new JSONObject();

        // 搜索与统计
        JSONArray data = capsuleService.getByUserId(id);
        Integer count = data.size();

        // 拼接字符串
        jsonObject.put("id", id);
        jsonObject.put("count", count);
        jsonObject.put("data", data);
        return jsonObject;
    }


    // 请求地址 /user/follow
    // param userId: 用户id
    // param followId: 被关注id
    // do: 添加关注
    // return 1|0 关注|未关注
    @RequestMapping("/follow")
    @ResponseBody
    public Integer follow(Integer userId, Integer followId)
    {
        return userService.follow(userId, followId);
    }


    // 请求地址 /user/getFollow
    // param userId: 用户id
    // param followId: 被关注id
    // do: 检查是否已经关注
    // return:  1|0 已关注|未关注
    @RequestMapping("/getFollow")
    @ResponseBody
    public Integer getFollow(Integer userId, Integer followId)
    {
        return userService.getFollow(userId, followId);
    }
}
