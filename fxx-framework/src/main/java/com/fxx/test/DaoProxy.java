package com.fxx.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/30 15:55
 * @Version: 1.0
 */
public class DaoProxy {

//    @Override
//    public Object intercept(Object object, Method method, Object[] objects, MethodProxy proxy) throws Throwable {
//        System.out.println("Before Method Invoke");
//        proxy.invokeSuper(object, objects);
//        System.out.println("After Method Invoke");
//
//        return object;
//    }

    public static void main(String[] args) {

//        Dao o = (Dao) Enhancer.create(Dao.class, (MethodInterceptor) (obj, method, args1, proxy) -> {
//            System.out.println("Before Method Invoke");
//            proxy.invokeSuper(obj, args1);
//            System.out.println("After Method Invoke");
//            return obj;
//        });
//        o.select();

        ArrayList<Object> objects = new ArrayList<>();
        objects.add("1");
        objects.add("2");
        objects.add("3");
        objects.add("4");
        objects.add("5");
        objects.add("6");
        objects.add("7");
        objects.add("8");
        objects.add("9");
        objects.add("10");
        objects.add("11");
        ArrayList<Object> objects1 = new ArrayList<>(3);
//        objects1.add("1");
//        objects1.add("2");
//        objects1.add("3");
//        objects.removeAll(objects1);
        List<Object> objects4 = objects.subList(1, 3);

        ArrayList<Object> objects2 = new ArrayList<>(0);

        ArrayList<Object> objects3 = new ArrayList<>(objects);
    }
}
