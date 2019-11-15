package com.fxx.servlet;

import com.alibaba.fastjson.JSON;
import com.fxx.annotation.RequestBody;
import com.fxx.annotation.RequestParam;
import com.fxx.annotation.RestController;
import com.fxx.handler.HandlerExecutionChain;
import com.fxx.handler.HandlerMethod;
import com.fxx.handler.MethodParameter;
import com.fxx.helper.HandlerMethodMappingHelper;
import com.fxx.helper.InitializationHelper;
import com.fxx.util.ReflectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

/**
 * @Description: java类作用描述
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/20 13:55
 * @Version: 1.0
 */
@WebServlet(name = "FxxServlet",urlPatterns = {
        "/*"
})
public class FxxServlet extends HttpServlet {

    private static final String FXX_REQUEST = "request";

    private HandlerMethodMappingHelper handlerMethodMappingHelper;


    @Override
    public void init() throws ServletException {
        InitializationHelper.init();
        handlerMethodMappingHelper = new HandlerMethodMappingHelper();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HandlerExecutionChain handler = handlerMethodMappingHelper.getHandler(req);

        if (Objects.nonNull(handler)){
            HandlerMethod handlerMethod = handler.getHandlerMethod();

            //设置参数
            ModelAndViewMap modelAndViewMap = new ModelAndViewMap();
            modelAndViewMap.initModel(req);
            modelAndViewMap.put(FXX_REQUEST,req);
            Object invokeValue = doInvoke(handlerMethod, modelAndViewMap);
            returnValue(invokeValue,handlerMethod,resp);
        }
    }

    private void returnValue(Object invokeValue,HandlerMethod handlerMethod,HttpServletResponse resp) {
        if (handlerMethod.getBeanType().isAnnotationPresent(RestController.class)
            || handlerMethod.getMethod().isAnnotationPresent(RequestBody.class)){
            //返回json
            returnJson(invokeValue,resp);
        }else {
            //跳转页面
        }
    }

    private void returnJson(Object invokeValue,HttpServletResponse resp) {
        String jsonString = JSON.toJSONString(invokeValue);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = resp.getWriter();
            out.append(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    private Object doInvoke(HandlerMethod handlerMethod, ModelAndViewMap modelAndViewMap) {
        Object[] params = getMethodParam(handlerMethod.getParameters(), modelAndViewMap);
        return ReflectionUtils.invokeMethod(handlerMethod.getBean(), handlerMethod.getMethod(), params);
    }

    private Object[] getMethodParam(MethodParameter[] parameters, ModelAndViewMap modelAndViewMap) {
        Object[] obj = new Object[parameters.length];
        if (ArrayUtils.isNotEmpty(parameters)){
            int i = 0;
            for (MethodParameter param:
                    parameters) {
                Map<Class<?>, Annotation> parameterAnnoMap= param.getParameterAnnotations();
                if (parameterAnnoMap.containsKey(RequestBody.class)){
                    //从body中读数据
                    Object paramValue = getRequestBodyValue(param, modelAndViewMap.getBodyMap());
                    obj[i] = paramValue;
                }else if (parameterAnnoMap.containsKey(RequestParam.class)){
                    //从表单中读数据
                    Object paramValue = getRequestParamValue(param,modelAndViewMap.getUrlMap());
                    obj[i] = paramValue;
                }else {
                    //正常读取字段
                    Object paramValue = getRequestValue(param,modelAndViewMap.getUrlMap());
                    obj[i] = paramValue;
                }
                i++ ;
            }
        }
        return obj;
    }

    /**
     * 通过 request 获取参数
     * @param param
     * @param paramMap
     * @return
     */
    private Object getRequestValue(MethodParameter param, Map<String,Object> paramMap) {
        return paramMap.get(param.getParameterName());
    }

    /**
     *  通过注解参数 获取值
     * @param param
     * @param paramMap
     * @return
     */
    private Object getRequestParamValue(MethodParameter param, Map<String,Object> paramMap) {
        Map<Class<?>, Annotation> parameterAnnotations = param.getParameterAnnotations();
        Object obj = null;
        if (Objects.nonNull(parameterAnnotations) ){
            RequestParam requestParam = (RequestParam)parameterAnnotations.get(RequestParam.class);
            if (Objects.nonNull(requestParam)){
                obj = paramMap.get(requestParam.value());
            }
        }
        return obj;
    }

    /**
     * 通过流 获取参数
     * @param param
     * @param paramMap
     * @return
     */
    public Object getRequestBodyValue(MethodParameter param, Map<String,Object> paramMap){

        //参数类型
        Class<?> paramClass = param.getParameterType();

        Object o = ReflectionUtils.newInstance(paramClass);
        Field[] declaredFields = paramClass.getDeclaredFields();
        for (Field field:
             declaredFields) {
            String name = field.getName();
            ReflectionUtils.setFiled(o,field,paramMap.get(name));
        }
        return o;
    }

}
