package j.common;

/**
 * 链表测试工具类
 */
public class LinkedListTestUtils {

    /**
     * 普通数组 -> 普通链表
     */
    public static ListNode buildList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        for (int num : arr) {
            curr.next = new ListNode(num);
            curr = curr.next;
        }

        return dummy.next;
    }

    /**
     * 数组 + pos -> 环形链表
     *
     * pos = -1 表示无环
     * pos >= 0 表示尾节点连接到索引 pos 的节点
     */
   public static ListNode buildCycleList(int[] arr, int pos) {
        if (arr == null || arr.length == 0)
            return null;

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        ListNode cycleEntry = null;

        for (int i = 0; i < arr.length; i++) {
            curr.next = new ListNode(arr[i]);
            curr = curr.next;

            if (i == pos) {
                cycleEntry = curr;
            }
        }

        if (pos != -1) {
            curr.next = cycleEntry;
        }

        return dummy.next;
    }

    /**
     * 链表 -> 数组
     */
    public static int[] toArray(ListNode head) {
        int len = 0;
        ListNode curr = head;

        while (curr != null) {
            len++;
            curr = curr.next;
        }

        int[] arr = new int[len];
        curr = head;
        int i = 0;

        while (curr != null) {
            arr[i++] = curr.value;
            curr = curr.next;
        }

        return arr;
    }
}