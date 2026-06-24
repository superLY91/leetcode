package k.easy

import j.common.LinkedListTestUtils
import j.common.ListNode


/**
 * 141. Linked List Cycle（环形链表）
 *
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）
 * 。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 *
 * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 *
 * 提示：
 * 链表中节点的数目范围是 [0, 104]
 * -105 <= Node.val <= 105
 * pos 为 -1 或者链表中的一个 有效索引 。
 *
 * 进阶：你能用 O(1)（即，常量）内存解决此问题吗？
 *
 * 思路：
 * 快慢指针
 * 慢指针每次走一步
 * 快指针每次走两步
 * 如果有环一定会相遇
 * 如果没环一定会走到null
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
class _0141_LinkedListCycle {

    class Solution {
        fun hasCycle(head: ListNode?): Boolean {
            if (head?.next == null) return false

            var fast = head
            var slow = head

            while (fast != null && fast.next != null) {
                fast = fast.next?.next
                slow = slow?.next
                if (slow == fast) {
                    return true
                }
            }

            return false
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = Solution()

            // 示例1: [3,2,0,-4], pos = 1 -> true
            testCycle(
                solution,
                intArrayOf(3, 2, 0, -4),
                1,
                true
            )

            // 示例2: [1,2], pos = 0 -> true
            testCycle(
                solution,
                intArrayOf(1, 2),
                0,
                true
            )

            // 示例3: [1], pos = -1 -> false
            testCycle(
                solution,
                intArrayOf(1),
                -1,
                false
            )

            // 补充测试: 空链表 -> false
            testCycle(
                solution,
                intArrayOf(),
                -1,
                false
            )

            // 补充测试: 两个节点无环 -> false
            testCycle(
                solution,
                intArrayOf(1, 2),
                -1,
                false
            )
        }

        /**
         * 测试环形链表题
         */
        fun testCycle(solution: Solution, input: IntArray, pos: Int, expected: Boolean) {
            val head = LinkedListTestUtils.buildCycleList(input, pos)
            val actual = solution.hasCycle(head)

            val pass = actual == expected
            println(
                (if (pass) "PASS" else "FAIL") +
                        " | input = ${input.contentToString()}" +
                        " | pos = $pos" +
                        " | expected = $expected" +
                        " | actual = $actual"
            )
        }
    }
}