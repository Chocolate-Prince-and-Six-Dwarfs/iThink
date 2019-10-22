package com.choco.ithink.service;

import com.choco.ithink.DAO.mapper.UserMapper;
import com.choco.ithink.exception.PrimarykeyException;
import com.choco.ithink.pojo.User;
import com.choco.ithink.pojo.UserExample;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserService {
    // 用于数据库查询的mapper接口
    @Resource
    private UserMapper userMapper;

    // 邮箱的正则表达式
    private String emailPattern = "^[\\w]{0,}@[\\w]{0,}\\.[\\w]{0,}$";
    // 密码的正则表达式
    private String pwdPattern = "^[\\w]{6,20}$";


    public String getEmailPattern() {
        return emailPattern;
    }


    public String getPwdPattern() {
        return pwdPattern;
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
                userMapper.insertSelective(user);
            }
            catch(Exception e)
            {
                // 发生错误
                e.printStackTrace();
                return -400;
            }

            // 检查是否成功插入数据
            if(userMapper.selectByExample(userExample).size() != 1)
            {
                return -400;
            }
            else
            {
                return 1;
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
        String path = System.getProperty("user.dir");

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
        File img = new File(path + "\\src\\main\\resources\\static\\img\\" + "头像.png");//指定要读取的图片
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
}
