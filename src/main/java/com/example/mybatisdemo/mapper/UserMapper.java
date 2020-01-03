package com.example.mybatisdemo.mapper;

import com.example.mybatisdemo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> listUsers();

    List<User> findUserByName(String userName);

    List<User> findUserByLoginName(String userLoginName);

    void insertUser(String userName, String passWord, String userLoginName, String gender);

    void deleteUserForId(int id);

    void updateUserInfoForName(String userLoginName, String passWord, String gender, String userName, String recentLogin);

    List<User> login(String userLoginName, String passWord);
}
