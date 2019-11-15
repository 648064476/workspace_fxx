package com.fxx.handler;

import com.fxx.annotation.RequestMethod;

/**
 * @Description: 控制器 url的封装
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/17 15:09
 * @Version: 1.0
 */
public class RequestMappingInfo {

    /**
     * 方法类型
     */
    private RequestMethod[] requestMethods;

    /**
     * 请求方法的url
     */
    private String[] requestUrl;

    public RequestMappingInfo(RequestMethod[] requestMethods, String[] requestUrl) {
        this.requestMethods = requestMethods;
        this.requestUrl = requestUrl;
    }

    public RequestMethod[] getRequestMethods() {
        return requestMethods;
    }

    public void setRequestMethods(RequestMethod[] requestMethods) {
        this.requestMethods = requestMethods;
    }

    public String[] getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String[] requestUrl) {
        this.requestUrl = requestUrl;
    }
}
