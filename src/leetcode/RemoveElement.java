package leetcode;

/**
 * Created by chenjian
 * 2018/10/6 13:00
 */
public class RemoveElement {
    static int nums[] = {0,1,2,2,3,0,4,2};
    private static int removeElement(int[] nums, int val) {
        //handle null
        int count = 0;
        for (int num : nums) {
            if (val != num)
                ++count;
        }
        for (int i = 0, j = i; i < count; ++i, ++j) {
            while (j < nums.length && nums[j] == val)
                ++j;
            nums[i] = nums[j];
        }
        return count;
    }

    public static void main(String[] args) {
        int res = removeElement(nums, 3);
        for (int i = 0; i < res; ++i) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }
}
