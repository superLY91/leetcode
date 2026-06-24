# LeetCode

Java / Kotlin LeetCode 练习记录，按题目主类型分类。

## 状态说明

| 标记 | 含义 |
| --- | --- |
| ✅ | 已完成 |
| 🚧 | 已创建文件，但实现未完成或仍需完善 |
| - | 暂无实现 |

## 数组 / 双指针

| 题号 | 题目 | 难度 | Java | Kotlin | 备注 |
| --- | --- | --- | --- | --- | --- |
| 1 | Two Sum | Easy | [✅](src/j/easy/_0001_TwoSum.java) | - | 哈希表 |
| 15 | 3Sum | Medium | [✅](src/j/medium/_0015_3Sum.java) | [✅](src/k/medium/_0015_3Sum.kt) | 排序 + 双指针 |
| 26 | Remove Duplicates from Sorted Array | Easy | [✅](src/j/easy/_0026_RemoveDuplicatesFromSortedArray.java) | [✅](src/k/easy/_0026_RemoveDuplicatesFromSortedArray.kt) | 原地双指针 |
| 75 | Sort Colors | Medium | [✅](src/j/medium/_0075_SortColors.java) | [✅](src/k/medium/_0075_SortColors.kt) | 三指针 / 荷兰国旗 |
| 125 | Valid Palindrome | Easy | [✅](src/j/easy/_0125_ValidPalindrome.java) | [✅](src/k/medium/_0125_ValidPalindrome.kt) | 字符串双指针 |
| 283 | Move Zeroes | Easy | [✅](src/j/easy/_0283_MoveZeroes.java) | - | 原地移动 |

## 链表

| 题号 | 题目 | 难度 | Java | Kotlin | 备注 |
| --- | --- | --- | --- | --- | --- |
| 2 | Add Two Numbers | Medium | [✅](src/j/medium/_0002_AddTwoNumbers.java) | - | 链表模拟 |
| 21 | Merge Two Sorted Lists | Easy | [✅](src/j/easy/_0021_MergeTwoSortedLists.java) | - | 递归 / 迭代 |
| 141 | Linked List Cycle | Easy | [✅](src/j/easy/_0141_LinkedListCycle.java) | [✅](src/k/easy/_0141_LinkedListCycle.kt) | 快慢指针 |
| 206 | Reverse Linked List | Easy | [✅](src/j/easy/_0206_ReverseLinkedList.java) | - | 指针反转 |

## 栈 / 设计

| 题号 | 题目 | 难度 | Java | Kotlin | 备注 |
| --- | --- | --- | --- | --- | --- |
| 20 | Valid Parentheses | Easy | [✅](src/j/easy/_0020_ValidParentheses.java) | - | 栈匹配 |
| 155 | Min Stack | Medium | [✅](src/j/medium/_0155_MinStack.java) | [✅](src/k/medium/_0155_MinStack.kt) | 辅助栈 |

## 二分查找

| 题号 | 题目 | 难度 | Java | Kotlin | 备注 |
| --- | --- | --- | --- | --- | --- |
| 33 | Search in Rotated Sorted Array | Medium | [✅](src/j/medium/_0033_SearchInRotatedSortedArray.java) | [✅](src/k/medium/_0033_SearchInRotatedSortedArray.kt) | 旋转数组二分 |
| 704 | Binary Search | Easy | [✅](src/j/easy/_0704_BinarySearch.java) | - | 标准二分 |

## 回溯

| 题号 | 题目 | 难度 | Java | Kotlin | 备注 |
| --- | --- | --- | --- | --- | --- |
| 39 | Combination Sum | Medium | [✅](src/j/medium/_0039_CombinationSum.java) | [✅](src/k/medium/_0039_CombinationSum.kt) | 组合搜索 |
| 46 | Permutations | Medium | [✅](src/j/medium/_0046_Permutations.java) | [✅](src/k/medium/_0046_Permutations.kt) | 排列搜索 |

## 树 / DFS / BFS

| 题号 | 题目 | 难度 | Java | Kotlin | 备注 |
| --- | --- | --- | --- | --- | --- |
| 98 | Validate Binary Search Tree | Medium | [✅](src/j/medium/_0098_ValidateBinarySearchTree.java) | [✅](src/k/medium/_0098_ValidateBinarySearchTree.kt) | BST 递归校验 |
| 102 | Binary Tree Level Order Traversal | Medium | [✅](src/j/medium/_0102_BinaryTreeLevelOrderTraversal.java) | - | BFS 层序遍历 |
| 124 | Binary Tree Maximum Path Sum | Hard | [✅](src/j/hard/_0124_BinaryTreeMaximumPathSum.java) | [✅](src/k/medium/_0124_BinaryTreeMaximumPathSum.kt) | 树形 DP |
| 144 | Binary Tree Preorder Traversal | Easy | [✅](src/j/easy/_0144_BinaryTreePreorderTraversal.java) | [✅](src/k/medium/_0144_BinaryTreePreorderTraversal.kt) | 前序遍历 |

## 动态规划 / 贪心

| 题号 | 题目 | 难度 | Java | Kotlin | 备注 |
| --- | --- | --- | --- | --- | --- |
| 53 | Maximum Subarray | Medium | [✅](src/j/medium/_0053_MaximumSubarray.java) | - | Kadane 算法 |
| 70 | Climbing Stairs | Easy | [✅](src/j/easy/_0070_ClimbingStairs.java) | - | 斐波那契 DP |
| 121 | Best Time to Buy and Sell Stock | Easy | [✅](src/j/easy/_0121_BestTimetoBuyandSellStock.java) | - | 一次遍历 |
| 198 | House Robber | Medium | [✅](src/j/medium/_0198_HouseRobber.java) | - | 线性 DP |

## 图 / 网格

| 题号 | 题目 | 难度 | Java | Kotlin | 题解 | 备注 |
| --- | --- | --- | --- | --- | --- | --- |
| 200 | Number of Islands | Medium | [✅](src/j/medium/_0200_NumberofIslands.java) | [🚧](src/k/medium/_0200_NumberofIslands.kt) | [✅](src/k/solutions/_0200_NumberofIslands.md) | DFS 网格连通块 |

## 区间

| 题号 | 题目 | 难度 | Java | Kotlin | 备注 |
| --- | --- | --- | --- | --- | --- |
| 56 | Merge Intervals | Medium | [✅](src/j/medium/_0056_Merge_Intervals.java) | [✅](src/k/_0056_Merge_Intervals.kt) | 排序 + 合并 |

## 位运算

| 题号 | 题目 | 难度 | Java | Kotlin | 备注 |
| --- | --- | --- | --- | --- | --- |
| 201 | Bitwise AND of Numbers Range | Medium | [✅](src/j/medium/_0201_BitwiseANDofNumbersRange.java) | - | 公共前缀 |

## 当前进度

| 指标 | 数量 |
| --- | ---: |
| 已归档题目 | 28 |
| Java 已完成 | 28 |
| Kotlin 已完成 | 13 |
| Kotlin 待完善 | 1 |
| Markdown 题解 | 1 |
