package j.medium;

import java.util.*;

/**
 * 56 Merge Intervals （合并区间）
 *
 * 以数组 intervals 表示若干个区间的集合，
 * 其中单个区间为 intervals[i] = [starti, endi]。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，
 * 该数组需恰好覆盖输入中的所有区间 。
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例 2：
 *
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * 示例 3：
 *
 * 输入：intervals = [[4,7],[1,4]]
 * 输出：[[1,7]]
 * 解释：区间 [1,4] 和 [4,7] 可被视为重叠区间。
 *
 *
 * 提示：
 *
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 *
 * 思路
 *
 * 先按区间起点排序。
 * 遍历时看当前区间和结果最后一个区间是否重叠：
 *
 * - 重叠则合并
 * - 不重叠则直接加入
 *
 * 这题先按区间左端点排序。
 * 排序后从左到右遍历，维护一个结果列表。
 * 对于当前区间，如果它的起点小于等于结果列表最后一个区间的终点，
 * 说明有重叠，就更新终点为两者较大值；
 * 如果不重叠，就直接加入结果列表
 * 。时间复杂度主要是排序，为 O(n log n)，空间复杂度 O(n)。
 *
 * 时间复杂度：O(n log n)
 * 空间复杂度：O(n)
 *
 * 排序：O(n log n)
 * 遍历：O(n)
 * 额外结果集：O(n)
 *
 *
 * 需要具备的知识点
 * 1.
 * 在对 二维数组的每一行 排序。
 * Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
 *
 * 2.
 * List<int[]> list = new ArrayList<>();
 * list.toArray(new int[list.size()][]);
 * 请把 list 里的所有 int[]，
 * 放进一个长度为 list.size() 的 int[][] 里，
 * 然后返回给我。
 * 最简单记法你就记这句：
 * List<int[]> 转 int[][]
 * 写：
 * return list.toArray(new int[list.size()][]);
 *
 */
public class _0056_Merge_Intervals {

	static class Solution {
		public int[][] merge(int[][] intervals) {
			if (intervals.length == 1)
				return intervals;

			// 1. 按起点排序
			Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

			// 2. 用list存合并后的结果
			List<int[]> list = new ArrayList<>();

			// 3. 先放入第一个区间
			list.add(intervals[0]);

			// 4. 从第二个区间开始便利
			for (int i = 1; i < intervals.length; i++) {
				int[] last = list.get(list.size() - 1); // 取结果中的最后一个区间
				int[] cur = intervals[i]; // 当前区间

				// 重叠：当前区间起点 <= 最后一个区间的终点，合并
				if (cur[0] <= last[1]) {
					last[1] = Math.max(cur[1], last[1]);
				} else {
					// 不重叠，直接插入
					list.add(cur);
				}
			}

			return list.toArray(new int[list.size()][]);
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		test(solution.merge(new int[][] {
				{ 1, 3 },
				{ 2, 6 },
				{ 8, 10 },
				{ 15, 18 }
		}), new int[][] {
				{ 1, 6 },
				{ 8, 10 },
				{ 15, 18 }
		});

		test(solution.merge(new int[][] {
				{ 1, 4 }, { 4, 5 }
		}), new int[][] {
				{ 1, 5 }
		});

		test(solution.merge(new int[][] {
				{ 4, 7 }, { 1, 4 }
		}), new int[][] {
				{ 1, 7 }
		});

		test(solution.merge(new int[][] {
				{ 1, 4 }
		}), new int[][] {
				{ 1, 4 }
		});

		test(solution.merge(new int[][] {
				{ 1, 4 }, { 5, 6 }
		}), new int[][] {
				{ 1, 4 }, { 5, 6 }
		});

		test(solution.merge(new int[][] {
				{ 1, 4 }, { 2, 3 }
		}), new int[][] {
				{ 1, 4 }
		});

		test(solution.merge(new int[][] {
				{ 1, 10 }, { 2, 3 }, { 4, 8 }, { 9, 10 }
		}), new int[][] {
				{ 1, 10 }
		});

		test(solution.merge(new int[][] {
				{ 2, 3 }, { 4, 5 }, { 6, 7 }, { 8, 9 }, { 1, 10 }
		}), new int[][] {
				{ 1, 10 }
		});

		test(solution.merge(new int[][] {
				{ 1, 4 }, { 0, 2 }, { 3, 5 }
		}), new int[][] {
				{ 0, 5 }
		});
	}

	private static void test(int[][] actual, int[][] expected) {
		boolean pass = Arrays.deepEquals(actual, expected);
		System.out.println(
				(pass ? "PASS" : "FAIL")
						+ " | expected = " + Arrays.deepToString(expected)
						+ ", actual = " + Arrays.deepToString(actual));
	}
}