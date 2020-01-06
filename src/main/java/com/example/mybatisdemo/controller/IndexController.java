package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.entity.Response;
import com.example.mybatisdemo.entity.User;
import com.example.mybatisdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//主页请求
@RestController
public class IndexController {

    @Autowired
    UserMapper userMapper;

    //如果session中没有user对象则登录超时
    //session超时时间为30秒
    @GetMapping(path = "getAllUsers")
    public Response getAllUsers(HttpSession session) {
        Response response = new Response();
        response.setSuccess(false);
        if(checkLogin(session)){
            response.setMessage("登录失效或者未登录");
        }else {
            response.setSuccess(true);
            response.setData(userMapper.listUsers());
        }
        return response;
    }

    @GetMapping(path = "deleteUser")
    public Response deleteUser(int id, HttpSession session) {
        Response response = new Response();
        response.setSuccess(false);
        if(checkLogin(session)){
            response.setMessage("登录失效或者未登录");
        }else {
            response.setSuccess(true);
            userMapper.deleteUserForId(id);
        }
        return response;
    }

    @GetMapping(path = "updatePassword")
    public Response updatePassword(String password, HttpSession session) {
        Response response = new Response();
        response.setSuccess(false);
        if(checkLogin(session)){
            response.setMessage("登录失效或者未登录");
        }else {
            if("".equals(password) || password.length() < 6){
                response.setMessage("请输入新密码");
                return response;
            }

            response.setSuccess(true);
            User user = (User) session.getAttribute("user");

            userMapper.updateUserInfoForName(user.getUserLoginName(), password, user.getGender(), user.getUserName(), null);
            session.setAttribute("user",null);
            response.setMessage("修改成功，请重新登录");
        }
        return response;
    }

    @GetMapping(path = "updateUserName")
    public Response updateUserName(String userName, HttpSession session) {
        Response response = new Response();
        response.setSuccess(false);
        if(checkLogin(session)){
            response.setMessage("登录失效或者未登录");
        }else {
            if("".equals(userName) || userName.length() < 6){
                response.setMessage("请输入新用户名");
                return response;
            }

            response.setSuccess(true);
            User user = (User) session.getAttribute("user");

            userMapper.updateUserInfoForName(user.getUserLoginName(), user.getPassWord(), user.getGender(), userName, null);
            response.setMessage("修改成功");
        }
        return response;
    }

    static boolean checkLogin(HttpSession session){
        User user = (User) session.getAttribute("user");
        System.out.println("当前用户："+ user);
        return (user == null);
    }
}
