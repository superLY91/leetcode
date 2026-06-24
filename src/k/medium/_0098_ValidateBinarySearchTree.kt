package k.medium

/**
98 Validate Binary Search Tree 验证二叉搜索树
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 * 有效 二叉搜索树定义如下：
 *
 * 节点的左子树只包含 严格小于 当前节点的数。
 * 节点的右子树只包含 严格大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [2,1,3]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：root = [5,1,4,null,null,3,6]
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 *
 *
 * 提示：
 *
 * 树中节点数目范围在[1, 10^4] 内
 * -2^31 <= Node.val <= 2^31 - 1
 *
 *
 * 时间复杂度：O(n) 每个节点只访问一次。
 * 空间复杂度：O(h)
 * h 是树高，因为递归调用栈深度等于树高。
 * 平衡树：O(log n)
 * 最坏链状树：O(n)
 */

import k.common.TreeNode

class _0098_ValidateBinarySearchTree {

    class Solution {
        fun isValidBST(root: TreeNode?): Boolean {
            return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE)
        }

        /**
         * 对每个节点，我们维护一个合法范围：
         * 当前节点必须 > 下界
         * 当前节点必须 < 上界
         *
         * 对左子树：
         * 左子树所有节点都必须 < 当前节点值
         * 所以递归左边时，上界变成 root.`val`
         *
         * 对右子树：
         * 右子树所有节点都必须 > 当前节点值
         * 所以递归右边时，下界变成 root.`val`
         */
        // 以 node 为根的这棵子树，所有节点值都必须落在 (lower, upper) 这个开区间里
        private fun dfs(root: TreeNode?, lower: Long, upper: Long) : Boolean {
            if (root == null) return true

            if (root.value.toLong() <= lower || root.value.toLong() >= upper) return false

            return dfs(root.left, lower, root.value.toLong()) &&
                    dfs(root.right, root.value.toLong(), upper)
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = Solution()

            // 1. 基本合法 BST
            test(
                solution.isValidBST(
                    node(2, node(1), node(3))
                ),
                true
            )

            // 2. 题目示例：不合法
            test(
                solution.isValidBST(
                    node(
                        5,
                        node(1),
                        node(4, node(3), node(6))
                    )
                ),
                false
            )

            // 3. 单节点
            test(
                solution.isValidBST(
                    node(1)
                ),
                true
            )

            // 4. 左子节点等于根节点，不合法
            test(
                solution.isValidBST(
                    node(1, node(1), null)
                ),
                false
            )

            // 5. 右子节点等于根节点，不合法
            test(
                solution.isValidBST(
                    node(1, null, node(1))
                ),
                false
            )

            // 6. 右子树里出现小于根的值，不合法
            test(
                solution.isValidBST(
                    node(
                        5,
                        node(4),
                        node(6, node(3), node(7))
                    )
                ),
                false
            )

            // 7. 合法复杂 BST
            test(
                solution.isValidBST(
                    node(
                        8,
                        node(
                            3,
                            node(1),
                            node(6, node(4), node(7))
                        ),
                        node(
                            10,
                            null,
                            node(14, node(13), null)
                        )
                    )
                ),
                true
            )

            // 8. 极大值
            test(
                solution.isValidBST(
                    node(Int.MAX_VALUE)
                ),
                true
            )

            // 9. 极小值
            test(
                solution.isValidBST(
                    node(Int.MIN_VALUE)
                ),
                true
            )

            // 10. 左子树深层节点违反规则
            test(
                solution.isValidBST(
                    node(
                        10,
                        node(
                            5,
                            node(2),
                            node(12)
                        ),
                        node(15)
                    )
                ),
                false
            )
        }

        private fun node(value: Int): TreeNode {
            return TreeNode(value)
        }

        private fun node(value: Int, left: TreeNode?, right: TreeNode?): TreeNode {
            return TreeNode(value, left, right)
        }

        private fun test(actual: Boolean, expected: Boolean) {
            val pass = actual == expected
            println(
                (if (pass) "PASS" else "FAIL") +
                        " | expected = $expected, actual = $actual"
            )
        }
    }
}