package j.easy;
import java.util.*;
/**
 * 283. Move Zeroes（移动零）
 * 
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * 
 * 示例 1:
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 
 * 示例 2:
 * 输入: nums = [0]
 * 输出: [0]
 * 
 * 提示:
 * 
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 * 
 * 自己思路：
 * 两个指针，left right
 * right遍历，发现不是0就和left换，left++
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class _0283_MoveZeroes {

    static class Solution {
        public void moveZeroes(int[] nums) {
            int left = 0;
            for (int right = 0; right < nums.length; right++) {
                if (nums[right] != 0) {
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                    left++;
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        test(solution, new int[] { 0, 1, 0, 3, 12 }, new int[] { 1, 3, 12, 0, 0 });
        test(solution, new int[] { 0 }, new int[] { 0 });
        test(solution, new int[] { 1, 2, 3 }, new int[] { 1, 2, 3 });
        test(solution, new int[] { 0, 0, 1 }, new int[] { 1, 0, 0 });
        test(solution, new int[] { 4, 0, 5, 0, 0, 3, 0, 1 }, new int[] { 4, 5, 3, 1, 0, 0, 0, 0 });
    }

    public static void test(Solution solution, int[] input, int[] expected) {
        solution.moveZeroes(input);

        boolean pass = Arrays.equals(input, expected);
        System.out.println(
                (pass ? "PASS" : "FAIL")
                        + " | expected = " + Arrays.toString(expected)
                        + ", actual = " + Arrays.toString(input));
    }
}