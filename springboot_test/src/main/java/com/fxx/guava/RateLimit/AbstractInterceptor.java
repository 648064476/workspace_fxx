package com.fxx.guava.RateLimit;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author gantingting
 */
public abstract  class AbstractInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object o = preFilter(request);
        if (o instanceof Date){
            return true;
        }
        handlerResponse(o,response);
        return false;
    }

    private void handlerResponse (Object o, HttpServletResponse response) {
        try {
            response.getWriter().write((String) o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract Object preFilter (HttpServletRequest request);

}
