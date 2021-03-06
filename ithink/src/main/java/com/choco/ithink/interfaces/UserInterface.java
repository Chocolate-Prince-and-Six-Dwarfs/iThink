package com.choco.ithink.interfaces;

import com.alibaba.fastjson.JSONObject;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface UserInterface {
    // 请求地址： user/login
    // param email: 登录键(邮箱)
    // param pwd: 用户密码
    // do: 检查邮箱和密码是否匹配
    // return: 匹配返回json格式{status: 1}, 若不匹配但用户已存在则返回{status: 0}, 若用户不存在则返回{status: -1}, 输入不符合规范返回{status: -2}, 未知错误返回{status: -400}
    JSONObject login(String email, String pwd, HttpServletRequest request);

    //请求地址： user/wxlogin
    // param email: 登录键(邮箱)
    // param pwd: 用户密码
    // do: 接收code并向微信服务器发送
    /*Integer wxlogin(String email,String pwd,HttpServletRequest request);*/

    // 请求地址： user/logout
    // do: 退出登录
    String logout(HttpServletRequest request);

    // 请求地址： user/checkEmail
    // param email: 登录键(邮箱)
    // do: 检查邮箱是否符合规范
    // return: 符合返回true, 不符合返回false
    Boolean checkEmail(String email);

    // 请求地址： user/checkPwd
    // param pwd: 密码
    // do: 检查密码是否符合规范
    // return: 符合返回true, 不符合返回false
    Boolean checkPwd(String pwd);

    // 请求地址： user/checkName
    // param name: 用户名
    // do: 检查用户名是否符合规范
    // return: 符合返回true, 不符合返回false
    public Boolean checkName(String name);

    // 请求地址： user/register
    // param username: 用户名称
    // param pwd: 用户密码
    // param sex: 性别
    // param email: 邮箱
    // param birthday: 生日
    // param phone: 手机号(可选)
    // do: 用户注册
    // return: 注册成功返回json格式{status: 1}, 若用户已存在则返回{status: 0}, 输入不符合规范返回{status: -2}, 未知错误返回{status: -400}
    JSONObject register(String username, String pwd, String sex, String email, String birthday, @Nullable String phone);


    // 请求地址： user/getInfoByUserId
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
    //              like: 20 (创意实现点赞数),
    //              dislike: 2 (创意实现的点灭数),
    //              comment: 3 （创意实现的评论数）
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
    JSONObject getInfoByUserId(Integer id, String opinion);


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
    JSONObject updateInfo(Integer id, @Nullable MultipartFile head, @Nullable String name, @Nullable String sex,
                          @Nullable String birthday, @Nullable String phone, @Nullable String address, @Nullable String industry,
                          @Nullable String school, @Nullable String introduction);


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
    JSONObject getById(Integer id);

    // 请求地址: user/getLoginId
    // do: 获取当前登录用户的id
    // return: 用户id
    Integer getLoginId(HttpServletRequest request);


    // 请求地址: user/getCollectById
    // param id: 用户id
    // do: 获取用户的创意主题和创意实现收藏列表(只返回id)
    // return:
    // {
    //      id: ,
    //      topic:  [0, 1, 3, 5]
    //      achievement: [0, 3, 8]
    // }
    JSONObject getCollectById(Integer id);


    // 请求地址: /user/getCapsuleById
    // param id: 用户id
    // do: 获取胶囊
    // return:
    // {
    // id: xxx,
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
    JSONObject getCapsuleById(Integer id);

    // 请求地址 /user/follow
    // param userId: 用户id
    // param followId: 被关注id
    // do: 添加关注（已关注则取消关注）
    // return: 操作完成后的关注状态 1|0 已关注|未关注
    Integer follow(Integer userId, Integer followId);

    // 请求地址 /user/getFollow
    // param userId: 用户id
    // param followId: 被关注id
    // do: 检查是否已经关注
    // return:  1|0 已关注|未关注
    Integer getFollow(Integer userId, Integer followId);
}
