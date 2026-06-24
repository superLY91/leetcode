package j.common;

import java.util.*;

public class TreeTestUtils {

    /**
     * Integer[] -> TreeNode
     * 按层序构建二叉树
     *
     * 例如：
     * [3,9,20,null,null,15,7]
     */
    public static TreeNode buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < arr.length) {
            TreeNode current = queue.poll();

            if (i < arr.length && arr[i] != null) {
                current.left = new TreeNode(arr[i]);
                queue.offer(current.left);
            }
            i++;

            if (i < arr.length && arr[i] != null) {
                current.right = new TreeNode(arr[i]);
                queue.offer(current.right);
            }
            i++;
        }

        return root;
    }

    /**
     * 二叉树 -> 层序数组字符串（便于调试）
     * 例如：
     * [3, 9, 20, null, null, 15, 7]
     */
    public static String treeToLevelOrderString(TreeNode root) {
        if (root == null) return "[]";

        List<String> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node == null) {
                result.add("null");
            } else {
                result.add(String.valueOf(node.value));
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }

        // 去掉尾部多余的 null
        int end = result.size() - 1;
        while (end >= 0 && "null".equals(result.get(end))) {
            end--;
        }

        return result.subList(0, end + 1).toString();
    }

    /**
     * 打印树（简单调试用）
     */
    public static void printTree(TreeNode root) {
        System.out.println(treeToLevelOrderString(root));
    }

    /**
     * 测试返回 int 的树题
     */
    public static void testInt(int actual, int expected, Integer[] input) {
        boolean pass = actual == expected;
        System.out.println(
            (pass ? "PASS" : "FAIL")
            + " | input = " + Arrays.toString(input)
            + " | expected = " + expected
            + " | actual = " + actual
        );
    }

    /**
     * 测试返回 boolean 的树题
     */
    public static void testBoolean(boolean actual, boolean expected, Integer[] input) {
        boolean pass = actual == expected;
        System.out.println(
            (pass ? "PASS" : "FAIL")
            + " | input = " + Arrays.toString(input)
            + " | expected = " + expected
            + " | actual = " + actual
        );
    }

    /**
     * 测试返回 List<Integer> 的树题
     */
    public static void testList(List<Integer> actual, List<Integer> expected, Integer[] input) {
        boolean pass = actual.equals(expected);
        System.out.println(
            (pass ? "PASS" : "FAIL")
            + " | input = " + Arrays.toString(input)
            + " | expected = " + expected
            + " | actual = " + actual
        );
    }

    /**
     * 测试返回 List<List<Integer>> 的树题
     */
    public static void testListList(List<List<Integer>> actual, List<List<Integer>> expected, Integer[] input) {
        boolean pass = actual.equals(expected);
        System.out.println(
            (pass ? "PASS" : "FAIL")
            + " | input = " + Arrays.toString(input)
            + " | expected = " + expected
            + " | actual = " + actual
        );
    }
}