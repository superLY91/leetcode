package j.easy;

import java.util.*;

/**
 * 20. ValidParentheses 有效括号
 * 
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 
 * 有效字符串需满足：
 * 
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号
 * 
 * 示例 1：
 * 
 * 输入：s = "()"
 * 
 * 输出：true
 * 
 * 示例 2：
 * 
 * 输入：s = "()[]{}"
 * 
 * 输出：true
 * 
 * 示例 3：
 * 
 * 输入：s = "(]"
 * 
 * 输出：false
 * 
 * 示例 4：
 * 
 * 输入：s = "([])"
 * 
 * 输出：true
 * 
 * 示例 5：
 * 
 * 输入：s = "([)]"
 * 
 * 输出：false
 * 
 * 自己思路：
 * 遍历字符串
 * 使用栈，遍历到左括号先入栈
 * 遍历到右括号，top出栈，匹配
 * 匹配到了就继续
 * 匹配失败就返回失败
 * 最后都没退出就返回成功
 * 
 * 思路
 * - 左括号入栈
 * - 右括号时检查栈顶是否匹配
 * - 最后栈必须为空
 * 
 * 提示：
 * 
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */

public class _0020_ValidParentheses {

    static class Solution {
        // 自己
        public boolean isValid(String s) {
            Stack<Character> stack = new Stack<>();

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(' || c == '[' || c == '{') {
                    stack.add(c);
                    continue;
                }

                if (stack.isEmpty())
                    return false;
                char top = stack.pop();
                if (c == ')' && top != '(' ||
                        c == ']' && top != '[' ||
                        c == '}' && top != '{')
                    return false;
            }

            return stack.isEmpty();
        }

        // 标准
        public boolean isValid_normal(String s) {
            Stack<Character> stack = new Stack<>();

            for (char c : s.toCharArray()) {
                if (c == '(' || c == '[' || c == '{') {
                    stack.add(c);
                } else {
                    if (stack.isEmpty())
                        return false;
                    char top = stack.pop();
                    if (c == ')' && top != '(' ||
                            c == ']' && top != '[' ||
                            c == '}' && top != '{')
                        return false;
                }
            }

            return stack.isEmpty();
        }

    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        test(solution.isValid("()"), true);
        test(solution.isValid("()[]{}"), true);
        test(solution.isValid("(]"), false);
        test(solution.isValid("([])"), true);
        test(solution.isValid("([)]"), false);
    }

    private static void test(boolean actual, boolean expected) {
        boolean pass = actual == expected;
        System.out.println(
                (pass ? "PASS" : "FAIL")
                        + " | expected = " + expected
                        + ", actual = " + actual);
    }
}