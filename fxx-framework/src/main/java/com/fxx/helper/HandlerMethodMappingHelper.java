package com.fxx.helper;

import com.fxx.annotation.*;
import com.fxx.handler.HandlerExecutionChain;
import com.fxx.handler.HandlerMethod;
import com.fxx.handler.MethodParameter;
import com.fxx.handler.RequestMappingInfo;
import com.fxx.util.ParamNameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Description: 请求映射类
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/18 10:57
 * @Version: 1.0
 */
public class HandlerMethodMappingHelper implements HandlerMapping {

    private static final Logger logger = LoggerFactory.getLogger(HandlerMethodMappingHelper.class);

    private Map<RequestMappingInfo, HandlerMethod> mappingLookup = new LinkedHashMap<>();

    public HandlerMethodMappingHelper() {
        initMapping();
    }

    public void initMapping() {

        //获取所有的controller
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        controllerClassSet.forEach(obj -> {
            Method[] declaredMethods = obj.getDeclaredMethods();
            if (ArrayUtils.isNotEmpty(declaredMethods)) {
                for (Method declaredMethod : declaredMethods) {
                    if (declaredMethod.isAnnotationPresent(PostMapping.class) || declaredMethod.isAnnotationPresent(RequestMapping.class)) {
                        RequestMappingInfo requestMappingInfo = getRequestMappingInfo(declaredMethod, obj);

                        HandlerMethod handlerMethod = new HandlerMethod(BeanHelper.getBean(obj), obj, declaredMethod, getParamter(declaredMethod, obj));

                        //初始化mappingLookup
                        if (Objects.nonNull(requestMappingInfo) && Objects.nonNull(handlerMethod)) {
                            mappingLookup.put(requestMappingInfo, handlerMethod);
                        }
                    }
                }
            }
        });
    }

    /**
     * 获取类中的路径
     *
     * @param declaredMethod
     * @param obj
     * @return
     */
    private static RequestMappingInfo getRequestMappingInfo(Method declaredMethod, Class<?> obj) {
        RequestMappingInfo requestMappingInfo = null;
        RequestMethod[] requestMethods = null;

        String headerUrl = null;

        //获取类上面的路径
        if (obj.isAnnotationPresent(RequestMapping.class)) {
            RequestMapping annotation = obj.getAnnotation(RequestMapping.class);
            headerUrl = annotation.value()[0];
        }


        //先获取RequestMapping上的路径
        RequestMapping requestMapping = declaredMethod.getAnnotation(RequestMapping.class);
        if (Objects.nonNull(requestMapping)) {
            String[] value = requestMapping.value();
            for (int i = 0; i < value.length; i++) {
                value[i] = headerUrl + value[i];
            }
            requestMethods = requestMapping.method();
            requestMappingInfo = new RequestMappingInfo(requestMethods, value);
        }
        //不为空直接返回
        if (Objects.nonNull(requestMappingInfo)) {
            return requestMappingInfo;
        }

        //获取PostMapping上面的路径
        PostMapping postMapping = declaredMethod.getAnnotation(PostMapping.class);
        if (Objects.nonNull(postMapping)) {
            String[] value = postMapping.value();
            for (int i = 0; i < value.length; i++) {
                value[i] = headerUrl + value[i];
            }
            requestMethods = new RequestMethod[]{RequestMethod.POST};
            requestMappingInfo = new RequestMappingInfo(requestMethods, value);
        }
        if (Objects.nonNull(requestMappingInfo)) {
            return requestMappingInfo;
        }
        return null;
    }

    /**
     * 获取方法的参数
     *
     * @param method
     * @return
     */
    public static MethodParameter[] getParamter(Method method, Class<?> clazz) {
        Parameter[] parameters = method.getParameters();
        MethodParameter[] methodParameters = new MethodParameter[parameters.length];
        try {
            //获取参数名
            List<String> methodParamNames = ParamNameUtils.getMethodParamNames(clazz, method);

            int i = 0;
            for (Parameter parameter : parameters) {

                Class<?> type = parameter.getType();
                methodParameters[i] = new MethodParameter(i, type, methodParamNames.get(i), getParamterAnnotationMap(parameter));
                i++;
            }
        } catch (IOException e) {
            logger.error("获取参数名失败" + e);
        }
        return methodParameters;
    }

    /**
     *  获取参数注解MAP
     * @param parameter
     * @return
     */
    private static Map<Class<?>, Annotation> getParamterAnnotationMap(Parameter parameter) {
        Map<Class<?>, Annotation> map = new HashMap<>();
        RequestParam requestParam = parameter.getDeclaredAnnotation(RequestParam.class);
        if (Objects.nonNull(requestParam)){
            map.put(RequestParam.class, requestParam);
        }
        RequestBody requestBodyParam = parameter.getDeclaredAnnotation(RequestBody.class);
        if (Objects.nonNull(requestBodyParam)){
            map.put(RequestBody.class, requestBodyParam);
        }

        //to do带扩展得参数注解
        return map;
    }

    /**
     * 获取处理器链
     *
     * @param request
     * @return
     */
    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest request) {
        List<HandlerMethod> handlerInternal = getHandlerInternal(request);
        return new HandlerExecutionChain(handlerInternal.get(0));
    }

    /**
     * 获取处理器
     *
     * @param request
     * @return
     */
    public List<HandlerMethod> getHandlerInternal(HttpServletRequest request) {
        String url = request.getRequestURI();
        String method = request.getMethod();

        Predicate<RequestMappingInfo> predicate = (e) -> ArrayUtils.contains(e.getRequestUrl(), url);

        Predicate<RequestMappingInfo> predicate1 = (e) -> (e.getRequestMethods().length == 0 ||
                RequestMethod.contains(e.getRequestMethods(), method));

        List<HandlerMethod> collect = mappingLookup.keySet().stream()
                .filter(predicate.and(predicate1)).map((k) -> mappingLookup.get(k))
                .collect(Collectors.toList());

        return collect;
    }
}
