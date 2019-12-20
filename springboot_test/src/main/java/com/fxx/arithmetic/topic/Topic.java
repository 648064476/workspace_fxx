package com.fxx.arithmetic.topic;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Topic {

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     * <p>
     * 示例:
     * <p>
     * 给定 nums = [2, 7, 11, 15], target = 9
     * <p>
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum (int[] nums, int target) {
        Map<Integer, Integer> map = Maps.newHashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }
            map.put(target - nums[i], i);
        }
        throw new IllegalArgumentException("数据不存在");
    }

    /**
     * 求两个链表的和
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers (ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode listNode = dummyHead;
        boolean flag = false;
        if (l1 != null && l2 != null) {
            do {
                int val = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0);
                val = flag ? val + 1 : val;
                listNode.next = new ListNode(val % 10);
                listNode = listNode.next;
                flag = (val >= 10);
                if (l1 != null) {
                    l1 = l1.next;
                }
                if (l2 != null) {
                    l2 = l2.next;
                }
            } while (l1 != null || l2 != null);
        }
        if (flag) {
            listNode.next = new ListNode(1);
        }
        return dummyHead.next;

    }

    /**
     * 字符串求最大子串
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring (String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < n; end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }

    public static void main (String[] args) {
//        ListNode l1 = new ListNode(2, null);
//        ListNode l2 = new ListNode(4, null);
//        ListNode l3 = new ListNode(3, null);
//        ListNode l4 = new ListNode(5, null);
//        ListNode l5 = new ListNode(6, null);
//        ListNode l6 = new ListNode(7, null);
////        ListNode l7 = new ListNode(7, null);
//        l1.setNext(l2);
//        l2.setNext(l3);
//
//        l4.setNext(l5);
//        l5.setNext(l6);
////        l6.setNext(l7);
//        System.out.println(addTwoNumbers(l1, l4));
        System.out.println(lengthOfLongestSubstring("acdefgde"));
    }


    static class ListNode {
        private Integer val;

        private ListNode next;

        public ListNode (Integer value) {
            this.val = value;
        }

        public ListNode (Integer value, ListNode next) {
            this.val = value;
            this.next = next;
        }


        public void setNext (ListNode next) {
            this.next = next;
        }

        @Override
        public String toString () {
            return "ListNode{" +
                    "value=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
