package com.services;

import com.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/10/10.
 */
public interface IUserService {
    void insertUser(User user);

    List<User> listPageUser(User user);

    List<User> getlistUser(User user);


    User getUserInfoByid(String userId);

    void deleteUser(String userId);

    void updateUser(User user);

    Map<String,Object> getMenu(User user, String parentId);
}
