package com.fxx.guava.RateLimit;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * guava实现限流器
 *
 * @author gantingting
 */
@Component("rateLimitInterceptor")
public class RateLimitInterceptor extends AbstractInterceptor {
    /**
     *     * 单机全局限流器(限制QPS为1)
     *    
     */
    private static final RateLimiter rateLimiter = RateLimiter.create(3);

    @Override
    protected Object preFilter (HttpServletRequest request) {
        if (!rateLimiter.tryAcquire()) {
            System.out.println("限流中......");
            return "Error Message";
        }
        System.out.println("请求成功");
        return new Date();
    }
}

