package com.fxx.handler;

/**
 * @Description: java类作用描述
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/17 15:17
 * @Version: 1.0
 */
public class HandlerExecutionChain {

    private HandlerMethod handlerMethod;

    public HandlerMethod getHandlerMethod() {
        return handlerMethod;
    }

    public void setHandlerMethod(HandlerMethod handlerMethod) {
        this.handlerMethod = handlerMethod;
    }

    public HandlerExecutionChain(HandlerMethod handlerMethod) {
        this.handlerMethod = handlerMethod;
    }
}
