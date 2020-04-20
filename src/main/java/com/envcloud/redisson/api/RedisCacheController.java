package com.envcloud.redisson.api;

import com.envcloud.redisson.model.User;
import com.envcloud.redisson.service.UserService;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "redis")
public class RedisCacheController {


     /**测试*/
    private static final Logger log = LoggerFactory.getLogger(RedisCacheController.class);

    private static final String USER_KEY = "USER_BUCKET_KEY::";

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private UserService userService;


    @RequestMapping("/addUser")
    public Integer add(User u) {
        Integer integer = userService.addUser(u);
        if (integer > 0) {
            stringRedisTemplate.opsForValue().set("user",u.toString());
            String s = stringRedisTemplate.opsForValue().get("12333");
            System.out.println(s);
        }
        return u.getId();
    }

/*    @RequestMapping("/getUser")
    public User getUser(Integer userId){
        RBucket<User> bucket = redissonClient.getBucket(USER_KEY + userId);
        if (bucket!=null){
            User user = bucket.get();
            return user;
        } else {
            return userService.getUser(userId);
        }
    }

    @RequestMapping("/updateUser")
    public Integer updateUser(User u){
        Integer integer = userService.updateUser(u);
        if (integer > 0) {
            RBucket<User> bucket = redissonClient.getBucket(USER_KEY + u.getId());
            bucket.set(u);
        }
        return u.getId();
    }

    @RequestMapping("/deleteUser")
    public User deleteUser(Integer userId){
        Integer integer = userService.deleteUser(userId);
        User user = null;
        if (integer > 0) {
            RBucket<User> bucket = redissonClient.getBucket(USER_KEY + userId);
            user = bucket.getAndDelete();
        }
        return user;
    }*/
}
