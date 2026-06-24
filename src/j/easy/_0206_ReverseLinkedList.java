package j.easy;

import java.util.*;

/**
 * 206. Reverse Linked List
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * 
 * 示例 1：
 * 
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 * 示例 2：
 * 
 * 输入：head = [1,2]
 * 输出：[2,1]
 * 示例 3：
 * 
 * 输入：head = []
 * 输出：[]
 * 
 * 自己思路：
 * 设置个temp 存要变的量
 * temp = head -> next
 * head-> next = head
 * head = temp
 * 直到head -> next 是null 返回head
 * 
 * 这个代码的问题是我不能改变head，不要去改变题目给的结构，要重新创建个结构
 * while (head != null) {
 * ListNode temp = head.next;
 * head.next = head;
 * head = temp;
 * }
 * return head;
 * 
 * 标准思路
 * 遍历链表，逐个修改指针方向：
 * 
 * `prev` 指向前一个节点
 * `curr` 指向当前节点
 * `next` 暂存下一个节点
 * 
 * 
 * 时间复杂度
 * 
 * O(n)因为你把链表从头到尾遍历了一次：
 * 
 * 空间复杂度
 * 
 * O(1)因为你没有新建和链表长度相关的额外数据结构，
 */
public class _0206_ReverseLinkedList {

    // Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    static class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            return prev;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        test(solution, new int[] { 1, 2, 3, 4, 5 }, new int[] { 5, 4, 3, 2, 1 });
        test(solution, new int[] { 1, 2 }, new int[] { 2, 1 });
        test(solution, new int[] {}, new int[] {});
        test(solution, new int[] { 1 }, new int[] { 1 });
    }

    /**
     * 测试入口：
     * 1. 先把输入数组转链表
     * 2. 调用 reverseList
     * 3. 再把结果链表转数组
     * 4. 和 expected 比较
     */
    private static void test(Solution solution, int[] input, int[] expected) {
        ListNode head = buildList(input);
        ListNode reversed = solution.reverseList(head);
        int[] actual = toArray(reversed);

        boolean pass = Arrays.equals(actual, expected);
        System.out.println(
                (pass ? "PASS" : "FAIL")
                        + " | input = " + Arrays.toString(input)
                        + " | expected = " + Arrays.toString(expected)
                        + " | actual = " + Arrays.toString(actual));
    }

    /**
     * 数组 -> 链表
     */
    private static ListNode buildList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        for (int num : arr) {
            curr.next = new ListNode(num);
            curr = curr.next;
        }

        return dummy.next;
    }

    /**
     * 链表 -> 数组
     */
    private static int[] toArray(ListNode head) {
        int len = 0;
        ListNode curr = head;

        while (curr != null) {
            len++;
            curr = curr.next;
        }

        int[] arr = new int[len];
        curr = head;
        int i = 0;

        while (curr != null) {
            arr[i++] = curr.val;
            curr = curr.next;
        }

        return arr;
    }
}