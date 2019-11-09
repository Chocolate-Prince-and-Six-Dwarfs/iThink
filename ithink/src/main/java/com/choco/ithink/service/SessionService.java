package com.choco.ithink.service;

import com.choco.ithink.DAO.mapper.UserMapper;
import com.choco.ithink.pojo.User;
import com.choco.ithink.pojo.UserExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class SessionService {
    @Resource
    private UserMapper userMapper;

    // param session: session类
    // param attr: 字段名
    // do: 获取session中某个字段值
    // return: 字段值
    public Object getAttr(HttpSession session, String attr)
    {
        Object tmp = session.getAttribute(attr);
        if(tmp==null)
        {
            return null;
        }
        else
        {
            return tmp;
        }
    }


    // param session: session类
    // param userEmail: 用户邮箱
    // do: 设置登录用户
    // return: 字段值
    public void setLoginUser(HttpSession session, String userEmail)
    {
        session.setAttribute("userEmail", userEmail);
    }


    // param session: session类
    // do: 注销登录用户
    public void logout(HttpSession session)
    {
        session.removeAttribute("userEmail");
    }


    // param session: session类
    // do: 检查是否登录
    // return: 已登录返回true 否则返回false
    public boolean isLogin(HttpSession session)
    {
        // 检查session确认是否登录
        String userName = (String)getAttr(session, "userEmail");
        if(userName==null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    // param session: session类
    // do: 获取用户id
    // return: 获取用户id
    public Integer getLoginId(HttpSession session)
    {
        String userEmail = (String)getAttr(session, "userEmail");
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserEmailEqualTo(userEmail);
        List<User> userList = userMapper.selectByExample(userExample);

        if(userList.size()!=1)
        {
            return null;
        }
        else
        {
            return userList.get(0).getUserId();
        }
    }
}
