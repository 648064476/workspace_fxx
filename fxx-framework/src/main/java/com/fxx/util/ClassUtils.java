package com.fxx.util;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @Description: 类操作工具类
 * @Author: FeiXinXin
 * @CreateDate: 2019/5/14 17:18
 * @Version: 1.0
 */
public class ClassUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtils.class);

    /**
     * 获取类加载器
     *
     * @return
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }


    /**
     * @param className  类名
     * @param initialize 必须要加载
     * @return
     */
    public static Class<?> loadClass(String className, boolean initialize) {
        Class<?> cla;
        try {
            cla = Class.forName(className,initialize,getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("加载类失败", e);
            throw new RuntimeException(e);
        }
        return cla;
    }

    /**
     * 获取基础包下的所有类
     *
     * @param basePackage
     * @return
     */
    public static Set<Class<?>> getClassSet(String basePackage) {
        HashSet<Class<?>> clsSet = new HashSet<>();

        try {
            Enumeration<URL> urls = getClassLoader().getResources(basePackage.replace(".", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (Objects.nonNull(url)) {
                    if ("file".equals(url.getProtocol())) {
                        String packagePath = url.getPath().replaceAll("%20", "");
                            addClass(clsSet, packagePath, basePackage);
                    } else if ("jar".equals(url.getProtocol())){
                        JarURLConnection jarConnection = (JarURLConnection) url.openConnection();
                        if (Objects.nonNull(jarConnection)) {
                            JarFile jarFile1 = jarConnection.getJarFile();
                            if (Objects.nonNull(jarFile1)) {
                                Enumeration<JarEntry> entries = jarFile1.entries();
                                while (entries.hasMoreElements()){
                                    JarEntry jarEntry = entries.nextElement();
                                    String name = jarEntry.getName();
                                    if (name.endsWith(".class")) {
                                        String className = name.substring(0, name.lastIndexOf(".")).replace("/", "");
                                        doAddClss(clsSet, className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("获取类失败", e);
            throw new RuntimeException(e);
        }

        return clsSet;
    }

    /**
     * 加载className ，并加入集合对象
     *
     * @param clsSet
     * @param className
     */
    private static void doAddClss(Set<Class<?>> clsSet, String className) {
        Class<?> aClass = loadClass(className, false);
        clsSet.add(aClass);
    }


    private static void addClass(Set<Class<?>> clsSet, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });

        for (File f:
             files) {
            String fileName = f.getName();
            if (f.isFile()){
                String className = fileName.substring(0, fileName.lastIndexOf("."));

                if (StringUtils.isNotBlank(packageName)){
                    className = packageName + "." + className;
                }
                doAddClss(clsSet, className);

            }else {
                String subPackageName = fileName;
                if (StringUtils.isNotBlank(packageName)){
                    subPackageName = packageName + "." + subPackageName;
                }
                String subPackagePath = fileName;
                if (StringUtils.isNotBlank(packagePath)){
                    subPackagePath = packagePath + "." + subPackagePath;
                }
                    addClass(clsSet,subPackagePath,subPackageName);
            }
        }
    }

    /**
     * 判断是否为基本类型
     */
    public boolean isBasicType(Class<?> clazz){
        return clazz.isPrimitive();
    }

    /**
     * 判断是否为request子类
     */
    public boolean isRequestChild(Class<?> clazz){
        return clazz.isAssignableFrom(ServletRequest.class);
    }

    /**
     * 判断是否为list子类
     */
    public boolean isListChild(Class<?> clazz){
        return clazz.isAssignableFrom(List.class);
    }

    /**
     * 判断是否为String
     */
    public boolean isString(Class<?> clazz){
        return clazz.isAssignableFrom(String.class);
    }
}
