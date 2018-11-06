package leetcode;

/**
 * Created by chenjian
 * 2018/10/6 12:26
 */
public class FindMaxConsecutiveOnes {
    private static int findMaxConsecutiveOnes(int[] nums) {
        if (null == nums || nums.length == 0)
            return 0;
        int result = 0;
        for (int i = 0; i < nums.length; ++i) {
            int temp = 0;
            while (i < nums.length && nums[i] == 1) {
                ++temp;
                ++i;
            }
            if (result < temp)
                result = temp;
        }
        return result;
    }
    public static void main(String[] args) {
        int nums[] = {1,1,0,0,0,1,1,1};
        System.out.println(findMaxConsecutiveOnes(nums));
    }
}
