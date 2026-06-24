package j.easy;

import java.util.*;

/**
 * 21. Merge Two Sorted Lists（合并两个有序链表）
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的
 * 
 * 示例 1：
 * 
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 
 * 示例 2：
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 
 * 示例 3：
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 * 
 * 提示：
 * 
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 * 
 * 自己思路
 * 升序的，就遍历，按大小顺序频进去，最后谁剩下了就把剩下的拼到后面
 * 
 * 思路
 * 用虚拟头节点 dummy。
 * 每次比较两个链表当前节点，谁小就接谁。
 * 最后接上剩余部分。
 * 
 * dummy 是虚拟头节点，主要是为了统一链表拼接逻辑，
 * 避免单独处理头节点初始化的问题。
 * 这样无论当前接的是第一个节点还是后面的节点，都可以统一写成 cur.next = ...。
 * 最后返回 dummy.next 就是真正的结果链表头节点。
 * 
 * 时间复杂度：
 * 空间复杂度：
 */
public class _0021_MergeTwoSortedLists {

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
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode dummy = new ListNode(-1);
            ListNode cur = dummy;
            while (list1 != null && list2 != null) {
                if (list1.val <= list2.val) {
                    cur.next = list1;
                    list1 = list1.next;
                } else {
                    cur.next = list2;
                    list2 = list2.next;
                }
                cur = cur.next;
            }

            cur.next = list1 != null ? list1 : list2;
            return dummy.next;
        }

    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        test(solution,
                new int[] { 1, 2, 4 },
                new int[] { 1, 3, 4 },
                new int[] { 1, 1, 2, 3, 4, 4 });

        test(solution,
                new int[] {},
                new int[] {},
                new int[] {});

        test(solution,
                new int[] {},
                new int[] { 0 },
                new int[] { 0 });

        test(solution,
                new int[] { 1, 3, 5 },
                new int[] { 2, 4, 6 },
                new int[] { 1, 2, 3, 4, 5, 6 });

        test(solution,
                new int[] { 1, 1, 2 },
                new int[] { 1, 3 },
                new int[] { 1, 1, 1, 2, 3 });
    }

    /**
     * 测试入口：
     * 1. 先把两个输入数组转成链表
     * 2. 调用 mergeTwoLists
     * 3. 再把结果链表转成数组
     * 4. 和 expected 比较
     */
    private static void test(Solution solution, int[] input1, int[] input2, int[] expected) {
        ListNode head1 = buildList(input1);
        ListNode head2 = buildList(input2);

        ListNode merged = solution.mergeTwoLists(head1, head2);
        int[] actual = toArray(merged);

        boolean pass = Arrays.equals(actual, expected);
        System.out.println(
                (pass ? "PASS" : "FAIL")
                        + " | input1 = " + Arrays.toString(input1)
                        + " | input2 = " + Arrays.toString(input2)
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