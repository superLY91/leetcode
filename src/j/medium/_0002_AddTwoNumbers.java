package j.medium;

import java.util.Arrays;

import j.common.LinkedListTestUtils;
import j.common.ListNode;

/**
 * 2. Add Two Numbers
 * 给你两个 非空 的链表，表示两个非负的整数。
 * 它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例 1：
 * 
 * 
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 * 
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 示例 3：
 * 
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 * 
 * 
 * 提示：
 * 
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 * 
 * 思路
 * 
 * 同时遍历两个链表，模拟竖式加法。
 * 每次取两个节点值加上进位 `carry`：
 * 
 * - 当前位 = `sum % 10`
 * - 进位 = `sum / 10`
 * 
 * 
 * 时间复杂度：O(max(m, n))
 * 空间复杂度：O(max(m, n))
 */
public class _0002_AddTwoNumbers {

    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(0);
            ListNode cur = dummy;
            int carry = 0;
            while (l1 != null || l2 != null || carry != 0) {
                int x = (l1 != null) ? l1.value : 0;
                int y = (l2 != null) ? l2.value : 0;

                int sum = x + y + carry;
                carry = sum / 10;

                cur.next = new ListNode(sum % 10);
                cur = cur.next;

                if (l1 != null)
                    l1 = l1.next;
                if (l2 != null)
                    l2 = l2.next;

            }
            return dummy.next;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        test(solution,
                new int[] { 2, 4, 3 },
                new int[] { 5, 6, 4 },
                new int[] { 7, 0, 8 });

        test(solution,
                new int[] { 0 },
                new int[] { 0 },
                new int[] { 0 });

        test(solution,
                new int[] { 9, 9, 9, 9, 9, 9, 9 },
                new int[] { 9, 9, 9, 9 },
                new int[] { 8, 9, 9, 9, 0, 0, 0, 1 });

        test(solution,
                new int[] { 1 },
                new int[] { 9, 9, 9 },
                new int[] { 0, 0, 0, 1 });

        test(solution,
                new int[] { 5 },
                new int[] { 5 },
                new int[] { 0, 1 });
    }

    private static void test(Solution solution, int[] input1, int[] input2, int[] expected) {
        ListNode l1 = LinkedListTestUtils.buildList(input1);
        ListNode l2 = LinkedListTestUtils.buildList(input2);

        ListNode result = solution.addTwoNumbers(l1, l2);
        int[] actual = LinkedListTestUtils.toArray(result);

        boolean pass = Arrays.equals(actual, expected);
        System.out.println(
                (pass ? "PASS" : "FAIL")
                        + " | input1 = " + Arrays.toString(input1)
                        + " | input2 = " + Arrays.toString(input2)
                        + " | expected = " + Arrays.toString(expected)
                        + " | actual = " + Arrays.toString(actual));
    }
}