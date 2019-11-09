package com.fxx.jwt;


import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @author jinbin
 * @date 2018-07-08 20:41
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {


    private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {

        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("token");

        //检查有没有需要用户权限的注解
        // 执行认证
        if (token == null) {
            throw new RuntimeException("无token，请重新登录");
        }
        try {
            JWTDemo.verifyToken(token);
        } catch (Exception e) {
            loginFailte(httpServletResponse);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public boolean loginFailte(HttpServletResponse httpServletResponse) {
        PrintWriter out = null;
        try {
            logger.error("请重新登录");
            Result result = new Result();
            result.setCode(CodeMsg.ERROR.getCode());
            result.setSuccess(false);
            result.setMsg("请重新登录");
            out = httpServletResponse.getWriter();
            out.append(JSON.toJSONString(result));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
