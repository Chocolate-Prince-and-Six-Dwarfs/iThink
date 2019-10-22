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


    // 请求地址： userInfo/getInfoByUserId
    // param id: 用户id
    // param opinion: 选项 ("topic"|"comment"|"follow")
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
    JSONObject getInfoByUserId(Integer id, String opinion);
}
