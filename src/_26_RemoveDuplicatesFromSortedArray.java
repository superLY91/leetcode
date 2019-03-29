
/**
 * Array
 */
public class _26_RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        int [] nums = new int[]{0,0,1,1,1,2,2,3,3,4};
        // nums is passed in by reference. (i.e., without making a copy)
        int len = removeDuplicates(nums);

        // any modification to nums in your function would be known by the caller.
        // using the length returned by your function, it prints the first len elements.
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + " ");
        }

    }

    public static int removeDuplicates(int[] nums) {
        int len = nums.length;
        int i = 0;
        for (int j = 0; j < len; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
