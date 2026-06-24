package j.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 155. Min Stack（最小栈）
 *
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * 实现 MinStack 类:
 *
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 *
 *
 * 示例 1:
 *
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 *
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 *
 * 提示：
 *
 * -231 <= val <= 231 - 1
 * pop、top 和 getMin 操作总是在 非空栈 上调用
 * push, pop, top, and getMin最多被调用 3 * 104 次
 *
 * 时间复杂度：
 * 空间复杂度：
 */
public class _0155_MinStack {

    static class MinStack {
        private final Deque<Integer> stack;
        private final Deque<Integer> minStack;

        public MinStack() {
            stack = new ArrayDeque<>();
            minStack = new ArrayDeque<>();
        }

        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }

        public void pop() {
            int removed = stack.pop();
            if (removed == minStack.peek()) {
                minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    public static void main(String[] args) {
        testExample1();
        testSingleElement();
        testDuplicateMin();
        testIncreasingOrder();
        testDecreasingOrder();
    }

    private static void testExample1() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);

        test(minStack.getMin(), -3, "example getMin");
        minStack.pop();
        test(minStack.top(), 0, "example top");
        test(minStack.getMin(), -2, "example getMin after pop");
    }

    private static void testSingleElement() {
        MinStack minStack = new MinStack();
        minStack.push(5);

        test(minStack.top(), 5, "single top");
        test(minStack.getMin(), 5, "single getMin");
    }

    private static void testDuplicateMin() {
        MinStack minStack = new MinStack();
        minStack.push(2);
        minStack.push(1);
        minStack.push(1);
        minStack.push(3);

        test(minStack.getMin(), 1, "duplicate min before pop");

        minStack.pop(); // pop 3
        test(minStack.getMin(), 1, "duplicate min after pop 3");

        minStack.pop(); // pop 1
        test(minStack.getMin(), 1, "duplicate min after pop one 1");

        minStack.pop(); // pop 1
        test(minStack.getMin(), 2, "duplicate min after pop second 1");
    }

    private static void testIncreasingOrder() {
        MinStack minStack = new MinStack();
        minStack.push(1);
        minStack.push(2);
        minStack.push(3);

        test(minStack.getMin(), 1, "increasing getMin");
        minStack.pop();
        test(minStack.top(), 2, "increasing top after pop");
        test(minStack.getMin(), 1, "increasing getMin after pop");
    }

    private static void testDecreasingOrder() {
        MinStack minStack = new MinStack();
        minStack.push(3);
        minStack.push(2);
        minStack.push(1);

        test(minStack.getMin(), 1, "decreasing getMin");
        minStack.pop();
        test(minStack.getMin(), 2, "decreasing getMin after pop");
        test(minStack.top(), 2, "decreasing top after pop");
    }

    private static void test(int actual, int expected, String name) {
        boolean pass = actual == expected;
        System.out.println(
                (pass ? "PASS" : "FAIL")
                        + " | " + name
                        + " | expected = " + expected
                        + ", actual = " + actual
        );
    }
}