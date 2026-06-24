package j.medium;

/**
 * 201. BitwiseANDofNumbersRange 数字范围按位与
 * 题目：
 * 给你两个整数 left 和 right ，表示区间 [left, right] ，
 * 返回此区间内所有数字 按位与 的结果（包含 left 、right 端点）。
 * 
 * 示例 1
 * 输入：left = 5, right = 7
 * 输出：4
 * 
 * 示例 2：
 * 输入：left = 0, right = 0
 * 输出：0
 * 
 * 示例 3：
 * 输入：left = 1, right = 2147483647
 * 输出：0
 * 
 * 提示：
 * 0 <= left <= right <= 231 - 1
 */

public class _0201_BitwiseANDofNumbersRange {

    static class Solution {
        public int rangBitwiseAnd(int left, int right) {
            int shift = 0;
            while (left < right) {
                left >>= 1;
                right >>= 1;
                shift++;
            }
            return left << shift;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        test(solution.rangBitwiseAnd(5, 7), 4);
        test(solution.rangBitwiseAnd(0, 0), 0);
        test(solution.rangBitwiseAnd(1, 2147483647), 0);
    }

    private static void test(int actual, int expected) {
        boolean pass = actual == expected;
        System.out.println(
                (pass ? "PASS" : "FAIL")
                        + " | expected = " + expected
                        + ", actual = " + actual);
    }
}