package com.choco.ithink.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.choco.ithink.DAO.mapper.*;
import com.choco.ithink.exception.PrimarykeyException;
import com.choco.ithink.pojo.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserService {
    // 用于数据库查询的mapper接口
    @Resource
    private UserMapper userMapper;
    // 用于查询粉丝列表
    @Resource
    private FollowersNamelistMapper followersNamelistMapper;
    @Resource
    private UserOtherInfoMapper userOtherInfoMapper;
    @Resource
    private TopicCollectionMapper topicCollectionMapper;
    @Resource
    private AchievementCollectionMapper achievementCollectionMapper;
    @Resource
    private UpdateTimeMapper updateTimeMapper;

    // 邮箱的正则表达式
    private String emailPattern = "^[a-zA-Z0-9]{1,}@[a-zA-Z0-9]{1,}(\\.[a-zA-Z0-9]{1,}){1,}$";
    // 密码的正则表达式
    private String pwdPattern = "^[a-zA-Z0-9]{6,20}$";

    // 用户名的正则表达式
    private String namePattern = "^[a-zA-Z0-9\\u4e00-\\u9fa5]{2,20}$";


    public String getEmailPattern() {
        return emailPattern;
    }


    public String getPwdPattern() {
        return pwdPattern;
    }

    public String getNamePattern() {
        return namePattern;
    }


    // param loginKey: 登录键
    // param pwd: 用户密码
    // do: 检查用户名是否匹配
    // return: 匹配返回1, 若不匹配但用户已存在则返回0, 若用户不存在则返回-1
    public Integer checkMatching(String loginKey, String pwd)
    {
        // 根据用户名查询数据库
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserEmailEqualTo(loginKey);
        List<User> result = userMapper.selectByExample(userExample);

        // 确认查询结果
        try
        {
            if (result.size() <= 0) {
                // 没有查询到结果
                return -1;
            }
            else if (result.size() > 1)
            {
                // 查询到多个结果
                throw new PrimarykeyException();
            }
            else
            {
                // 查询到单条结果
                if (result.get(0).getUserPassword().equals(pwd))
                {
                    // 存在且匹配
                    return 1;
                }
                else
                {
                    // 存在但不匹配
                    return 0;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -100;
        }
    }


    // param s: 字符串
    // param pattern: 正则表达式
    // do: 检查字符串是否符合指定的正则表达式
    // return: 匹配返回true, 否则返回false
    public Boolean checkString(String s, String pattern)
    {
        return Pattern.matches(pattern, s);
    }


    // param username: 用户名称
    // param pwd: 用户密码
    // param sex: 性别
    // param email: 邮箱
    // param birthday: 生日
    // param phone: 手机号(可选)
    // do: 将新用户数据插入数据表
    // return: 成功返回1, 若用户已存在则返回0, 其他错误返回-400
    public Integer userRegister(String username, String pwd, String sex, String email, String birthday, @Nullable String phone)
    {
        // 查询用户是否已经存在
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(username);
        // 如果结果为空(即用户不存在)
        if(userMapper.selectByExample(userExample).isEmpty())
        {
            // 构造实体
            User user = constructNewUser(username, pwd, sex, email, birthday, phone);
            if(user == null)
            {
                return -400;
            }

            // 插入数据
            try
            {
                Integer count = userMapper.insertSelective(user);
                // 检查是否成功插入数据
                if(count != 1)
                {
                    return -400;
                }
                else
                {
                    Integer id = user.getUserId();
                    // 创建其他用户数据字段
                    UserOtherInfo userOtherInfo = new UserOtherInfo();
                    userOtherInfo.setUserId(id);
                    userOtherInfoMapper.insertSelective(userOtherInfo);

                    // 创建更新时间记录
                    UpdateTime updateTime = new UpdateTime();
                    updateTime.setTime(new Date());
                    updateTime.setUserId(id);
                    updateTimeMapper.insertSelective(updateTime);
                    return 1;
                }
            }
            catch(Exception e)
            {
                // 发生错误
                e.printStackTrace();
                return -400;
            }


        }
        //如果结果非空(即用户已经存在)
        else
        {
            return 0;
        }
    }


    // param username: 用户名称
    // param pwd: 用户密码
    // param sex: 性别
    // param email: 邮箱
    // param birthday: 生日
    // param phone: 手机号(可选)
    // do: 构建新用户
    // return: 构建成功返回对象, 否则返回null
    private User constructNewUser(String username, String pwd, String sex, String email, String birthday, @Nullable String phone)
    {
        // 默认参数设置
        User user = new User();
        Integer defaultUserCredit = 100;
        String defaultPhone = "";
        //String path = System.getProperty("user.dir");

        // 设置参数
        user.setUserName(username);
        user.setUserPassword(pwd);
        user.setUserSex(sex);
        user.setUserEmail(email);
        user.setUserBirth(birthday);
        user.setUserCredit(defaultUserCredit);
        if(phone!=null)
        {
            user.setUserPhone(phone);
        }
        else
        {
            user.setUserPhone(defaultPhone);
        }

        // 读入默认头像
        String imgName = "头像.png";
        InputStream img = getClass().getClassLoader().getResourceAsStream("static/img/" + imgName);//指定要读取的图片
        BufferedImage bufferedImage;
        try {
            // 将图片读取为块
            bufferedImage = ImageIO.read(img);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            // 图片写入字节数组
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
            byte[] bytesImg = byteArrayOutputStream.toByteArray();
            // 设置图片
            user.setUserAvatar(bytesImg);
        }
        catch(Exception e)
        {
            // 发生错误
            e.printStackTrace();
            return null;
        }

        return user;
    }

    // param userId: 用户id
    // do: 查找用户的关注列表
    // return: 用户列表
    public JSONArray getFansById(Integer userId)
    {
        // 查找关系表
        FollowersNamelistExample followersNamelistExample = new FollowersNamelistExample();
        followersNamelistExample.createCriteria().andFollowIdEqualTo(userId);
        List<FollowersNamelist> followersNamelists =  followersNamelistMapper.selectByExample(followersNamelistExample);

        // 根据关系表中找到的id查找用户列表
        JSONArray jsonArray = new JSONArray();
        for(int i=0; i<followersNamelists.size(); ++i)
        {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUserIdEqualTo(followersNamelists.get(i).getUserId());
            User user = userMapper.selectByExampleWithBLOBs(userExample).get(0);

            // 拼接json
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", user.getUserId());
            jsonObject.put("head", user.getUserAvatar());
            jsonObject.put("name", user.getUserName());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }


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
    // return: 成功返回1, 失败返回0
    public Integer updateInfoById(Integer id, @Nullable MultipartFile head, @Nullable String name, @Nullable String sex,
               @Nullable String birthday, @Nullable String phone, @Nullable String address, @Nullable String industry,
               @Nullable String school, @Nullable String introduction)
    {
//        // 获取原本的用户基础数据
//        UserExample oldUserExample = new UserExample();
//        oldUserExample.createCriteria().andUserIdEqualTo(id);
//        User user = null;
//        try
//        {
//            user = userMapper.selectByExampleWithBLOBs(user);
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//            return 0;
//        }
        // 预定义状态
        Integer status = 0;

        // 实体
        User user = new User();
        UserOtherInfo userOtherInfo = new UserOtherInfo();
        // example类
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(id);
        UserOtherInfoExample userOtherInfoExample = new UserOtherInfoExample();
        userOtherInfoExample.createCriteria().andUserIdEqualTo(id);

        try
        {
            // 构建新的用户基础信息
            if(head!=null)
            {
                user.setUserAvatar(head.getBytes());
            }
            user.setUserName(name);
            user.setUserSex(sex);
            user.setUserBirth(birthday);
            user.setUserPhone(phone);
            // 更新用户基础信息
            Integer countBase = 0;
            if(name!=null || sex!=null || birthday!=null || phone!=null || head!=null)
            {
                countBase = userMapper.updateByExampleSelective(user, userExample);
            }

            // 构建新的用户补充信息
            userOtherInfo.setUserAddress(address);
            userOtherInfo.setUserIndustry(industry);
            userOtherInfo.setUserSchool(school);
            userOtherInfo.setUserSelfintroduction(introduction);
            // 更新用户补充信息
            Integer countOther = 0;
            if(address!=null || industry!=null || school!=null || introduction!=null)
            {
                countOther = userOtherInfoMapper.updateByExampleSelective(userOtherInfo, userOtherInfoExample);
            }

            if((countBase==1 || countOther==1) && (countBase + countOther) <=2)
            {
                status = 1;
            }
            else
            {
                status = 0;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status = 0;
        }

        return status;
    }


    // param id: 用户id
    // do: 获取用户除了密码以外的信息
    // return: 用户数据
    public JSONObject getById(Integer id)
    {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(id);
        List<User> userList = userMapper.selectByExampleWithBLOBs(userExample);

        if(userList.size()!=1)
        {
            return null;
        }
        else
        {
            return list2JSON(userList).getJSONObject(0);
        }
    }


    // param userList: 实体列表
    // do： 将列表构建为JSON数组对象
    // return: 构建的json数组对象
    public JSONArray list2JSON(List<User> userList)
    {
        JSONArray jsonArray = new JSONArray();

        // 循环处理实体
        for(int i=0; i<userList.size(); ++i)
        {
            // 获取其余信息
            UserOtherInfoExample userOtherInfoExample = new UserOtherInfoExample();
            userOtherInfoExample.createCriteria().andUserIdEqualTo(userList.get(i).getUserId());
            List<UserOtherInfo> userOtherInfoList = userOtherInfoMapper.selectByExample(userOtherInfoExample);
            if(userOtherInfoList.size()!=1)
            {
                continue;
            }
            UserOtherInfo userOtherInfo = userOtherInfoList.get(0);
            String address = userOtherInfo.getUserAddress();
            String industry = userOtherInfo.getUserIndustry();
            String school = userOtherInfo.getUserSchool();
            String introduction = userOtherInfo.getUserSelfintroduction();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", userList.get(i).getUserId());
            jsonObject.put("name", userList.get(i).getUserName());
            jsonObject.put("sex", userList.get(i).getUserSex());
            jsonObject.put("email", userList.get(i).getUserEmail());
            jsonObject.put("birthday", userList.get(i).getUserBirth());
            jsonObject.put("phone", userList.get(i).getUserPhone());
            jsonObject.put("credit", userList.get(i).getUserCredit());
            jsonObject.put("head", userList.get(i).getUserAvatar());
            jsonObject.put("address", address);
            jsonObject.put("industry", industry);
            jsonObject.put("school", school);
            jsonObject.put("introduction", introduction);
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }


    // param id: 用户id
    // do: 获取用户的创意主题和创意实现收藏列表(只返回id)
    // return:[0, 1, 3, 5]
    public JSONArray getCollectTopicById(Integer id)
    {
        JSONArray jsonArray = new JSONArray();

        // 收藏的主题列表
        JSONArray topic = new JSONArray();
        TopicCollectionExample topicCollectionExample = new TopicCollectionExample();
        topicCollectionExample.createCriteria().andUserIdEqualTo(id);
        List<TopicCollection> topicCollectionList = topicCollectionMapper.selectByExample(topicCollectionExample);

        for(int i=0; i<topicCollectionList.size(); ++i)
        {
            jsonArray.add(topicCollectionList.get(i).getTopicId());
        }

        return jsonArray;
    }


    // param id: 用户id
    // do: 获取用户的创意主题和创意实现收藏列表(只返回id)
    // return:[0, 1, 3, 5]
    public JSONArray getCollectAchievementById(Integer id)
    {
        JSONArray jsonArray = new JSONArray();

        // 收藏的主题列表
        JSONArray achievement = new JSONArray();
        AchievementCollectionExample achievementCollectionExample = new AchievementCollectionExample();
        achievementCollectionExample.createCriteria().andUserIdEqualTo(id);
        List<AchievementCollection> achievementCollectionList = achievementCollectionMapper.selectByExample(achievementCollectionExample);

        for(int i=0; i<achievementCollectionList.size(); ++i)
        {
            jsonArray.add(achievementCollectionList.get(i).getAchievementId());
        }

        return jsonArray;
    }
}
