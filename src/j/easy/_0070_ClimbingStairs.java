package j.easy;

/**
 * 70. Climbing Stairs（爬楼梯）
 * 
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 
 * 示例 1：
 * 
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * 
 * 示例 2：
 * 
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 * 
 * 提示：
 * 
 * 1 <= n <= 45
 * 
 * 自己思路
 * 这个是一个斐波那契数列
 * 第n个台阶，可以一步上，也可以两步上，只有这两种方法，但是都是在上到n-1 和 n-2 的基础上
 * f(n) 代表上到n个台阶的方法数
 * f(n) = f(n-2) + f(n-1)
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class _0070_ClimbingStairs {

    static class Solution {
        public int climbStairs(int n) {
            if (n <= 2) return n;

            int a = 1;
            int b = 2;
            for(int i = 3; i <= n; i++) {
                int sum = a + b;
                a = b;
                b = sum;
            }
            return b;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        test(solution.climbStairs(2), 2);
        test(solution.climbStairs(3), 3);
    }

    private static void test(int actual, int expected) {
        boolean pass = actual == expected;
        System.out.println(
                (pass ? "PASS" : "FAIL")
                        + " | expected = " + expected
                        + ", actual = " + actual);
    }

}