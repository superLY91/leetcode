package j.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
public class _0015_3Sum {

    static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            Arrays.sort(nums);

            for (int i = 0; i < nums.length - 2; i++) {
                // 把相同的第一位去掉
                if (i > 0 && nums[i] == nums[i - 1]) continue;

                // 因为是排序的，第一个大于0了，后续数字都大于0，不可加起来等于0了，所以直接退出
                if (nums[i] > 0) break;

                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];
                    if (sum == 0) {
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        // sum如果等于0，left和right，都要变才可能再出出现等于0
                        left++;
                        right--;

                        // 因为都要边，所以要把重复的去掉
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }

                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else if (sum < 0) {
                        // sum如果小于0， 就要让sun变大，排序后，left代表小的，所以要让小的变大
                        left++;
                    } else {
                        // sum如果大于0， 就要让sun变小，排序后，right代表大的，所以要让大的变小
                        right--;
                    }
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        test(solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4}),
                Arrays.asList(
                        Arrays.asList(-1, -1, 2),
                        Arrays.asList(-1, 0, 1)
                ));

        test(solution.threeSum(new int[]{0, 1, 1}),
                Collections.emptyList());

        test(solution.threeSum(new int[]{0, 0, 0}),
                Arrays.asList(
                        Arrays.asList(0, 0, 0)
                ));

        test(solution.threeSum(new int[]{0, 0, 0, 0}),
                Arrays.asList(
                        Arrays.asList(0, 0, 0)
                ));

        test(solution.threeSum(new int[]{-2, 0, 0, 2, 2}),
                Arrays.asList(
                        Arrays.asList(-2, 0, 2)
                ));

        test(solution.threeSum(new int[]{-2, -1, 1, 2}),
                Collections.emptyList());

        test(solution.threeSum(new int[]{-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6}),
                Arrays.asList(
                        Arrays.asList(-4, -2, 6),
                        Arrays.asList(-4, 0, 4),
                        Arrays.asList(-4, 1, 3),
                        Arrays.asList(-4, 2, 2),
                        Arrays.asList(-2, -2, 4),
                        Arrays.asList(-2, 0, 2)
                ));

        test(solution.threeSum(new int[]{-1, -1, -1, 2, 2}),
                Arrays.asList(
                        Arrays.asList(-1, -1, 2)
                ));

        test(solution.threeSum(new int[]{-3, 0, 1, 2, -1, 1, -2}),
                Arrays.asList(
                        Arrays.asList(-3, 1, 2),
                        Arrays.asList(-2, 0, 2),
                        Arrays.asList(-2, 1, 1),
                        Arrays.asList(-1, 0, 1)
                ));

        test(solution.threeSum(new int[]{1, 2, -2, -1}),
                Collections.emptyList());
    }

    private static void test(List<List<Integer>> actual, List<List<Integer>> expected) {
        List<List<Integer>> actualNormalized = normalize(actual);
        List<List<Integer>> expectedNormalized = normalize(expected);

        boolean pass = actualNormalized.equals(expectedNormalized);

        System.out.println(
                (pass ? "PASS" : "FAIL")
                        + " | expected = " + expectedNormalized
                        + ", actual = " + actualNormalized
        );
    }

    private static List<List<Integer>> normalize(List<List<Integer>> list) {
        List<List<Integer>> result = new ArrayList<>();

        for (List<Integer> triple : list) {
            List<Integer> sortedTriple = new ArrayList<>(triple);
            Collections.sort(sortedTriple);
            result.add(sortedTriple);
        }

        result.sort((a, b) -> {
            for (int i = 0; i < 3; i++) {
                int cmp = Integer.compare(a.get(i), b.get(i));
                if (cmp != 0) return cmp;
            }
            return 0;
        });

        return result;
    }
}