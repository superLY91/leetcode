package j.medium;

/**
 * 53. Maximum Subarray（最大子数组和）
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 
 * 子数组是数组中的一个连续部分。
 * 
 * 
 * 示例 1：
 * 
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 * 
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 * 
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 * 
 * 
 * 提示：
 * 
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 
 * 
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 * 
 * 思路
 * 
 * 定义 `cur` 为“以当前位置结尾”的最大子数组和：
 * - 要么从当前元素重新开始
 * - 要么接在前面的连续和后面
 * 
 * 这题可以用动态规划。定义 cur 表示以当前位置结尾的最大子数组和。
 * 对于当前元素 nums[i]，要么从它自己重新开始，要么接在前面的连续子数组后面，
 * 所以状态转移方程是 cur = max(nums[i], cur + nums[i])。
 * 然后用 maxSum 维护全局最大值。时间复杂度 O(n)，空间复杂度 O(1)。
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class _0053_MaximumSubarray {

    static class Solution {
        public int maxSubArray(int[] nums) {
            int cur = nums[0];
            int maxSum = nums[0];
            for (int i = 1; i < nums.length; i++) {
                cur = Math.max(nums[i], cur + nums[i]);
                maxSum = Math.max(maxSum, cur);
            }
            return maxSum;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        test(solution.maxSubArray(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }), 6);
        test(solution.maxSubArray(new int[] { 1 }), 1);
        test(solution.maxSubArray(new int[] { 5, 4, -1, 7, 8 }), 23);

        // 补充测试
        test(solution.maxSubArray(new int[] { -1, -2, -3, -4 }), -1);
        test(solution.maxSubArray(new int[] { 0 }), 0);
        test(solution.maxSubArray(new int[] { -2, -1 }), -1);
        test(solution.maxSubArray(new int[] { 1, 2, 3, 4 }), 10);
        test(solution.maxSubArray(new int[] { 8, -19, 5, -4, 20 }), 21);
    }

    private static void test(int actual, int expected) {
        boolean pass = actual == expected;
        System.out.println(
                (pass ? "PASS" : "FAIL")
                        + " | expected = " + expected
                        + ", actual = " + actual);
    }
}