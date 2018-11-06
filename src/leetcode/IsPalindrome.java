package leetcode;

/**
 * Created by chenjian
 * 2018/11/4 16:41
 */
public class IsPalindrome {
    public static boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        int len = s.length();
        for (int i = 0; i < (len >> 1); ++i) {
            if (s.charAt(i) != s.charAt(len - i - 1))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(123));
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(-121));
    }
}
