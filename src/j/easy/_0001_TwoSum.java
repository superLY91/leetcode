package j.easy;

import java.util.*;

/**
 * 1. Two Sum
 *
 * 题目：
 * 给定一个整数数组 nums 和一个整数目标值 target，
 * 请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案，但是，数组中同一个元素不能使用两遍。
 * 你可以按任意顺序返回答案。
 *
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 *
 * 示例 2：
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 *
 * 示例 3：
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 * 思路：
 * 用 HashMap 存储「数字 -> 下标」。
 * 遍历数组时，先看 target - nums[i] 是否已经出现过。
 * 如果出现过，直接返回两个下标。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
public class _0001_TwoSum {

    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {
                int num = target - nums[i];
                if (map.containsKey(num)) {
                    return new int[] { map.get(num), i };
                }
                map.put(nums[i], i);
            }
            return new int[] { -1, -1 };
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        test(solution.twoSum(new int[] { 2, 7, 11, 15 }, 9), new int[] { 0, 1 });
        test(solution.twoSum(new int[] { 3, 2, 4 }, 6), new int[] { 1, 2 });
        test(solution.twoSum(new int[] { 3, 3 }, 6), new int[] { 0, 1 });
    }

    private static void test(int[] actual, int[] expected) {
        boolean pass = Arrays.equals(actual, expected);
        System.out.println(
                (pass ? "PASS" : "FAIL")
                        + " | expected = " + Arrays.toString(expected)
                        + ", actual = " + Arrays.toString(actual));
    }
}