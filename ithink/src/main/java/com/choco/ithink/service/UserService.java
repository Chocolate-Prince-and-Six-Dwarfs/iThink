package com.choco.ithink.service;

import com.choco.ithink.DAO.mapper.UserMapper;
import com.choco.ithink.exception.PrimarykeyException;
import com.choco.ithink.pojo.User;
import com.choco.ithink.pojo.UserExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    // 用于数据库查询的mapper接口
    @Resource
    private UserMapper userMapper;


    // param loginKey: 登录键
    // param pwd: 用户密码
    // do: 检查用户名是否匹配
    // return: 匹配返回1, 若不匹配但用户已存在则返回0, 若用户不存在则返回-1
    public Integer checkUserPwd(String loginKey, String pwd)
    {
        // 根据用户名查询数据库
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(loginKey);
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
}
