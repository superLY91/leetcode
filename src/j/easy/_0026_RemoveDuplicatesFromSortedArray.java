package j.easy;

import java.util.Arrays;

/**
 * 26. Remove Duplicates from Sorted Array（删除有序数组中的重复项）
 *
 * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 * 元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
 *
 * 考虑 nums 的唯一元素的数量为 k。去重后，返回唯一元素的数量 k。
 *
 * nums 的前 k 个元素应包含 排序后 的唯一数字。下标 k - 1 之后的剩余元素可以忽略。
 *
 * 判题标准:
 *
 * 系统会用下面的代码来测试你的题解:
 *
 * int[] nums = [...]; // 输入数组
 * int[] expectedNums = [...]; // 长度正确的期望答案
 *
 * int k = removeDuplicates(nums); // 调用
 *
 * assert k == expectedNums.length;
 * for (int i = 0; i < k; i++) {
 * assert nums[i] == expectedNums[i];
 * }
 * 如果所有断言都通过，那么您的题解将被 通过。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2,_]
 * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
 * 示例 2：
 *
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：5, nums = [0,1,2,3,4,_,_,_,_,_]
 * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * -100 <= nums[i] <= 100
 * nums 已按 非递减 顺序排列。
 *
 * 思路
 *
 * 有序数组中重复元素一定相邻。
 * 用双指针：
 *
 * - `slow` 指向去重后最后一个元素位置
 * - `fast` 遍历数组
 * 发现新值就放到 `slow + 1`
 *
 * 时间复杂度：
 * 空间复杂度：
 */
public class _0026_RemoveDuplicatesFromSortedArray {

    static class Solution {
        public int removeDuplicates(int[] nums) {
            if (nums == null || nums.length == 0) return 0;

            int slow = 0;
            for (int fast = 1; fast < nums.length; fast++) {
                if (nums[fast] != nums[slow]) {
                    slow++;
                    nums[slow] = nums[fast];
                }
            }
            return slow + 1;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        test(solution, new int[]{1}, 1, new int[]{1});
        test(solution, new int[]{1, 2, 3}, 3, new int[]{1, 2, 3});
        test(solution, new int[]{1, 1, 1}, 1, new int[]{1});
        test(solution, new int[]{1, 1, 2}, 2, new int[]{1, 2});
        test(solution, new int[]{1, 2, 2}, 2, new int[]{1, 2});
        test(solution, new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}, 5, new int[]{0, 1, 2, 3, 4});
        test(solution, new int[]{-3, -3, -2, -2, -1, 0, 0}, 4, new int[]{-3, -2, -1, 0});
        test(solution, new int[]{1, 1, 1, 2, 2, 3}, 3, new int[]{1, 2, 3});
        test(solution, new int[]{1, 2, 3, 4, 5}, 5, new int[]{1, 2, 3, 4, 5});
        test(solution, new int[]{0, 0, 0, 0, 0}, 1, new int[]{0});
    }

    private static void test(Solution solution, int[] nums, int expectedK, int[] expectedNums) {
        int[] original = Arrays.copyOf(nums, nums.length);
        int actualK = solution.removeDuplicates(nums);

        boolean kPass = actualK == expectedK;
        boolean numsPass = true;

        for (int i = 0; i < actualK; i++) {
            if (nums[i] != expectedNums[i]) {
                numsPass = false;
                break;
            }
        }

        boolean pass = kPass && numsPass;

        System.out.println((pass ? "PASS" : "FAIL")
                + " | input = " + Arrays.toString(original)
                + " | expectedK = " + expectedK
                + " | actualK = " + actualK
                + " | expectedNums = " + Arrays.toString(expectedNums)
                + " | actualPrefix = " + Arrays.toString(Arrays.copyOf(nums, actualK)));
    }
}