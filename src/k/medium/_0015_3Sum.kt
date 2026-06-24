package k.medium

/**
 *
 * 15. 3Sum（三数之和
 *
 * 给你一个整数数组 nums ，判断是否存在三元组
 * [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。
 * 请你返回所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 * 示例 2：
 *
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 * 示例 3：
 *
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 *
 *
 * 提示：
 *
 * 3 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 *
 * 思路
 *
 * 先排序。
 * 固定一个数 `nums[i]`，然后用双指针在后面找另外两个数。
 * 注意去重。
 *
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(log n) ~ O(n)
 */
class _0015_3Sum {

    class Solution {
        fun threeSum(nums: IntArray): List<List<Int>> {
            val result = mutableListOf<List<Int>>()
            nums.sort()

            for (i in 0 until nums.size - 2) {
                // 第一个去重
                if (i > 0 && nums[i] == nums[i - 1]) continue
                // 因为是排序的，第一个大于0了，后续数字都大于0，不可能加起来等于0了，所以直接退出
                if (nums[i] > 0) break

                var left = i + 1
                var right = nums.size - 1
                while (left < right) {
                    val sum = nums[i] + nums[left] + nums[right]

                    if (sum == 0) {
                        result.add(listOf(nums[i], nums[left], nums[right]))
                        // sum如果等于0，left和right都要变，才可能再出现等于0
                        left++
                        right--

                        // 去重
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++
                        }
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--
                        }
                    } else if (sum < 0) {
                        // sum如果小于0，就要让sum变大
                        left++
                    } else {
                        // sum如果大于0，就要让sum变小
                        right--
                    }
                }
            }
            return result
        }
    }
}

fun main() {
    val solution = _0015_3Sum.Solution()

    test(
        solution.threeSum(intArrayOf(-1, 0, 1, 2, -1, -4)),
        listOf(
            listOf(-1, -1, 2),
            listOf(-1, 0, 1)
        )
    )

    test(solution.threeSum(intArrayOf(0, 1, 1)), emptyList())

    test(
        solution.threeSum(intArrayOf(0, 0, 0)),
        listOf(listOf(0, 0, 0))
    )

    test(
        solution.threeSum(intArrayOf(0, 0, 0, 0)),
        listOf(listOf(0, 0, 0))
    )

    test(
        solution.threeSum(intArrayOf(-2, 0, 0, 2, 2)),
        listOf(listOf(-2, 0, 2))
    )

    test(solution.threeSum(intArrayOf(-2, -1, 1, 2)), emptyList())

    test(
        solution.threeSum(intArrayOf(-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6)),
        listOf(
            listOf(-4, -2, 6),
            listOf(-4, 0, 4),
            listOf(-4, 1, 3),
            listOf(-4, 2, 2),
            listOf(-2, -2, 4),
            listOf(-2, 0, 2)
        )
    )

    test(
        solution.threeSum(intArrayOf(-1, -1, -1, 2, 2)),
        listOf(listOf(-1, -1, 2))
    )

    test(
        solution.threeSum(intArrayOf(-3, 0, 1, 2, -1, 1, -2)),
        listOf(
            listOf(-3, 1, 2),
            listOf(-2, 0, 2),
            listOf(-2, 1, 1),
            listOf(-1, 0, 1)
        )
    )

    test(solution.threeSum(intArrayOf(1, 2, -2, -1)), emptyList())
}

private fun test(actual: List<List<Int>>, expected: List<List<Int>>) {
    val actualNormalized = normalize(actual)
    val expectedNormalized = normalize(expected)

    val pass = actualNormalized == expectedNormalized

    println(
        (if (pass) "PASS" else "FAIL") +
                " | expected = $expectedNormalized" +
                ", actual = $actualNormalized"
    )
}

private fun normalize(list: List<List<Int>>): List<List<Int>> {
    val result = mutableListOf<List<Int>>()

    for (triple in list) {
        result.add(triple.sorted())
    }

    return result.sortedWith { a, b ->
        for (i in 0 until 3) {
            val cmp = a[i].compareTo(b[i])
            if (cmp != 0) return@sortedWith cmp
        }
        0
    }
}