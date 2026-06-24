package j.medium;

import java.util.*;

/**
 * 39 Combination Sum 组合总和 (M，数组，回溯)
 *
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
 * 找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 *
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 *
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 *
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 * 示例 2：
 *
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * 示例 3：
 *
 * 输入: candidates = [2], target = 1
 * 输出: []
 *
 * 提示：
 *
 * 1 <= candidates.length <= 30
 * 2 <= candidates[i] <= 40
 * candidates 的所有元素 互不相同
 * 1 <= target <= 40
 *
 * 思路：
 * 回溯就是
 * 选定
 * 递归
 * 撤销
 *
 * 时间复杂度：O(N^(T / M))
 * 其中 N 是 candidates 的长度，T 是 target，M 是 candidates 中的最小值。
 * 因为回溯树的最大深度约为 T / M，每层最坏有 N 个分支。
 *
 * 空间复杂度：O(T / M)
 * 主要是递归栈深度和当前 path 的长度。
 */
public class _0039_CombinationSum {

    static class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> path = new ArrayList<>();

            backtrack(candidates, 0, 0, target, path, result);
            return result;
        }

        private void backtrack(int[] candidates, int start, int sum, int target,
                               List<Integer> path, List<List<Integer>> result) {
            // 如果 sum >= target 就返回，如果sum == target就result.add(patch)

            if (sum == target) {
                result.add(new ArrayList<>(path));
                return;
            }

            if (sum > target) {
                return;
            }

            for (int i = start; i < candidates.length; i++) {
                // 加入
                path.add(candidates[i]);

                // 递归 ：i 而不是 i+1，因为当前数字可以重复使用
                backtrack(candidates, i, sum + candidates[i], target, path, result);

                // 撤销
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        test(
                solution.combinationSum(new int[]{2, 3, 6, 7}, 7),
                Arrays.asList(
                        Arrays.asList(2, 2, 3),
                        Arrays.asList(7)
                )
        );

        test(
                solution.combinationSum(new int[]{2, 3, 5}, 8),
                Arrays.asList(
                        Arrays.asList(2, 2, 2, 2),
                        Arrays.asList(2, 3, 3),
                        Arrays.asList(3, 5)
                )
        );

        test(
                solution.combinationSum(new int[]{2}, 1),
                Collections.emptyList()
        );

        test(
                solution.combinationSum(new int[]{3}, 9),
                Arrays.asList(
                        Arrays.asList(3, 3, 3)
                )
        );

        test(
                solution.combinationSum(new int[]{5}, 6),
                Collections.emptyList()
        );

        test(
                solution.combinationSum(new int[]{2, 4, 6}, 8),
                Arrays.asList(
                        Arrays.asList(2, 2, 2, 2),
                        Arrays.asList(2, 2, 4),
                        Arrays.asList(2, 6),
                        Arrays.asList(4, 4)
                )
        );

        test(
                solution.combinationSum(new int[]{2, 3, 5}, 5),
                Arrays.asList(
                        Arrays.asList(2, 3),
                        Arrays.asList(5)
                )
        );
    }

    private static void test(List<List<Integer>> actual, List<List<Integer>> expected) {
        boolean pass = sameCombinations(actual, expected);

        System.out.println(
                (pass ? "PASS" : "FAIL")
                        + " | expected = " + expected
                        + ", actual = " + actual
        );
    }

    private static boolean sameCombinations(List<List<Integer>> actual, List<List<Integer>> expected) {
        if (actual == null || expected == null) return actual == expected;
        if (actual.size() != expected.size()) return false;

        Set<String> actualSet = normalize(actual);
        Set<String> expectedSet = normalize(expected);

        return actualSet.equals(expectedSet);
    }

    private static Set<String> normalize(List<List<Integer>> lists) {
        Set<String> set = new HashSet<>();

        for (List<Integer> list : lists) {
            List<Integer> copy = new ArrayList<>(list);
            Collections.sort(copy);
            set.add(copy.toString());
        }

        return set;
    }
}