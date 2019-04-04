import java.util.Arrays;
import java.util.HashMap;

/**
 * 169. Majority Element (Easy)
 *
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 *
 * You may assume that the array is non-empty and the majority element always exist in the array.
 *
 * Example 1:
 *
 * Input: [3,2,3]
 * Output: 3
 *
 * Example 2:
 *
 * Input: [2,2,1,1,1,2,2]
 * Output: 2
 *
 */
public class _169_MajorityElement {

    public static void main(String[] args) {
        int[] nums = new int[]{2,1,2,1,2,1,2};
        int majority = majorityElement3(nums);
        System.out.println(majority);
    }

    /**
     * My Solution 1
     * @param nums
     * @return
     */
    public static int majorityElement(int [] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int len = nums.length;
        int majority = len / 2;
        int result = 0;

        for (int i = 0; i < len; i++){
            hashMap.put(nums[i], hashMap.get(nums[i]) == null ? 1 : hashMap.get(nums[i]) + 1);
            if (hashMap.get(nums[i]) > majority) {
                majority = hashMap.get(nums[i]);
                result = nums[i];
            }
        }
        return result;
    }

    /**
     * My Solution 2
     * @param nums
     * @return
     */
    public static int majorityElement1(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int len = nums.length;
        int times = len / 2;

        for (int i = 0; i < len; i++){
            hashMap.put(nums[i], hashMap.get(nums[i]) == null ? 1 : hashMap.get(nums[i]) + 1);
            if (hashMap.get(nums[i]) > times) {
                return nums[i];
            }
        }
        return -1;
    }

    public static int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];

    }

    /**
     * Approach 6: Boyer-Moore Voting Algorithm
     * @param nums
     * @return
     */
    public static  int majorityElement3(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}
