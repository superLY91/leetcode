package k.medium

/**
 * 144. Binary Tree Preorder Traversal（二叉树的前序遍历）
 *
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 *
 * 示例 1：
 *
 * 输入：root = [1,null,2,3]
 *
 * 输出：[1,2,3]
 *
 * 解释：
 *
 *
 *
 * 示例 2：
 *
 * 输入：root = [1,2,3,4,5,null,8,null,null,6,7,9]
 *
 * 输出：[1,2,4,5,6,7,3,8,9]
 *
 * 解释：
 *
 *
 *
 * 示例 3：
 *
 * 输入：root = []
 *
 * 输出：[]
 *
 * 示例 4：
 *
 * 输入：root = [1]
 *
 * 输出：[1]
 *
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 *
 * 进阶：递归算法很简单，你可以通过迭代算法完成吗？
 *
 *
 * 时间复杂度：
 * 空间复杂度：
 */
import k.common.TreeNode
import java.util.ArrayDeque
import java.util.Arrays
import java.util.LinkedList
import java.util.Queue
import kotlin.collections.isNotEmpty

class _0144_BinaryTreePreorderTraversal {

    class Solution {
        fun preorderTraversal(root: TreeNode?): List<Int> {
            val result = mutableListOf<Int>()
            dfs(root, result)
            return result
        }

        fun dfs(node: TreeNode?, result: MutableList<Int>) {
            if (node == null) return

            result.add(node.value)
            dfs(node.left, result)
            dfs(node.right, result)
        }

        // 栈：因为深度优先搜索和栈的原理一样
//        fun preorderTraversal(root: TreeNode?): List<Int> {
//            val result = mutableListOf<Int>()
//            if (root == null) {
//                return result
//            }
//
//            val stack = ArrayDeque<TreeNode>()
//            stack.push(root)
//
//            while (stack.isNotEmpty()) {
//                val node = stack.pop()
//                result.add(node.value)
//
//                if (node.right != null) {
//                    stack.push(node.right)
//                }
//                if (node.left != null) {
//                    stack.push(node.left)
//                }
//            }
//
//            return result
//        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = Solution()

            // 示例1: [1,null,2,3] -> [1,2,3]
            test(
                solution.preorderTraversal(
                    buildTree(arrayOf(1, null, 2, 3))
                ),
                listOf(1, 2, 3)
            )

            // 示例2: [1,2,3,4,5,null,8,null,null,6,7,9] -> [1,2,4,5,6,7,3,8,9]
            test(
                solution.preorderTraversal(
                    buildTree(arrayOf(1, 2, 3, 4, 5, null, 8, null, null, 6, 7, 9))
                ),
                listOf(1, 2, 4, 5, 6, 7, 3, 8, 9)
            )

            // 示例3: [] -> []
            test(
                solution.preorderTraversal(
                    buildTree(emptyArray())
                ),
                emptyList()
            )

            // 示例4: [1] -> [1]
            test(
                solution.preorderTraversal(
                    buildTree(arrayOf(1))
                ),
                listOf(1)
            )

            // 补充测试: [1,2,3] -> [1,2,3]
            test(
                solution.preorderTraversal(
                    buildTree(arrayOf(1, 2, 3))
                ),
                listOf(1, 2, 3)
            )
        }

        private fun test(actual: List<Int>, expected: List<Int>) {
            val pass = actual == expected
            println(
                "${if (pass) "PASS" else "FAIL"} | expected = $expected, actual = $actual"
            )
        }

        /**
         * 按 LeetCode 层序数组构建二叉树
         */
        private fun buildTree(values: Array<Int?>): TreeNode? {
            if (values.isEmpty() || values[0] == null) {
                return null
            }

            val root = TreeNode(values[0]!!)
            val queue: Queue<TreeNode> = LinkedList()
            queue.offer(root)

            var i = 1
            while (queue.isNotEmpty() && i < values.size) {
                val current = queue.poll()

                if (i < values.size && values[i] != null) {
                    current.left = TreeNode(values[i]!!)
                    queue.offer(current.left)
                }
                i++

                if (i < values.size && values[i] != null) {
                    current.right = TreeNode(values[i]!!)
                    queue.offer(current.right)
                }
                i++
            }

            return root
        }
    }
}