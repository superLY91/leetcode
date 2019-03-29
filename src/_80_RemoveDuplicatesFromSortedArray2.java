
/**
 * Array
 */
public class _80_RemoveDuplicatesFromSortedArray2 {

    public static void main(String[] args) {
        int [] nums = new int[]{0,0,1,1,1,1,2,3,3};
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
        if (len == 0) {
            return 0;
        }
        int i = 0;
        int size = 0;
        for (int j = 0; j < len; j++) {
            if (nums[i] != nums[j]) {
                size = 0;
                i++;
                nums[i] = nums[j];
            } else if (j != 0 && size < 1){
                size++;
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
