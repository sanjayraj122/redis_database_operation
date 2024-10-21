package com.redis_database.dao;

import com.redis_database.entity.User;
import com.redis_database.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDao {

    @Autowired
    private RedisTemplate<String,Object> template ;

    private static final String KEY="USER122";

    public User saveUser(User user){
         template.opsForHash().put(KEY, user.getUserId(),user);
         return user;
    }

    public User getUserById(String userId){
        return (User) template.opsForHash().get(KEY,userId);
    }

    public Map<Object,Object> findAll(){

        return template.opsForHash().entries(KEY);


    }

    // delete
    public void deleteUser(String userId){
        Long deletedCount = template.opsForHash().delete(KEY, userId);
        if (deletedCount == 0) {
            throw new UserNotFoundException("User with ID " + userId + " not found for deletion.");
        }
    }
    }
