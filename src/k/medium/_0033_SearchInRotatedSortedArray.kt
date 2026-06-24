package k.medium

/**
 * 33. Search in Rotated Sorted Array 搜索旋转排序数组
 *
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 *
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 向左旋转，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,5,6,7] 下标 3 上向左旋转后可能变为 [4,5,6,7,0,1,2] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 *
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 *
 * 示例 1：
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 示例 2：
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：nums = [1], target = 0
 * 输出：-1
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * nums 中的每个值都 独一无二
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -104 <= target <= 104
 *
 *
 * 自己思路
 * 这不是简单的便利就行了吗，target必须是发生旋转的那个位置才行吗
 *
 * 时间复杂度：
 * 空间复杂度：
 */
class _0033_SearchInRotatedSortedArray {

    class Solution {
        fun search(nums: IntArray, target: Int): Int {
            var left = 0
            var right = nums.size - 1

            while (left <= right) {
                var mid = left + (right - left) / 2

                if (nums[mid] == target) {
                    return mid;
                }

                // 左边有序
                if (nums[left] <= nums[mid]) {
                    if (nums[left] <= target && target < nums[mid]) {
                        right = mid - 1
                    } else {
                        left = mid + 1
                    }
                }
                // 右边有序
                else {
                    if (nums[mid] < target && target <= nums[right]) {
                        left = mid + 1
                    } else {
                        right = mid - 1
                    }
                }
            }
            return -1;
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = Solution()

            test(solution.search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 0), 4)
            test(solution.search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 3), -1)
            test(solution.search(intArrayOf(1), 0), -1)
        }

        private fun test(actual: Int, expected: Int) {
            val pass = actual == expected
            println(
                (if (pass) "PASS" else "FAIL") +
                        " | expected = " + expected +
                        ", actual = " + actual
            )
        }
    }
}