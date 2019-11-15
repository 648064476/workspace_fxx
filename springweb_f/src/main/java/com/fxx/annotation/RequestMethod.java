package com.fxx.annotation;

/**
 * 请求方式的方式
 */
public enum RequestMethod {
    GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;

    public static boolean contains(RequestMethod[] methods,String methodName) {
        for (RequestMethod method : methods) {
            String str = method.toString();
            if (str.equals(methodName)){
                return true;
            }
        }
        return false;
    }
}
