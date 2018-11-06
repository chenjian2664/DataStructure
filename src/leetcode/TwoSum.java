package leetcode;

/**
 * Created by chenjian
 * 2018/10/6 12:39
 */
public class TwoSum {
    private static int[] twoSum(int[] numbers, int target) {

//        for (int i = 0; i < numbers.length - 1; ++i) {
//            for (int j = i + 1; j < numbers.length; ++j) {
//                if (numbers[i] + numbers[j] == target) {
//                    return new int[] {i + 1, j + 1};
//                } else if (numbers[i] + numbers[j] > target)
//                    break;
//            }
//        }
//        return null;
        for (int left = 0, right = numbers.length - 1; left < right; ) {
            int temp = numbers[left] + numbers[right];
            if (temp == target) {
                return new int[] {left + 1, right + 1};
            } else if (temp < target) {
                ++left;
            } else {
                --right;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        int[] result = twoSum(numbers, 22);
        System.out.println(result[0] + " : " + result[1]);
    }
}

