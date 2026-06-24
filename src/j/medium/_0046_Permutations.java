package j.medium;

import java.util.*;

/**
 * 46 Permutations 全排列
 *
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 *
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：[[1]]
 *
 * 提示：
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 *
 * 思路：
 * 回溯（Backtracking）不是排列组合
 * 排列组合：更偏数学，问你“有多少种”
 * 全排列：要你把每一种情况都真正列出来
 *
 * 为什么叫回溯
 * 因为过程是：
 * 先选一个数
 * 继续往下选
 * 选到底后，保存结果
 * 然后“撤销刚才那一步”，回去尝试别的选择
 * 所以叫：
 * 做选择 -> 递归 -> 撤销选择
 * 这个模板特别重要
 * for 每一个可选元素:
 * 做选择
 * 递归下一层
 * 撤销选择
 *
 * 时间复杂度
 * O(n × n!)
 *
 * 原因：
 *
 * 一共有 n! 种排列
 * 每种排列长度是 n
 * 每次加入结果时要复制一个长度为 n 的列表
 *
 * 所以一般写：
 *
 * O(n * n!)
 * 空间复杂度
 * O(n)
 *
 * 递归栈 + used + path 的额外空间大约是 O(n)。
 *
 * 如果把结果集也算上，那总空间当然更大，因为要存所有排列。
 *
 * n! 读作 n 的阶乘。
 *
 * 意思是：
 *
 * n! = n × (n-1) × (n-2) × ... × 2 × 1
 *
 */
public class _0046_Permutations {

    static class Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            boolean[] used = new boolean[nums.length];
            backtrack(nums, used, path, result);
            return result;
        }

        // 基于当前 path 和 used 状态，继续往下找所有可能的排列。
        private void backtrack(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> result) {
            // 终止条件：路径长度 等于 num的长度，说明找到了一个完整的排列
            if (path.size() == nums.length) {
                // 因为要拷贝一份当前结果。不能直接把 path 放进去，
                // 如果放进去那最后 result 里存的会全是同一个 path，结果就乱了。不然回溯时它会继续变。
                result.add(new ArrayList<>(path));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                // 如果被选择了就继续
                if (used[i]) {
                    continue;
                }
                // 做选择
                path.add(nums[i]);
                used[i] = true;

                backtrack(nums, used, path, result);

                // 撤销选择
                path.remove(path.size() - 1);
                used[i] = false;
            }
        }

        public static void main(String[] args) {
            Solution solution = new Solution();

            test(
                    solution.permute(new int[]{1}),
                    Arrays.asList(
                            Arrays.asList(1)
                    )
            );

            test(
                    solution.permute(new int[]{0, 1}),
                    Arrays.asList(
                            Arrays.asList(0, 1),
                            Arrays.asList(1, 0)
                    )
            );

            test(
                    solution.permute(new int[]{1, 2, 3}),
                    Arrays.asList(
                            Arrays.asList(1, 2, 3),
                            Arrays.asList(1, 3, 2),
                            Arrays.asList(2, 1, 3),
                            Arrays.asList(2, 3, 1),
                            Arrays.asList(3, 1, 2),
                            Arrays.asList(3, 2, 1)
                    )
            );

            test(
                    solution.permute(new int[]{-1, 0, 1}),
                    Arrays.asList(
                            Arrays.asList(-1, 0, 1),
                            Arrays.asList(-1, 1, 0),
                            Arrays.asList(0, -1, 1),
                            Arrays.asList(0, 1, -1),
                            Arrays.asList(1, -1, 0),
                            Arrays.asList(1, 0, -1)
                    )
            );

            test(
                    solution.permute(new int[]{3, 1, 2}),
                    Arrays.asList(
                            Arrays.asList(3, 1, 2),
                            Arrays.asList(3, 2, 1),
                            Arrays.asList(1, 3, 2),
                            Arrays.asList(1, 2, 3),
                            Arrays.asList(2, 3, 1),
                            Arrays.asList(2, 1, 3)
                    )
            );
        }

        private static void test(List<List<Integer>> actual, List<List<Integer>> expected) {
            boolean pass = samePermutations(actual, expected);

            System.out.println(
                    (pass ? "PASS" : "FAIL")
                            + " | expected = " + expected
                            + ", actual = " + actual
            );
        }

        private static boolean samePermutations(List<List<Integer>> actual, List<List<Integer>> expected) {
            if (actual == null || expected == null) return actual == expected;
            if (actual.size() != expected.size()) return false;

            Set<String> actualSet = new HashSet<>();
            Set<String> expectedSet = new HashSet<>();

            for (List<Integer> list : actual) {
                actualSet.add(list.toString());
            }

            for (List<Integer> list : expected) {
                expectedSet.add(list.toString());
            }

            return actualSet.equals(expectedSet);
        }
    }
}