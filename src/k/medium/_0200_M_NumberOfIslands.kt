package k.medium

/**
 * https://leetcode.cn/problems/number-of-islands/
 *
 * 200 Number of Islands
 *
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 示例 1：
 *
 * 输入：grid = [
 * ['1','1','1','1','0'],
 * ['1','1','0','1','0'],
 * ['1','1','0','0','0'],
 * ['0','0','0','0','0']
 * ]
 * 输出：1
 * 示例 2：
 *
 * 输入：grid = [
 * ['1','1','0','0','0'],
 * ['1','1','0','0','0'],
 * ['0','0','1','0','0'],
 * ['0','0','0','1','1']
 * ]
 * 输出：3
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 *
 * 这题用 DFS。遍历整个网格，遇到一个 '1'，说明发现了一个新岛屿，计数加一。
 * 然后从这个点开始 DFS，把上下左右所有连通的 '1' 都标记成 '0'，表示已经访问过。
 * 这样每个岛屿只会被统计一次。
 * 时间复杂度是 O(m*n)，因为每个格子最多访问一次。
 *
 * 时间复杂度： O(m*n)
 * 空间复杂度： O(m*n)
 */
class _0200_M_NumberOfIslands {

    class Solution {
        fun numIslands(grid: Array<CharArray>): Int {
            var count = 0

            for (row in grid.indices) {
                for (col in grid[0].indices) {
                    if (grid[row][col] == '1') {
                        count++
                        dfs(grid, row, col)
                    }
                }
            }
            return count
        }

        private fun dfs(grid: Array<CharArray>, row: Int, col: Int) {
            if (row !in grid.indices || col !in grid[0].indices) return
            if (grid[row][col] != '1') return

            grid[row][col] = '0'

            dfs(grid, row - 1, col)
            dfs(grid, row + 1, col)
            dfs(grid, row, col - 1)
            dfs(grid, row, col + 1)
        }
    }
}

fun main() {
    val solution = _0200_M_NumberOfIslands.Solution()

    test(
        solution.numIslands(
            gridOf(
                "11110",
                "11010",
                "11000",
                "00000"
            )
        ),
        1
    )

    test(
        solution.numIslands(
            gridOf(
                "11000",
                "11000",
                "00100",
                "00011"
            )
        ),
        3
    )

    test(
        solution.numIslands(
            gridOf(
                "1"
            )
        ),
        1
    )

    test(
        solution.numIslands(
            gridOf(
                "0"
            )
        ),
        0
    )

    test(
        solution.numIslands(
            gridOf(
                "10101"
            )
        ),
        3
    )

    test(
        solution.numIslands(
            gridOf(
                "1",
                "0",
                "1",
                "1",
                "0"
            )
        ),
        2
    )

    test(
        solution.numIslands(
            gridOf(
                "1100",
                "1001",
                "0011",
                "0000"
            )
        ),
        2
    )

    test(
        solution.numIslands(
            gridOf(
                "000",
                "000",
                "000"
            )
        ),
        0
    )

    test(
        solution.numIslands(
            gridOf(
                "111",
                "111",
                "111"
            )
        ),
        1
    )
}

private fun gridOf(vararg rows: String): Array<CharArray> {
    return Array(rows.size) { index -> rows[index].toCharArray() }
}

private fun test(actual: Int, expected: Int) {
    val pass = actual == expected
    println(
        (if (pass) "PASS" else "FAIL") +
                " | expected = $expected" +
                ", actual = $actual"
    )
}
