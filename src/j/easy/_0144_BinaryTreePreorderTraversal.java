package j.easy;

import j.common.TreeNode;

import java.util.*;

/**
 * 144. Binary Tree Preorder Traversal（二叉树的前序遍历）
 *
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 *
 * 示例 1：
 *
 * 输入：root = [1,null,2,3]
 *
 * 输出：[1,2,3]
 *
 * 解释：
 *
 *
 *
 * 示例 2：
 *
 * 输入：root = [1,2,3,4,5,null,8,null,null,6,7,9]
 *
 * 输出：[1,2,4,5,6,7,3,8,9]
 *
 * 解释：
 *
 *
 *
 * 示例 3：
 *
 * 输入：root = []
 *
 * 输出：[]
 *
 * 示例 4：
 *
 * 输入：root = [1]
 *
 * 输出：[1]
 *
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 *
 * 进阶：递归算法很简单，你可以通过迭代算法完成吗？
 *
 *
 * 时间复杂度：O(n)
 * 空间复杂度：最坏 O(n)
 */
public class _0144_BinaryTreePreorderTraversal {

    static class Solution {
        // 递归
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            dfs(root, result);
            return result;
        }

        private void dfs(TreeNode node, List<Integer> result) {
            if (node == null) return;

            result.add(node.value);
            dfs(node.left, result);
            dfs(node.right, result);
        }

        // 栈 因为深度优先搜索和栈的原理一样
//        public List<Integer> preorderTraversal(TreeNode root) {
//            List<Integer> result = new ArrayList<>();
//            if (root == null) {
//                return result;
//            }
//
//            Deque<TreeNode> stack = new ArrayDeque<>();
//            stack.push(root);
//
//            while (!stack.isEmpty()) {
//                TreeNode node = stack.pop();
//                result.add(node.value);
//
//                // 因为栈是 后进先出，所以要先压 右，再压 左，这样弹出来时才是 根左右。
//                if (node.right != null) {
//                    stack.push(node.right);
//                }
//                if (node.left != null) {
//                    stack.push(node.left);
//                }
//            }
//            return result;
//        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 示例1: [1,null,2,3] -> [1,2,3]
        test(
                solution.preorderTraversal(
                        buildTree(new Integer[]{1, null, 2, 3})
                ),
                Arrays.asList(1, 2, 3)
        );

        // 示例2: [1,2,3,4,5,null,8,null,null,6,7,9] -> [1,2,4,5,6,7,3,8,9]
        test(
                solution.preorderTraversal(
                        buildTree(new Integer[]{1, 2, 3, 4, 5, null, 8, null, null, 6, 7, 9})
                ),
                Arrays.asList(1, 2, 4, 5, 6, 7, 3, 8, 9)
        );

        // 示例3: [] -> []
        test(
                solution.preorderTraversal(
                        buildTree(new Integer[]{})
                ),
                Arrays.asList()
        );

        // 示例4: [1] -> [1]
        test(
                solution.preorderTraversal(
                        buildTree(new Integer[]{1})
                ),
                Arrays.asList(1)
        );

        // 补充测试: [1,2,3] -> [1,2,3]
        test(
                solution.preorderTraversal(
                        buildTree(new Integer[]{1, 2, 3})
                ),
                Arrays.asList(1, 2, 3)
        );
    }

    private static void test(List<Integer> actual, List<Integer> expected) {
        boolean pass = actual.equals(expected);
        System.out.println(
                (pass ? "PASS" : "FAIL")
                        + " | expected = " + expected
                        + ", actual = " + actual
        );
    }

    /**
     * 按 LeetCode 层序数组构建二叉树
     */
    private static TreeNode buildTree(Integer[] values) {
        if (values == null || values.length == 0 || values[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode current = queue.poll();

            if (i < values.length && values[i] != null) {
                current.left = new TreeNode(values[i]);
                queue.offer(current.left);
            }
            i++;

            if (i < values.length && values[i] != null) {
                current.right = new TreeNode(values[i]);
                queue.offer(current.right);
            }
            i++;
        }

        return root;
    }
}