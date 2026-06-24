package j.easy;

/**
 * 1. 704. Binary Search（二分查找）
 * 
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target ，
 * 写一个函数搜索 nums 中的 target，如果 target 存在返回下标，否则返回 -1。
 * 
 * 你必须编写一个具有 O(log n) 时间复杂度的算法。
 * 
 * 
 * 示例 1:
 * 
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * 示例 2:
 * 
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 * 
 * 提示：
 * 
 * 你可以假设 nums 中的所有元素是不重复的。
 * n 将在 [1, 10000]之间。
 * nums 的每个元素都将在 [-9999, 9999]之间。
 * 
 * 自己思路：
 * 因为是升序，所以用用二分查找，right - left = 算出长度
 * 算出长度 middle = (算出长度 / 2) - 1
 * if 直到left <= right
 * if middle < targt
 * left = middle + 1
 * if middle > targt
 * right = middle - 1
 * if middle == target
 * return true
 * 
 * 
 * 思路
 * 标准二分模板：
 * - `mid == target` 返回
 * - 小了去右边
 * - 大了去左边
 * 
 * 
 * 时间复杂度：O(log n)
 * 空间复杂度：O(n)
 */
public class _0704_BinarySearch {

    static class Solution {
        public int search(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int len = (right - left) / 2;
                int mid = left + len;

                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        test(solution.search(new int[] { -1, 0, 3, 5, 9, 12 }, 9), 4);
        test(solution.search(new int[] { -1, 0, 3, 5, 9, 12 }, 2), -1);
        test(solution.search(new int[] { 1 }, 1), 0);
        test(solution.search(new int[] { 1 }, 0), -1);
        test(solution.search(new int[] { 1, 3, 5, 7, 9 }, 7), 3);
    }

    private static void test(int actual, int expected) {
        boolean pass = actual == expected;
        System.out.println(
                (pass ? "PASS" : "FAIL")
                        + " | expected = " + expected
                        + ", actual = " + actual);
    }
}