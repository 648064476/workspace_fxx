package com.fxx.arithmetic.linkedlist;

public class LinkedList {

    /**
     * k个为一组逆序
     */
    public static ListNode reverseKGroup (ListNode head, int k) {
        ListNode temp = head;
        for (int i = 1; i < k && temp != null; i++) {
            temp = temp.next;
        }
        //判断节点的数量是否能够凑成一组
        if (temp == null)
            return head;

        ListNode t2 = temp.next;
        temp.next = null;
        //把当前的组进行逆序
        ListNode newHead = reverseList(head);
        //把之后的节点进行分组逆序
        ListNode newTemp = reverseKGroup(t2, k);
        // 把两部分连接起来
        head.next = newTemp;

        return newHead;
    }

    /**
     * 逆序单链表
     */
    private static ListNode reverseList (ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode result = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return result;
    }



    public static ListNode solve (ListNode head, int k) {
        // 调用逆序函数
        head = reverseList(head);
        // 调用每 k 个为一组的逆序函数（从头部开始组起）
        head = reverseKGroup(head, k);
        // 在逆序一次
        head = reverseList(head);
        return head;

    }

    public static void main (String[] args) {
        ListNode listNode = new ListNode(null,1);
        ListNode listNode1 = new ListNode(null,2);
        ListNode listNode2 = new ListNode(null,3);
        ListNode listNode3 = new ListNode(null,4);
        ListNode listNode4 = new ListNode(null,5);
        ListNode listNode5 = new ListNode(null,6);
        ListNode listNode6 = new ListNode(null,7);

        listNode.setNext(listNode1);
        listNode1.setNext(listNode2);
        listNode2.setNext(listNode3);
        listNode3.setNext(listNode4);
        listNode4.setNext(listNode5);
        listNode5.setNext(listNode6);
        System.out.println(solve(listNode,3));
    }

    static class ListNode {
        ListNode next;
        Integer value;

        public ListNode (ListNode next, Integer value) {
            this.next = next;
            this.value = value;
        }

        public void setNext (ListNode next) {
            this.next = next;
        }

        @Override
        public String toString () {
            return "ListNode{" +
                    "next=" + next +
                    ", value=" + value +
                    '}';
        }
    }
}
