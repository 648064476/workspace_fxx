package com.fxx;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.LockSupport;

/**
 * @Description: java类作用描述
 * @Author: FeiXinXin
 * @CreateDate: 2019/7/19 11:23
 * @Version: 1.0
 */
@SpringBootApplication
public class NosqlTest {

    public static void main(String[] args) throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        int i = unsafe.pageSize();

        unsafe.getClass().getClassLoader().loadClass("");
//        unsafe.park(true,100L);
//        System.out.println(i);
        int i1 = unsafe.addressSize();
//        unsafe.
        System.out.println(i1);
//        SpringApplication.run(NosqlTest.class,args);
    }
}
