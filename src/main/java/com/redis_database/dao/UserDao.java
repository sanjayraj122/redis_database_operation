package com.redis_database.dao;

import com.redis_database.entity.User;
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
          template.opsForHash().delete(KEY,userId);

    }

}
