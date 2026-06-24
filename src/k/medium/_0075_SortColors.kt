package k.medium

/**
 * 75 Sort Colors 颜色分类。荷兰国旗
 *
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，
 * 原地 对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
 *
 * 示例 1：
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 *
 * 示例 2：
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 *
 * 进阶：
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 * 时间复杂度：O(n) 每个元素最多被交换一次。
 * 空间复杂度：O(1) 原地排序。
 */
class _0075_SortColors {

    class Solution {
        fun sortColors(nums: IntArray) {
            var left = 0
            var right = nums.size - 1
            var i = 0

            while (i <= right) {
                if (nums[i] == 0) {
                    swap(nums, i, left)
                    left++;
                    i++
                } else if (nums[i] == 1) {
                    i++
                } else {
                    swap(nums, i, right)
                    right--
                }
            }
        }

        private fun swap(nums: IntArray, i: Int, j: Int) {
            val temp = nums[i]
            nums[i] = nums[j]
            nums[j] = temp
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = Solution()

            // 1. 题目示例 1
            test(solution, intArrayOf(2, 0, 2, 1, 1, 0), intArrayOf(0, 0, 1, 1, 2, 2))

            // 2. 题目示例 2
            test(solution, intArrayOf(2, 0, 1), intArrayOf(0, 1, 2))

            // 3. 只有一个元素
            test(solution, intArrayOf(0), intArrayOf(0))
            test(solution, intArrayOf(1), intArrayOf(1))
            test(solution, intArrayOf(2), intArrayOf(2))

            // 4. 已经排好序
            test(solution, intArrayOf(0, 0, 1, 1, 2, 2), intArrayOf(0, 0, 1, 1, 2, 2))

            // 5. 完全逆序
            test(solution, intArrayOf(2, 2, 1, 1, 0, 0), intArrayOf(0, 0, 1, 1, 2, 2))

            // 6. 全部相同
            test(solution, intArrayOf(0, 0, 0), intArrayOf(0, 0, 0))
            test(solution, intArrayOf(1, 1, 1), intArrayOf(1, 1, 1))
            test(solution, intArrayOf(2, 2, 2), intArrayOf(2, 2, 2))

            // 7. 只有两种颜色
            test(solution, intArrayOf(0, 2, 0, 2, 2, 0), intArrayOf(0, 0, 0, 2, 2, 2))
            test(solution, intArrayOf(1, 2, 1, 2, 2, 1), intArrayOf(1, 1, 1, 2, 2, 2))
            test(solution, intArrayOf(0, 1, 0, 1, 1, 0), intArrayOf(0, 0, 0, 1, 1, 1))

            // 8. 容易出错的短数组
            test(solution, intArrayOf(1, 0), intArrayOf(0, 1))
            test(solution, intArrayOf(2, 1), intArrayOf(1, 2))
            test(solution, intArrayOf(2, 0), intArrayOf(0, 2))
            test(solution, intArrayOf(1, 2, 0), intArrayOf(0, 1, 2))
            test(solution, intArrayOf(2, 1, 0), intArrayOf(0, 1, 2))

            // 9. 多个 2 在前面，检查 i 不前进的场景
            test(solution, intArrayOf(2, 2, 0, 1, 1, 0), intArrayOf(0, 0, 1, 1, 2, 2))

            // 10. 乱序综合
            test(solution, intArrayOf(1, 2, 0, 2, 1, 0, 1, 2, 0), intArrayOf(0, 0, 0, 1, 1, 1, 2, 2, 2))
        }

        private fun test(solution: Solution, nums: IntArray, expected: IntArray) {
            solution.sortColors(nums)

            val pass = nums.contentEquals(expected)
            println(
                (if (pass) "PASS" else "FAIL") +
                        " | expected = ${expected.contentToString()}" +
                        ", actual = ${nums.contentToString()}"
            )
        }
    }
}