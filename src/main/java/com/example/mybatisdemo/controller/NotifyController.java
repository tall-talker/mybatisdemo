package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.entity.Response;
import com.example.mybatisdemo.entity.User;
import com.example.mybatisdemo.mapper.NotifyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import java.util.Date;

import static com.example.mybatisdemo.controller.IndexController.checkLogin;

@RestController
public class NotifyController {

    @Autowired
    NotifyMapper notifyMapper;

    @GetMapping(path = "getAllNotifies")
    public Response getAllNotifies(HttpSession session) {
        Response response = new Response();
        response.setSuccess(false);
        if(checkLogin(session)){
            response.setMessage("登录失效或者未登录");
        }else {
            response.setSuccess(true);
            response.setData(notifyMapper.listNotifies());
        }
        return response;
    }

    @GetMapping(path = "createNotify")
    public Response createNotify(String title, String content, HttpSession session) {
        Response response = new Response();
        response.setSuccess(false);
        if(checkLogin(session)){
            response.setMessage("登录失效或者未登录");
        }else {
            User user = (User) session.getAttribute("user");
            String author =   user.getUserName();
            String createDate = new Date().toString();
            response.setSuccess(true);

            if("".equals(title)){
                response.setMessage("标题格式错误");
                return response;
            }
            if("".equals(content)){
                response.setMessage("内容格式错误");
                return response;
            }

            notifyMapper.insertNotify(title, content, author, createDate);
            response.setMessage("创建成功");
        }
        return response;
    }

    @GetMapping(path = "deleteNotify")
    public Response deleteNotify(int id, HttpSession session) {
        Response response = new Response();
        response.setSuccess(false);
        if(checkLogin(session)){
            response.setMessage("登录失效或者未登录");
        }else {
            response.setSuccess(true);
            notifyMapper.deleteNotifyForId(id);
        }
        return response;
    }
}
