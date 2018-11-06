package sort;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by chenjian
 * 2018/9/24 9:47
 */
public class Sort {
    static int nums1[] = {8, 12, 4, 123, 5, 32, 56, 9, 92, 66};
    static int nums2[] = {1, 7, 2, 8, 3, 4, 5, 29, 30, 49};
    private static void quickSort1(int[] nums, int start, int end) {
        //handle null
        if (start >= end)
            return;
        int tail = end;
        int base = nums[start];
        while (start < end) {
            if (nums[end] < nums[start]) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
            }
            if (base == nums[start]) {
                --end;
            }
            if (base == nums[end]) {
                ++start;
            }
        }
        quickSort1(nums, 0, start - 1);
        quickSort1(nums, end + 1, tail);
    }
    private static void quickSort2(int[] nums, int start, int end) {
        //handle null
        if (start >= end) {
            return;
        }
        int tail = end;
        int base = nums[start];
        while (start < end) {
            while (start < end && nums[start] <= base)
                ++start;
            nums[start] = nums[end];
            while (start < end && base <= nums[end])
                --end;
            nums[end] = nums[start];

        }
        nums[start] = base;

        quickSort2(nums, 0, start - 1);
        quickSort2(nums, end + 1, tail);
    }

    public static void main(String[] args) {
//        quickSort1(nums1, 0, nums1.length - 1);
//        for (int num : nums1) {
//            System.out.print(num + " ");
//        }
//        quickSort2(nums2, 0, nums2.length - 1);
//        for (int num : nums2) {
//            System.out.print(num + " ");
//        }
//        Thread[] ths = new Thread[3];
//        for (int i = 0; i < 3; ++i) {
//            ths[i] = new Thread(() -> {
//                for (int j = 0; j < 5; ++j)
//                    System.out.print(j);
//                System.out.print("*");
//            });
//        }
//        for (Thread th : ths)
//            th.start();
        Sort sort = null;
        sort.hello();
    }
    public static void hello() {
        System.out.println("hello ");
    }
}
