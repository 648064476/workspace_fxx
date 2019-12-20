package com.fxx.annotation;

import com.fxx.handler.HandlerExecutionChain;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取handler
 */
public interface HandlerMapping {

    /**
     * 获取处理器
     * @param request
     * @return
     */
    HandlerExecutionChain getHandler(HttpServletRequest request);
}
