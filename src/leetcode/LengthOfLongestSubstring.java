package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenjian
 * 2018/10/28 20:36
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("bbbccabbb"));
    }
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty())
            return 0;
        int res = 0, temp = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            if (map.containsKey(s.charAt(i)) && map.get(s.charAt(i)) + temp >= i) {
                res = Math.max(res, temp);
                temp = i - map.get(s.charAt(i));
            } else
                ++temp;
            map.put(s.charAt(i), i);
        }
        return Math.max(res,temp);
    }

}
