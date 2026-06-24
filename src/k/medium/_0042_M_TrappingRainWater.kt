package k.medium

/**
 * https://leetcode.cn/problems/trapping-rain-water/
 *
 * 42. 接雨水
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 *
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *
 *
 * 提示：
 *
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 */
class _0042_M_TrappingRainWater {

    class Solution {
        fun trap(height: IntArray): Int {
            var left = 0
            var right = height.size - 1
            var leftMax = 0
            var rightMax = 0
            var result = 0

            while (left < right) {
                leftMax = maxOf(leftMax, height[left])
                rightMax = maxOf(rightMax, height[right])

                if (leftMax < rightMax) {
                    result += leftMax - height[left]
                    left++
                } else {
                    result += rightMax - height[right]
                    right--
                }
            }
            return result
        }
    }
}

fun main() {
    val solution = _0042_M_TrappingRainWater.Solution()

    test(solution, intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1), 6)
    test(solution, intArrayOf(4, 2, 0, 3, 2, 5), 9)
    test(solution, intArrayOf(0), 0)
    test(solution, intArrayOf(1, 2), 0)
    test(solution, intArrayOf(1, 2, 3, 4), 0)
    test(solution, intArrayOf(4, 3, 2, 1), 0)
    test(solution, intArrayOf(2, 0, 2), 2)
    test(solution, intArrayOf(3, 0, 0, 2, 0, 4), 10)
    test(solution, intArrayOf(5, 4, 1, 2), 1)
    test(solution, intArrayOf(4, 2, 3), 1)
}

private fun test(solution: _0042_M_TrappingRainWater.Solution, height: IntArray, expected: Int) {
    val actual = solution.trap(height)
    val pass = actual == expected
    println(
        (if (pass) "PASS" else "FAIL") +
                " | input = ${height.contentToString()}" +
                " | expected = $expected" +
                ", actual = $actual"
    )
}
