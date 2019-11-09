package com.fxx.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: java类作用描述
 * @Author: FeiXinXin
 * @CreateDate: 2019/7/22 10:55
 * @Version: 1.0
 */
@RestController
@RequestMapping("/jwt")
public class JwtController {

    private static final Logger logger = LoggerFactory.getLogger(JwtController.class);

    @RequestMapping("/login")
    public Object login(User user){
        logger.info("进入登录页");
        String token = JWTDemo.createTokenWithClaim(user.getUserName());
        return token;
    }


    @RequestMapping("/getUser")
    public Object getUser(HttpServletRequest httpServletRequest){
        logger.info("获取用户登录信息");

        return httpServletRequest.getHeader("token");
    }


}
