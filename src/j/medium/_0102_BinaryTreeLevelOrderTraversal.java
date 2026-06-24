package j.medium;

import java.util.*;

import j.common.TreeNode;
import j.common.TreeTestUtils;

/**
 * 102. Binary Tree Level Order Traversal（二叉树的层序遍历）
 * 
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 * 
 * 示例 1：
 * 
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
 * 示例 2：
 * 
 * 输入：root = [1]
 * 输出：[[1]]
 * 示例 3：
 * 
 * 输入：root = []
 * 输出：[]
 * 
 * 
 * 提示：
 * 
 * 树中节点数目在范围 [0, 2000] 内
 * -1000 <= Node.val <= 1000
 * 
 * 思路
 * 用队列做 BFS。广度优先搜索
 * 每次记录当前层节点数量 `size`，依次弹出并加入下一层节点
 * 
 * Queue 用 LinkedList
 * queue.offer(root); 是添加 和add 一样
 * queue.poll(); 是取出最上层的，queue.peek() 是返回最上层，但是不删除
 * 
 * BFS。广度优先搜索，理解为一层一层往外找。队列 Queue 先进先出
 * 
 * BFS
 * Breadth-First Search，广度优先搜索
 * 一层一层找
 * 常用队列
 * 适合层序遍历、最短路径
 * 
 * DFS
 * Depth-First Search，深度优先搜索
 * 一条路先走到底，再回头
 * 常用递归 / 栈
 * 适合回溯、连通块、树递归遍历
 * 
 * BFS = 一层一层搜 = 用队列
 * DFS = 一路往下搜 = 用递归/栈
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
public class _0102_BinaryTreeLevelOrderTraversal {

    static class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null)
                return res;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> level = new ArrayList<>();

                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    level.add(node.value);

                    if (node.left != null)
                        queue.offer(node.left);
                    if (node.right != null)
                        queue.offer(node.right);
                }
                res.add(level);
            }

            return res;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        Integer[] input1 = { 3, 9, 20, null, null, 15, 7 };
        TreeNode root1 = TreeTestUtils.buildTree(input1);
        TreeTestUtils.testListList(
                solution.levelOrder(root1),
                Arrays.asList(
                        Arrays.asList(3),
                        Arrays.asList(9, 20),
                        Arrays.asList(15, 7)),
                input1);

        Integer[] input2 = { 1 };
        TreeNode root2 = TreeTestUtils.buildTree(input2);
        TreeTestUtils.testListList(
                solution.levelOrder(root2),
                Arrays.asList(
                        Arrays.asList(1)),
                input2);

        Integer[] input3 = {};
        TreeNode root3 = TreeTestUtils.buildTree(input3);
        TreeTestUtils.testListList(
                solution.levelOrder(root3),
                Arrays.asList(),
                input3);
    }

}