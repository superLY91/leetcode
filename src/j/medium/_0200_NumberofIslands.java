package j.medium;

/**
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
public class _0200_NumberofIslands {

    static class Solution {
        public int numIslands(char[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int cout = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        cout++;
                        dfs(grid, i, j);
                    }
                }
            }

            return cout;
        }

        // 从 i,j 这个坐标开始，深度搜索，把所有连通的‘1’，都标记成’0‘，表示已经访问过
        public void dfs(char[][] grid, int i, int j) {
            int m = grid.length;
            int n = grid[0].length;

            // 越界
            if (i < 0 || i >= m || j < 0 || j >= n) {
                return;
            }

            // 碰到水就返回
            if (grid[i][j] == '0') {
                return;
            }

            // 走到这个步就一定是大陆了，把大陆1，重置成0
            grid[i][j] = '0';

            // 开始从这个位置上下左右搜索
            dfs(grid, i - 1, j); // 上
            dfs(grid, i + 1, j); // 下
            dfs(grid, i, j - 1); // 左
            dfs(grid, i, j + 1); // 右
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        test(solution.numIslands(new char[][] {
                { '1', '1', '1', '1', '0' },
                { '1', '1', '0', '1', '0' },
                { '1', '1', '0', '0', '0' },
                { '0', '0', '0', '0', '0' }
        }), 1);

        test(solution.numIslands(new char[][] {
                { '1', '1', '0', '0', '0' },
                { '1', '1', '0', '0', '0' },
                { '0', '0', '1', '0', '0' },
                { '0', '0', '0', '1', '1' }
        }), 3);

        test(solution.numIslands(new char[][] {
                { '1' }
        }), 1);

        test(solution.numIslands(new char[][] {
                { '0' }
        }), 0);

        test(solution.numIslands(new char[][] {
                { '1', '0', '1', '0', '1' }
        }), 3);

        test(solution.numIslands(new char[][] {
                { '1' },
                { '0' },
                { '1' },
                { '1' },
                { '0' }
        }), 2);

        test(solution.numIslands(new char[][] {
                { '1', '1', '0', '0' },
                { '1', '0', '0', '1' },
                { '0', '0', '1', '1' },
                { '0', '0', '0', '0' }
        }), 2);
    }

    private static void test(int actual, int expected) {
        boolean pass = actual == expected;
        System.out.println(
                (pass ? "PASS" : "FAIL")
                        + " | expected = " + expected
                        + ", actual = " + actual);
    }
}