package com.fxx.guava.Annotation;

import org.springframework.lang.NonNull;


/**
 * @author gantingting
 */
public class AnnotationTest {

    public  void test(@NonNull String a){
        System.out.println(a);
    }
    public static void main (String[] args) {
        AnnotationTest annotationTest = new AnnotationTest();
        annotationTest.test(null);

    }
}
