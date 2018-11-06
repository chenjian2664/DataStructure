package leetcode;

/**
 * Created by chenjian
 * 2018/11/4 16:49
 */
public class SearchInsert {
    public static int searchInsert(int[] nums, int target) {
        if (null == nums || nums.length == 0)
            return 0;
        if (target < nums[0])
            return 0;
        if (target > nums[nums.length - 1])
            return nums.length;
        return searchInsert(nums, target, 0, nums.length - 1);
    }

    private static int searchInsert(int[] nums, int target, int left, int right) {
        int mid = (left + right) >> 1;
        if (nums[mid] == target)
            return mid;
        if (left >= right) {
            if (target < nums[mid])
                return mid;
            if (target > nums[mid])
                return mid + 1;
        }
        if (target < nums[mid])
            return searchInsert(nums, target, left, mid - 1);
        else
            return searchInsert(nums, target, mid + 1, right);
    }

    public static void main(String[] args) {
        int[] nums = {1, 3};
//        System.out.println(searchInsert(nums, 5)); // 2
//        System.out.println(searchInsert(nums, 2)); // 1
//        System.out.println(searchInsert(nums, 7)); // 4
        System.out.println(searchInsert(nums, 0)); // 0
    }

}
