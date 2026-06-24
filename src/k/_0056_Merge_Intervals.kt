package k

/**
 * 56. Merge Intervals（合并区间）
 */
class _0056_Merge_Intervals {

    class Solution {
        fun merge(intervals: Array<IntArray>): Array<IntArray> {
            if (intervals.size == 1) return intervals

            // 1. 按起点排序
            intervals.sortBy { it[0] }

            // 2. 用 MutableList 存合并后的结果
            val list = mutableListOf<IntArray>()

            // 3. 先放入第一个区间
            list.add(intervals[0])

            // 4. 从第二个区间开始遍历
            for (i in 1 until intervals.size) {
                val last = list[list.size - 1]   // 结果中的最后一个区间
                val cur = intervals[i]           // 当前区间

                // 重叠：当前区间起点 <= 最后一个区间终点
                if (cur[0] <= last[1]) {
                    last[1] = maxOf(last[1], cur[1])
                } else {
                    // 不重叠，直接加入
                    list.add(cur)
                }
            }

            return list.toTypedArray()
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = Solution()

            test(
                solution.merge(
                    arrayOf(
                        intArrayOf(1, 3),
                        intArrayOf(2, 6),
                        intArrayOf(8, 10),
                        intArrayOf(15, 18)
                    )
                ),
                arrayOf(
                    intArrayOf(1, 6),
                    intArrayOf(8, 10),
                    intArrayOf(15, 18)
                )
            )

            test(
                solution.merge(
                    arrayOf(
                        intArrayOf(1, 4),
                        intArrayOf(4, 5)
                    )
                ),
                arrayOf(
                    intArrayOf(1, 5)
                )
            )

            test(
                solution.merge(
                    arrayOf(
                        intArrayOf(4, 7),
                        intArrayOf(1, 4)
                    )
                ),
                arrayOf(
                    intArrayOf(1, 7)
                )
            )

            test(
                solution.merge(
                    arrayOf(
                        intArrayOf(1, 4)
                    )
                ),
                arrayOf(
                    intArrayOf(1, 4)
                )
            )

            test(
                solution.merge(
                    arrayOf(
                        intArrayOf(1, 4),
                        intArrayOf(5, 6)
                    )
                ),
                arrayOf(
                    intArrayOf(1, 4),
                    intArrayOf(5, 6)
                )
            )

            test(
                solution.merge(
                    arrayOf(
                        intArrayOf(1, 4),
                        intArrayOf(2, 3)
                    )
                ),
                arrayOf(
                    intArrayOf(1, 4)
                )
            )

            test(
                solution.merge(
                    arrayOf(
                        intArrayOf(1, 10),
                        intArrayOf(2, 3),
                        intArrayOf(4, 8),
                        intArrayOf(9, 10)
                    )
                ),
                arrayOf(
                    intArrayOf(1, 10)
                )
            )

            test(
                solution.merge(
                    arrayOf(
                        intArrayOf(2, 3),
                        intArrayOf(4, 5),
                        intArrayOf(6, 7),
                        intArrayOf(8, 9),
                        intArrayOf(1, 10)
                    )
                ),
                arrayOf(
                    intArrayOf(1, 10)
                )
            )

            test(
                solution.merge(
                    arrayOf(
                        intArrayOf(1, 4),
                        intArrayOf(0, 2),
                        intArrayOf(3, 5)
                    )
                ),
                arrayOf(
                    intArrayOf(0, 5)
                )
            )
        }

        private fun test(actual: Array<IntArray>, expected: Array<IntArray>) {
            val pass = actual.contentDeepEquals(expected)
            println(
                (if (pass) "PASS" else "FAIL") +
                        " | expected = " + expected.contentDeepToString() +
                        ", actual = " + actual.contentDeepToString()
            )
        }
    }
}