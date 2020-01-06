package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.entity.Response;
import com.example.mybatisdemo.entity.User;
import com.example.mybatisdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

//处理登录，注册，登出请求
//注意线上的应用用户密码要经过加密，绝对不允许存明文
//可以改成post请求增加安全性
@RestController
public class LoginController {

    @Autowired
    UserMapper userMapper;

    @ResponseBody
    @GetMapping(path = "login")
    public Response login(String userLoginName, String passWord, HttpSession session){
        Response response = new Response();
        response.setSuccess(false);

        if(userLoginName == null || passWord == null){
            response.setMessage("参数错误");
            return response;
        }

        List<User> login = userMapper.login(userLoginName, passWord);

        if(login.size() == 1){
            User user = login.get(0);
            session.setAttribute("user",user);
            String loginTime = new Date().toString();

            userMapper.updateUserInfoForName(user.getUserLoginName(), user.getPassWord(),
                    user.getGender(), user.getUserName(), loginTime);

            response.setSuccess(true);
            response.setMessage("登录成功");
        }else {
            response.setMessage("登录名或密码错误");
        }

        return  response;
    }

    @ResponseBody
    @GetMapping(path = "refreshAccount")
    public Response refreshAccount(HttpSession session){
        User user = (User) session.getAttribute("user");
        Response response = new Response();
        response.setSuccess(true);
        response.setData(user);

        if(user == null){
            response.setMessage("登录失效");
        }else{
            user.setPassWord("**********");
        }
        return response;
    }

    @ResponseBody
    @GetMapping(path = "logout")
    public Response logout(HttpSession session){
        session.setAttribute("user",null);
        Response response = new Response();
        response.setSuccess(true);
        return response;
    }

    private Response makeMsg(Response response, StringBuffer message){
        response.setMessage(message.toString());
        return response;
    }

    @ResponseBody
    @GetMapping(path = "register")
    public Response register(String userName, String passWord,
                             String userLoginName, String gender){

        Response response = new Response();
        response.setSuccess(true);
        StringBuffer message = new StringBuffer("");

        if("".equals(userName) || userName.length() < 6){
            message.append("用户名格式错误");
            return makeMsg(response,message);
        }
        if("".equals(passWord) || passWord.length() < 6){
            message.append("密码格式错误");
            return makeMsg(response,message);
        }
        if("".equals(userLoginName) || userLoginName.length() < 6){
            message.append("登录名格式错误");
            return makeMsg(response,message);
        }
        if("".equals(gender)){
            message.append("性别不能为空");
            return makeMsg(response,message);
        }
        List<User> users = userMapper.findUserByLoginName(userLoginName);
        if(users.size() == 1){
            message.append("登录名不能重复");
            return makeMsg(response,message);
        }else{
            message.append("注册成功");
            userMapper.insertUser(userName, passWord, userLoginName, gender);
            return makeMsg(response,message);
        }
    }
}
