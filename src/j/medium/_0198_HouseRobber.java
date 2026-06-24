package j.medium;

/**
 * 198. House Robber（打家劫舍）
 * 
 * 你是一个专业的小偷，计划偷窃沿街的房屋。
 * 每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * 
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * 
 * 
 * 提示：
 * 
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 * 
 * 思路
 * DP：
 * - 不偷当前房子：`dp[i-1]`
 * - 偷当前房子：`dp[i-2] + nums[i]`
 * 取两者最大值。
 * 
 * 你先记住一句话：
 * 
 * 到第 i 间房时，只有两种选择：偷它 或 不偷它。
 * 
 * dp[i] = 偷到第 i 间房时，能偷到的最大金额
 * 
 * 这题是动态规划。定义 dp[i] 表示考虑前 i 间房后，能偷到的最大金额。
 * 对于第 i 间房，有两种选择：
 * 不偷当前房子，金额是 dp[i-1]；
 * 偷当前房子，则不能偷前一间，金额是 dp[i-2] + nums[i]。
 * 所以状态转移方程是 dp[i] = max(dp[i-1], dp[i-2] + nums[i])
 * 因为只依赖前两个状态，所以可以把空间优化到 O(1)。
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class _0198_HouseRobber {

    static class Solution {
        public int rob(int[] nums) {
            if (nums.length == 1) return nums[0];
            if (nums.length == 2) return Math.max(nums[0], nums[1]);

            int prev2 = nums[0];
            int prev1 = Math.max(nums[0], nums[1]);

            for (int i = 2; i < nums.length; i++) {
                int cur = Math.max(prev1, prev2 + nums[i]);
                prev2 = prev1;
                prev1 = cur;
            }

           return prev1;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        test(solution.rob(new int[] { 1, 2, 3, 1 }), 4);
        test(solution.rob(new int[] { 1, 2, 3, 1 }), 4);
        test(solution.rob(new int[] { 1, 2, 3, 1 }), 4);
    }

    private static void test(int actual, int expected) {
        boolean pass = actual == expected;
        System.out.println(
                (pass ? "PASS" : "FAIL")
                        + " | expected = " + expected
                        + ", actual = " + actual);
    }

}