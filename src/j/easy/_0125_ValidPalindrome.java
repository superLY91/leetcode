package j.easy;

/**
 *
 * 125. Valid Palindrome（验证回文串）
 *
 * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。
 * 则可以认为该短语是一个 回文串 。
 *
 * 字母和数字都属于字母数字字符。
 *
 * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 *
 * 输入: s = "A man, a plan, a canal: Panama"
 * 输出：true
 * 解释："amanaplanacanalpanama" 是回文串。
 * 示例 2：
 *
 * 输入：s = "race a car"
 * 输出：false
 * 解释："raceacar" 不是回文串。
 * 示例 3：
 *
 * 输入：s = " "
 * 输出：true
 * 解释：在移除非字母数字字符之后，s 是一个空字符串 "" 。
 * 由于空字符串正着反着读都一样，所以是回文串。
 *
 * 提示：
 *
 * 1 <= s.length <= 2 * 105
 * s 仅由可打印的 ASCII 字符组成
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class _0125_ValidPalindrome {

    static class Solution {
        public boolean isPalindrome(String s) {
            int left = 0;
            int right = s.length() - 1;

            while (left < right) {
                while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                    left++;
                }
                while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                    right--;
                }
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                left++;
                right--;

            }
            return true;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        test(solution.isPalindrome("A man, a plan, a canal: Panama"), true);
        test(solution.isPalindrome("race a car"), false);
        test(solution.isPalindrome(" "), true);
        test(solution.isPalindrome(".,"),
                true);
        test(solution.isPalindrome("a"), true);
        test(solution.isPalindrome("aa"), true);
        test(solution.isPalindrome("ab"), false);
        test(solution.isPalindrome("0P"), false);
        test(solution.isPalindrome("Madam"), true);
        test(solution.isPalindrome("12321"), true);
        test(solution.isPalindrome("1231"), false);
        test(solution.isPalindrome("A1b2B1a"), true);
    }

    private static void test(boolean actual, boolean expected) {
        boolean pass = actual == expected;
        System.out.println(
                (pass ? "PASS" : "FAIL")
                        + " | expected = " + expected
                        + ", actual = " + actual
        );
    }
}