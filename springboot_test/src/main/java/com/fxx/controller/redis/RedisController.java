package com.fxx.controller.redis;

import com.fxx.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author gantingting
 */
@Controller
@RequestMapping("/redis")
public class RedisController {

    private static final Logger log = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisUtil redisUtil;


    @RequestMapping("/set")
    @ResponseBody
    public Object set () {
        try {
            redisUtil.set("aa", "bb");
            return true;
        } catch (Exception e) {
            return "Errror Message";
        }
    }

    @RequestMapping("/get")
    @ResponseBody
    public Object get () {
        try {
            return redisUtil.get("aa");
        } catch (Exception e) {
            return "Errror Message";
        }
    }

    @RequestMapping("/pubmsg")
    @ResponseBody
    public String pubMsg () {
        redisUtil.getRedisTemplate().convertAndSend("phone", "223333");
        redisUtil.getRedisTemplate().convertAndSend("phoneTest2", "aaaa");
        log.info("Publisher sendes Topic... ");
        return "success";
    }

    @RequestMapping("/pipeline")
    @ResponseBody
    public Object testPipeline () {
        long start = System.currentTimeMillis();
        // 执行Redis的流水线命令
        List result = redisUtil.getRedisTemplate().executePipelined(new SessionCallback() {
            @Override
            public Object execute (RedisOperations redisOperations) throws DataAccessException {
                for (int i = 0; i < 100000; i++) {
                    int j = i + 1;
                    redisOperations.boundValueOps("key" + j).set("value" + j);
                    redisOperations.boundValueOps("key" + j).get();
                }
                return null;
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return result;

    }

    @RequestMapping("/lua")
    @ResponseBody
    public Object testLua () {
        DefaultRedisScript<String> stringDefaultRedisScript = new DefaultRedisScript<>();
        //设置脚本
        stringDefaultRedisScript.setScriptText("return 'hello redis';");

        RedisSerializer<String> stringSerializer = redisUtil.getRedisTemplate().getStringSerializer();

        //执行Lua脚本
        String execute = redisUtil.getRedisTemplate().execute(stringDefaultRedisScript, stringSerializer, stringSerializer, null);
        return execute;
    }
}

