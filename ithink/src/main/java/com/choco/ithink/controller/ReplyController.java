package com.choco.ithink.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.choco.ithink.interfaces.ReplyInterface;
import com.choco.ithink.service.CommentService;
import com.choco.ithink.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reply")
public class ReplyController implements ReplyInterface {
    @Autowired
    private ReplyService replyService;

    // 请求地址: reply/do
    // param commentId: 创意评论id
    // param content: 回复内容
    // param fromId: 回复者id
    // param toId: 被回复者id
    // do: 发表回复
    // return:
    // 成功: {status: 1, id: xxx},
    // 失败: {status: 0, id: null}
    @RequestMapping("/do")
    @ResponseJSONP
    public JSONObject doReply(Integer commentId, String content, Integer fromId, Integer toId)
    {
        // 回复
        JSONObject jsonObject = replyService.doReply(commentId, content, fromId, toId);

        return jsonObject;
    }


    // 请求地址: reply/getById
    // param id: 回复id
    // do: 获取回复内容
    // return:
    // 成功:
    // {
    //      replyId: 0,(回复id)
    //      commentId: 0,(创意实现id)
    //      content: "回复内容",
    //      time: "回复时间",
    //      fromId: 0,(回复者id)
    //      fromName: "回复者昵称"
    //      toId: 0,(被回复者id)
    //      toName: "被回复者昵称"
    // }
    // 失败: null
    @RequestMapping("/getById")
    @ResponseJSONP
    public JSONObject getById(Integer id)
    {
        // 获取回复
        JSONObject jsonObject = replyService.getById(id);

        return jsonObject;
    }
}
