package leetcode;

/**
 * Created by chenjian
 * 2018/10/15 22:57
 */
public class ReverseWords {
    public static String reverseWords(String s) {
        if (s == null || s.length() <= 1)
            return s;
        s = s.trim();
        StringBuilder res = new StringBuilder();
        s = reverseWords(s, 0, s.length() - 1);
        for (int i = 0; i < s.length();) {
            while (i < s.length() && s.charAt(i) == ' ')
                ++i;
            if (i == s.length())
                break;
            int j = i + 1;
            while (j < s.length() && s.charAt(j) != ' ')
                ++j;
            res.append(reverseWords(s, i, j - 1)).append(" ");
            i = j;
        }
        return res.toString().trim();
    }

    private static String reverseWords(String s, int left, int right) {
        char[] chars = s.toCharArray();
        int low = left, count = right - left + 1;
        for (;left < right; ++left, --right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
        }
        return new String(chars, low, count);
    }

    public static void main(String[] args) {
        String s = "    ";
        System.out.println("     ".trim().length());
        System.out.println(s.length());
        System.out.println(reverseWords(s).length());
    }
}
