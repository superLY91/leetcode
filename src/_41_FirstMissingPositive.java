/**
 * 41. First Missing Positive (Hard)
 *
 * Given an unsorted integer array, find the smallest missing positive integer.
 *
 * Example 1:
 *
 * Input: [1,2,0]
 * Output: 3
 * Example 2:
 *
 * Input: [3,4,-1,1]
 * Output: 2
 * Example 3:
 *
 * Input: [7,8,9,11,12]
 * Output: 1
 * Note:
 *
 * Your algorithm should run in O(n) time and uses constant extra space.
 *
 * solution:
 *
 * The idea is that given a array of size n, we try to rearrange the array to be in the format of [1,....,n], the first element missing in this sequence would be the least positive integer missing.
 * long explanation:
 * Now the solution is based on the idea that we traverse twice the array, first time we place the element(*that is greater between 1 and n, otherwise its lcoation is out of bound) to its corrresponding location, specifically, we swap this value with the element at its corresponding location (e.g. [0,-1,1], for element 0 and -1, we don't do anything, for third element 1, we swap with its corresponding location which is 1st element of the array with index 0 (array counts from 0), so we swap each nums[i] with nums[nums[i] - 1]) iff this element is between the bounds of the array(that is, nums[i] > 0 && nums[i] < size). if we did one swap at index i, our ith element is placed at its designated location but the element swapped with i is not "rearragnged" yet, so i use
 * i-- to decrement to rearragnge at this idx (one can always do a while loop to do this, see codes on the bottom).
 * after this one traversal, each element in the array should be at its designated location , and the first index that's not at its designated location would be the minimum positive integer. we just have to do another loop starts from 1 to n, the first index dismatches is then our target integer.
 * if we exit the loop, the smallest integer is then not between 1 to n, it is n + 1(this is the case where the array of size n contains every element from 1 to n)
 */

public class _41_FirstMissingPositive {

    public static void main(String[] args) {
//        int[] nums = new int[]{2, 3, -7, 6, 8, 1, -10, 15};
        int[] nums = new int[]{1, 2, 3};
        int first = firstMissingPositive(nums);
        System.out.println(first);
    }

    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // when you decides to do a while loop instead of decrement,replace the first for loop like this
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0 && nums[i] < n && (nums[nums[i] - 1] != nums[i])) {
                swap(nums, nums[i] - 1, i);
                /** now no need to decrement i, since while loop will keep swapping
                 * until the element is out of bound **/
                i--;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (nums[i - 1] != i) {
                return i;
            }
        }
        return n + 1;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
