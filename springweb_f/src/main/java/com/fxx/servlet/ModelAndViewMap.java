package com.fxx.servlet;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description: 参数封装类
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/20 14:15
 * @Version: 1.0
 */
public class ModelAndViewMap {

    //所有的参数
    private Map<String, Object> defaultModel = null;

    //表单参数
    private Map<String, Object> urlMap = null;

    //流参数
    private Map<String, Object> bodyMap = null;

    public ModelAndViewMap() {
        this.defaultModel = new LinkedHashMap<>();
    }

    public void setRedirectModel(LinkedHashMap<String, Object> defaultModel) {
        this.defaultModel = defaultModel;
    }

    public ModelAndViewMap(LinkedHashMap<String, Object> redirectModel) {
        this.defaultModel = redirectModel;
    }

    public void initModel(HttpServletRequest request){
        initModelUrl(request);
        initModelBody(request);
    }

    private void initModelUrl(HttpServletRequest request) {
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String key = parameterNames.nextElement();
            String value = request.getParameter(key);
            defaultModel.put(key, value);
            if (Objects.isNull(urlMap)){
                urlMap = new LinkedHashMap<>();
            }
            urlMap.put(key, value);
        }
    }

    private void initModelBody(HttpServletRequest request) {
        String body = getBody(request);
        if (StringUtils.isNotBlank(body)){
            Map mapType = JSON.parseObject(body,Map.class);
            if (Objects.isNull(bodyMap)){
                bodyMap = new LinkedHashMap<>();
            }
            defaultModel.putAll(mapType);
            bodyMap.putAll(mapType);
        }
    }

    public Map<String, Object> getModel(){
        return this.defaultModel;
    }

    public Map<String, Object> getUrlMap(){
        return this.urlMap;
    }

    public Map<String, Object> getBodyMap(){
        return this.bodyMap;
    }

    public String getBody(HttpServletRequest request){
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        String line = null;
        try {
            //接收request数据流，并指定编码格式接收
            br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void put(String key,Object value){
        defaultModel.put(key, value);
    }

}
