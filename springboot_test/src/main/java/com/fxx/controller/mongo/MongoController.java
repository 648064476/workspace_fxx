package com.fxx.controller.mongo;

import com.fxx.dto.User;
import com.fxx.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

/**
 * @author gantingting
 */
@Controller
@RequestMapping("/mongo")
public class MongoController {

    private static final Logger log = LoggerFactory.getLogger(MongoController.class);

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping("/set")
    @ResponseBody
    public Object set (User user) {
        try {
            user.setUuid(UUID.randomUUID());
            return mongoTemplate.save(user);
        } catch (Exception e) {
            return "Errror Message";
        }
    }

    @RequestMapping("/get")
    @ResponseBody
    public Object get () {
        try {
            return mongoTemplate.findAll(User.class);
        } catch (Exception e) {
            return "Errror Message";
        }
    }

}

