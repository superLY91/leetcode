package k.medium

/**
 * 46 Permutations 全排列
 *
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 *
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：[[1]]
 *
 * 提示：
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 *
 * 思路：
 * 回溯（Backtracking）不是排列组合
 * 排列组合：更偏数学，问你“有多少种”
 * 全排列：要你把每一种情况都真正列出来
 *
 * 为什么叫回溯
 * 因为过程是：
 * 先选一个数
 * 继续往下选
 * 选到底后，保存结果
 * 然后“撤销刚才那一步”，回去尝试别的选择
 * 所以叫：
 * 做选择 -> 递归 -> 撤销选择
 * 这个模板特别重要
 * for 每一个可选元素:
 * 做选择
 * 递归下一层
 * 撤销选择
 *
 * 时间复杂度
 * O(n × n!)
 *
 * 原因：
 *
 * 一共有 n! 种排列
 * 每种排列长度是 n
 * 每次加入结果时要复制一个长度为 n 的列表
 *
 * 所以一般写：
 *
 * O(n * n!)
 * 空间复杂度
 * O(n)
 *
 * 递归栈 + used + path 的额外空间大约是 O(n)。
 *
 * 如果把结果集也算上，那总空间当然更大，因为要存所有排列。
 *
 * n! 读作 n 的阶乘。
 *
 * 意思是：
 *
 * n! = n × (n-1) × (n-2) × ... × 2 × 1
 *
 */
class _0046_Permutations {

    class Solution {
        fun permute(nums: IntArray): List<List<Int>> {
            val result = mutableListOf<List<Int>>()
            val path = mutableListOf<Int>()
            val used = BooleanArray(nums.size)

            backtrack(nums, used, path, result)
            return result;
        }

        private fun backtrack(nums: IntArray, used: BooleanArray,
                              path: MutableList<Int>, result: MutableList<List<Int>>) {
            // 终止条件：路径长度等于 nums 的长度，说明找到了一个完整排列
            if (path.size == nums.size) {
                // 拷贝一份当前结果，不能直接放 path
                result.add(ArrayList(path))
                return
            }

            for (i in nums.indices) {
                // 如果已经选择了就继续
                if (used[i]) {
                    continue
                }

                // 做选择
                path.add(nums[i])
                used[i] = true

                backtrack(nums, used, path, result)

                // 撤销
                path.removeAt(path.size - 1)
                used[i] = false
            }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = Solution()

            test(
                solution.permute(intArrayOf(1)),
                listOf(
                    listOf(1)
                )
            )

            test(
                solution.permute(intArrayOf(0, 1)),
                listOf(
                    listOf(0, 1),
                    listOf(1, 0)
                )
            )

            test(
                solution.permute(intArrayOf(1, 2, 3)),
                listOf(
                    listOf(1, 2, 3),
                    listOf(1, 3, 2),
                    listOf(2, 1, 3),
                    listOf(2, 3, 1),
                    listOf(3, 1, 2),
                    listOf(3, 2, 1)
                )
            )

            test(
                solution.permute(intArrayOf(-1, 0, 1)),
                listOf(
                    listOf(-1, 0, 1),
                    listOf(-1, 1, 0),
                    listOf(0, -1, 1),
                    listOf(0, 1, -1),
                    listOf(1, -1, 0),
                    listOf(1, 0, -1)
                )
            )

            test(
                solution.permute(intArrayOf(3, 1, 2)),
                listOf(
                    listOf(3, 1, 2),
                    listOf(3, 2, 1),
                    listOf(1, 3, 2),
                    listOf(1, 2, 3),
                    listOf(2, 3, 1),
                    listOf(2, 1, 3)
                )
            )
        }

        private fun test(actual: List<List<Int>>, expected: List<List<Int>>) {
            val pass = samePermutations(actual, expected)

            println(
                (if (pass) "PASS" else "FAIL") +
                        " | expected = $expected, actual = $actual"
            )
        }

        private fun samePermutations(actual: List<List<Int>>?, expected: List<List<Int>>?): Boolean {
            if (actual == null || expected == null) return actual == expected
            if (actual.size != expected.size) return false

            val actualSet = mutableSetOf<String>()
            val expectedSet = mutableSetOf<String>()

            for (list in actual) {
                actualSet.add(list.toString())
            }

            for (list in expected) {
                expectedSet.add(list.toString())
            }

            return actualSet == expectedSet
        }
    }
}