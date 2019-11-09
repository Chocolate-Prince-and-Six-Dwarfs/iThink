package com.choco.ithink.controller;

import com.choco.ithink.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    SessionService sessionService;


    @RequestMapping({"/main", "/", "/index"})
    public String main(HttpServletRequest request)
    {
        if(sessionService.isLogin(request.getSession()))
        {
            return "main";
        }
        else
        {
            return "index";
        }
    }


    @RequestMapping({"/search"})
    public String search()
    {
        return "search";
    }


    @RequestMapping({"/user"})
    public String user()
    {
        return "user";
    }

    @RequestMapping("/notifyTest")
    public String notifyTest()
    {
        return "notify";
    }
}
