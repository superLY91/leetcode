# 42. Trapping Rain Water

## 题目理解

给定一个非负整数数组 `height`，数组中的每个数字表示一根宽度为 `1` 的柱子高度。

下雨后，柱子之间可能会形成凹槽来接住雨水。目标是计算最多能接多少单位的水。

例如：

```text
height = [0,1,0,2,1,0,1,3,2,1,2,1]
```

输出：

```text
6
```

## 核心观察

对任意位置 `i` 来说，它能接多少水，不取决于当前位置两边所有柱子的细节，只取决于两件事：

- 左边最高的柱子高度 `leftMax`
- 右边最高的柱子高度 `rightMax`

当前位置能接的水量是：

```text
min(leftMax, rightMax) - height[i]
```

如果这个值小于 `0`，说明当前位置本身太高，不能接水，实际水量就是 `0`。

为什么取 `min(leftMax, rightMax)`：

水能装多高，取决于左右两边较矮的那一边。较高的一边不会成为限制，较矮的一边才是水会溢出去的边界。

## 方法一：动态规划预处理

可以先预处理两个数组：

- `leftMax[i]`：位置 `i` 左侧包含自己在内的最高柱子
- `rightMax[i]`：位置 `i` 右侧包含自己在内的最高柱子

然后遍历每个位置，累加：

```text
min(leftMax[i], rightMax[i]) - height[i]
```

这种方法直观，但需要额外 `O(n)` 空间。

## 方法二：双指针

双指针可以把空间复杂度优化到 `O(1)`。

定义：

- `left` 指向数组左侧
- `right` 指向数组右侧
- `leftMax` 记录 `left` 左边走过的最高柱子
- `rightMax` 记录 `right` 右边走过的最高柱子

每次比较 `leftMax` 和 `rightMax`：

- 如果 `leftMax < rightMax`，说明左侧当前位置的接水高度已经由 `leftMax` 决定
- 否则，说明右侧当前位置的接水高度已经由 `rightMax` 决定

原因是当前位置能接的水由较矮边决定。当 `leftMax < rightMax` 时，右边已经存在一个比 `leftMax` 更高的边界，所以左边当前位置能接多少水可以确定。

## 示例分析

输入：

```text
[4,2,0,3,2,5]
```

从左往右看：

- 高度 `2` 的位置，左边最高是 `4`，右边最高是 `5`，能接 `min(4, 5) - 2 = 2`
- 高度 `0` 的位置，能接 `min(4, 5) - 0 = 4`
- 高度 `3` 的位置，能接 `min(4, 5) - 3 = 1`
- 高度 `2` 的位置，能接 `min(4, 5) - 2 = 2`

总水量：

```text
2 + 4 + 1 + 2 = 9
```

## Kotlin 参考实现

```kotlin
class _0042_M_TrappingRainWater {

    class Solution {
        fun trap(height: IntArray): Int {
            var left = 0
            var right = height.size - 1
            var leftMax = 0
            var rightMax = 0
            var result = 0

            while (left < right) {
                leftMax = maxOf(leftMax, height[left])
                rightMax = maxOf(rightMax, height[right])

                if (leftMax < rightMax) {
                    result += leftMax - height[left]
                    left++
                } else {
                    result += rightMax - height[right]
                    right--
                }
            }

            return result
        }
    }
}
```

## 为什么双指针是正确的

任意位置 `i` 的接水量由 `min(leftMax, rightMax)` 决定。

当 `leftMax < rightMax` 时，左侧当前位置的右边界至少有 `rightMax`，并且 `rightMax` 比 `leftMax` 高，所以较矮边一定是 `leftMax`。此时左侧位置能接多少水已经确定，可以放心计算并移动 `left`。

当 `leftMax >= rightMax` 时，同理，右侧当前位置的较矮边一定是 `rightMax`，可以计算右侧位置并移动 `right`。

每一步都只在水量已经确定的一侧计算，所以不会漏算，也不会重复计算。

## 复杂度分析

设数组长度为 `n`。

时间复杂度：`O(n)`

每个位置最多被左右指针访问一次。

空间复杂度：`O(1)`

只使用了几个变量，没有额外数组。

## 小结

这道题的关键不是模拟雨水下落，而是判断每个位置能被左右边界限制到多高。

核心公式是：

```text
water[i] = min(leftMax, rightMax) - height[i]
```

动态规划预处理更容易理解，双指针则是在同样逻辑上把空间优化到 `O(1)`。
