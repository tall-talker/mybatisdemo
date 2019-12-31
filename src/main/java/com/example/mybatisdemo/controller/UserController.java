package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.entity.User;
import com.example.mybatisdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//用来测试crud操作的接口，不需要返回response
@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping(path = "listUsers")
    public List<User> listUsers() {
        return userMapper.listUsers();
    }

    @GetMapping(path = "findUserByName")
    public List<User> findUserByName(String userName) {
        return userMapper.findUserByName(userName);
    }

    @GetMapping(path = "findUserByLoginName")
    public List<User> findUserByLoginName(String userLoginName) {
        return userMapper.findUserByLoginName(userLoginName);
    }

    @GetMapping(path = "insertUser")
    public void insertUser(String userName, String passWord, String userLoginName, String gender) {
        userMapper.insertUser(userName, passWord, userLoginName, gender);
    }

    @GetMapping(path = "deleteUserForName")
    public void deleteUserForName(String userName) {
        userMapper.deleteUserForName(userName);
    }

    @GetMapping(path = "updateUserInfoForName")
    public void updateUserInfoForName(String userName, String passWord, String userLoginName, String gender, String recentLogin) {
        userMapper.updateUserInfoForName(userLoginName, passWord, gender, userName, recentLogin);
    }

}
