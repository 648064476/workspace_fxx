package com.fxx.arithmetic.bitoperation;

/**
 * 位运算操作
 */
public class BitOperation {

    /**
     * 判断奇偶
     *
     * @param n
     */
    public static int test (int n) {
        return n & 1;
    }

    /**
     * 交换两个数
     *
     * @param
     */
    public static void test1 (int x, int y) {
        x = x ^ y;   // （1）
        y = x ^ y;  // （2）
        x = x ^ y; // （3）
        System.out.println(x);
        System.out.println(y);
    }

    /**
     * 找出没有重复的数
     *
     * @param
     */
    public static int test2 (int[] arr) {
        int tmp = arr[0];
        for (int i = 1; i < arr.length; i++) {
            tmp = tmp ^ arr[i];
        }
        return tmp;
    }

    /**
     * 找出不大于N的最大的2的幂指数
     *
     * @param n
     * @return
     */
    static int test3 (int n) {
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8; // 整型一般是 32 位，上面我是假设 8 位。
        return (n + 1) >> 1;
    }




    public static void main (String[] args) {
        System.out.println(test(4));

        test1(5, 6);
        System.out.println(test2(new int[]{12, 12, 34}));
        System.out.println(test3(19));


    }

}
