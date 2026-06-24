package k.medium

import k.common.TreeNode
import kotlin.math.max

/**
 * 124 Binary Tree Maximum Path Sum 二叉树中的最大路径和
 *
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。
 * 同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 *
 * 路径和 是路径中各节点值的总和。
 *
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 *
 * 示例 2：
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 *
 * 提示：
 * 树中节点数目范围是 [1, 3 * 104]
 * -1000 <= Node.val <= 1000
 *
 * 自己思路：
 * 首先肯定是深度搜索dfs
 *
 * 思路：
 * 这题用后序遍历。
 * 对于每个节点，先递归求左右子树能提供的最大贡献。
 *
 * 1. leftGain = max(dfs(root.left), 0)
 * 2. rightGain = max(dfs(root.right), 0)
 *
 * 如果某一边贡献是负数，就舍弃，因为接上它只会让路径和更小。
 *
 * 然后把当前节点作为路径的“拐点”，计算：
 * currentPathSum = root.val + leftGain + rightGain
 *
 * 用它更新全局最大值。
 *
 * 注意：
 * 返回给父节点的值不能同时包含左右两边，
 * 因为路径不能分叉着继续往上走。
 * 所以返回：
 * root.val + max(leftGain, rightGain)
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(h)，h 为树高
 */
class _0124_BinaryTreeMaximumPathSum {

    class Solution {
        var maxSum = Integer.MIN_VALUE

        fun maxPathSum(root: TreeNode?): Int {
            maxSum = Integer.MIN_VALUE
            dfs(root)
            return maxSum
        }

        /**
         * 从 root 出发，往下走，最多只能选一条分支时，
         * 能提供给父节点的最大路径和。
         */
        private fun dfs(root: TreeNode?): Int {
            if (root == null) return 0

            val leftGain = max(dfs(root.left), 0)
            val rightGain = max(dfs(root.right), 0)

            // 判断当前节点是作为拐点
            val currentSum = root.value + leftGain + rightGain;

            // 更像全局最大值
            maxSum = max(maxSum, currentSum)

            // 判断接上面父节点，算当前的最大路径
            return root.value + max(leftGain, rightGain)
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = Solution()

            // 1. 题目示例 1
            test(
                solution.maxPathSum(
                    node(1, node(2), node(3))
                ),
                6
            )

            // 2. 题目示例 2：最大路径不经过根节点
            test(
                solution.maxPathSum(
                    node(
                        -10,
                        node(9),
                        node(20, node(15), node(7))
                    )
                ),
                42
            )

            // 3. 单节点正数
            test(
                solution.maxPathSum(
                    node(5)
                ),
                5
            )

            // 4. 单节点负数
            test(
                solution.maxPathSum(
                    node(-3)
                ),
                -3
            )

            // 5. 根节点为负，但左右子树正，最大路径穿过根
            test(
                solution.maxPathSum(
                    node(
                        2,
                        node(-1),
                        node(3)
                    )
                ),
                5
            )

            // 6. 一边是负贡献，应该舍弃
            test(
                solution.maxPathSum(
                    node(
                        1,
                        node(2),
                        node(-5)
                    )
                ),
                3
            )

            // 7. 全负数：取最大的那个单节点
            test(
                solution.maxPathSum(
                    node(
                        -10,
                        node(-20),
                        node(-30)
                    )
                ),
                -10
            )

            // 8. 左子树内部就是最大路径，不经过根
            test(
                solution.maxPathSum(
                    node(
                        -1,
                        node(10, node(20), node(30)),
                        node(-5)
                    )
                ),
                60
            )

            // 9. 链状树
            test(
                solution.maxPathSum(
                    node(
                        1,
                        node(
                            2,
                            node(
                                3,
                                node(4),
                                null
                            ),
                            null
                        ),
                        null
                    )
                ),
                10
            )

            // 10. 经典负贡献剪枝测试
            test(
                solution.maxPathSum(
                    node(
                        10,
                        node(2),
                        node(10, null, node(-25, node(3), node(4)))
                    )
                ),
                22
            )

            // 11. 左右都能贡献，当前节点做拐点
            test(
                solution.maxPathSum(
                    node(
                        5,
                        node(4),
                        node(8)
                    )
                ),
                17
            )

            // 12. 更复杂一点
            test(
                solution.maxPathSum(
                    node(
                        1,
                        node(-2, node(4), node(5)),
                        node(3, node(-6), node(2))
                    )
                ),
                9
            )
        }

        private fun node(value: Int): TreeNode {
            return TreeNode(value)
        }

        private fun node(value: Int, left: TreeNode?, right: TreeNode?): TreeNode {
            return TreeNode(value, left, right)
        }

        private fun test(actual: Int, expected: Int) {
            val pass = actual == expected
            println(
                (if (pass) "PASS" else "FAIL") +
                        " | expected = $expected, actual = $actual"
            )
        }
    }
}