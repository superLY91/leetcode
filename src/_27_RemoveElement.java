/**
 * Array
 */
public class _27_RemoveElement {

    public static void main(String[] args) {
        int [] nums = new int[]{0,1,2,2,3,0,4,2};
        int val = 2;
        int len = removeElement(nums, val);

        System.out.println(len);

        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    public static int removeElement(int[] nums, int val) {
        int len = nums.length;
        int i = 0;
        for (int j = 0; j < len; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

}
