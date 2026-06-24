package k.medium

/**
 **
39 Combination Sum 组合总和 (M，数组，回溯)

给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。

candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。

对于给定的输入，保证和为 target 的不同组合数少于 150 个。

示例 1：

输入：candidates = [2,3,6,7], target = 7
输出：[[2,2,3],[7]]
解释：
2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
7 也是一个候选， 7 = 7 。
仅有这两种组合。
示例 2：

输入: candidates = [2,3,5], target = 8
输出: [[2,2,2,2],[2,3,3],[3,5]]
示例 3：

输入: candidates = [2], target = 1
输出: []

提示：

1 <= candidates.length <= 30
2 <= candidates[i] <= 40
candidates 的所有元素 互不相同
1 <= target <= 40

思路：
回溯就是
选定
递归
撤销

时间复杂度：O(N^(T / M))
其中 N 是 candidates 的长度，T 是 target，M 是 candidates 中的最小值。
因为回溯树的最大深度约为 T / M，每层最坏有 N 个分支。

空间复杂度：O(T / M)
主要是递归栈深度和当前 path 的长度。
 */
class _0039_CombinationSum {

    class Solution {
        fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
            val result = mutableListOf<List<Int>>()
            val path = mutableListOf<Int>()

            backTrack(candidates, 0, 0, target, path, result)
            return result
        }

        fun backTrack(candidates: IntArray, start: Int, sum: Int, target: Int,
                      path: MutableList<Int>, result: MutableList<List<Int>>) {
            if (sum == target) {
                result.add(ArrayList(path))
                return
            }
            if (sum > target) {
                return
            }

            for (i in start until candidates.size) {
                // 加入
                path.add(candidates[i])
                // 递归
                backTrack(candidates, i, sum + candidates[i], target, path, result)
                // 撤销
                path.removeAt(path.size - 1)
            }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = Solution()

            test(
                solution.combinationSum(intArrayOf(2, 3, 6, 7), 7),
                listOf(
                    listOf(2, 2, 3),
                    listOf(7)
                )
            )

            test(
                solution.combinationSum(intArrayOf(2, 3, 5), 8),
                listOf(
                    listOf(2, 2, 2, 2),
                    listOf(2, 3, 3),
                    listOf(3, 5)
                )
            )

            test(
                solution.combinationSum(intArrayOf(2), 1),
                emptyList()
            )

            test(
                solution.combinationSum(intArrayOf(3), 9),
                listOf(
                    listOf(3, 3, 3)
                )
            )

            test(
                solution.combinationSum(intArrayOf(5), 6),
                emptyList()
            )

            test(
                solution.combinationSum(intArrayOf(2, 4, 6), 8),
                listOf(
                    listOf(2, 2, 2, 2),
                    listOf(2, 2, 4),
                    listOf(2, 6),
                    listOf(4, 4)
                )
            )

            test(
                solution.combinationSum(intArrayOf(2, 3, 5), 5),
                listOf(
                    listOf(2, 3),
                    listOf(5)
                )
            )
        }

        private fun test(actual: List<List<Int>>, expected: List<List<Int>>) {
            val pass = sameCombinations(actual, expected)

            println(
                (if (pass) "PASS" else "FAIL") +
                        " | expected = $expected, actual = $actual"
            )
        }

        private fun sameCombinations(actual: List<List<Int>>?, expected: List<List<Int>>?): Boolean {
            if (actual == null || expected == null) return actual == expected
            if (actual.size != expected.size) return false

            val actualSet = normalize(actual)
            val expectedSet = normalize(expected)

            return actualSet == expectedSet
        }

        private fun normalize(lists: List<List<Int>>): Set<String> {
            val set = mutableSetOf<String>()

            for (list in lists) {
                val copy = list.toMutableList()
                copy.sort()
                set.add(copy.toString())
            }

            return set
        }
    }
}