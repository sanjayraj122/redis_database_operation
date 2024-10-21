package com.redis_database.controller;

import com.redis_database.dao.UserDao;
import com.redis_database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        user.setUserId(UUID.randomUUID().toString());
        return userDao.saveUser(user);

    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable String userId){
        return userDao.getUserById(userId);

    }

    @GetMapping("/allUser")
    public List<User> getAllUser(){

       Map<Object,Object>all=userDao.findAll();
        Collection<Object> values =all.values();

        List<User> collect = values.stream().map(value -> (User) value).collect(Collectors.toList());
        return collect;

    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        userDao.deleteUser(userId);
        return "user deleted successfully";
    }

}
