package j.medium;

import java.util.*;

/**
 * 3. Longest Substring Without Repeating Characters（无重复字符的最长子串）
 * 
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
 * 
 * 
 * 示例 1:
 * 
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案。
 * 示例 2:
 * 
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 
 * 
 * 提示：
 * 
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 * 
 * 自己思路：
 * 滑动窗口
 * 发现相同的就滑动到不相同的为止，每次移动都记录长度，和最大长度比较，返回最大长度
 * 用hashset，因为要确保窗口内不重复
 * 
 * 思路
 * 
 * 滑动窗口 + `HashSet`
 * 
 * - 右指针扩张窗口
 * - 出现重复字符时，不断移动左指针缩小窗口
 * - 更新最大长度
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
public class _0003_LongestSubstringWithoutRepeatingCharacters {

    static class Solution {
        public int lengthOfLongestSubstring(String s) {
            HashSet<Character> window = new HashSet<>();
            int left = 0;
            int maxLen = 0;

            for (int right = 0; right < s.length(); right++) {
                char c = s.charAt(right);
                
                while (window.contains(c)) {
                    window.remove(s.charAt(left));
                    left++;
                }
                window.add(c);
                maxLen = Math.max(maxLen, right - left + 1);
                // maxLen = Math.max(maxLen, window.size());
            }

            return maxLen;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        test(solution.lengthOfLongestSubstring("abcabcbb"), 3);
        test(solution.lengthOfLongestSubstring("bbbbb"), 1);
        test(solution.lengthOfLongestSubstring("pwwkew"), 3);
    }

    private static void test(int actual, int expected) {
        boolean pass = actual == expected;
        System.out.println(
                (pass ? "PASS" : "FAIL")
                        + " | expected = " + expected
                        + ", actual = " + actual);
    }

}