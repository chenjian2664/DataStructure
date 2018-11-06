package leetcode;

import stack.ArrayStack;
import stack.Stack;

/**
 * Created by chenjian
 * 2018/9/23 15:49
 */
public class AddBinary {
    static String a = "1010";
    static String b = "1011";
    private static String addBinary(String a, String b) {
        Stack<Character> stack = new ArrayStack<>();
        boolean flag = false;
        int i, j;
        for (i = a.length() - 1, j = b.length() - 1; i >= 0 && j >= 0; --i, --j) {
            if (a.charAt(i) == b.charAt(j)) {
                if (flag) {
                    stack.push('1');
                    if (a.charAt(i) == '0')
                        flag = false;
                } else {
                    stack.push('0');
                    if (a.charAt(i) == '1')
                        flag = true;
                }
            } else { // not equal
                if (flag) {
                    stack.push('0');
                } else {
                    stack.push('1');
                }
            }
        }
        if (i >= 0)
            dealLeftString(i, a, stack, flag);
        if (j >= 0)
            dealLeftString(j, b, stack, flag);
        if (flag && i == j)
            stack.push('1');
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }
//
        a.indexOf(b);
        return builder.toString();
    }

    private static void dealLeftString(int i, String a, Stack<Character> stack, boolean flag) {
        while (i >= 0) {
            if (flag) {
                if (a.charAt(i) == '1') {
                    stack.push('0');
                } else {
                    stack.push('1');
                    flag = false;
                }
            } else {
                stack.push(a.charAt(i));
            }
            --i;
        }
        if (flag)
            stack.push('1');
    }

    public static void main(String[] args) {
        String s = addBinary(a, b);
        System.out.println(s);
    }
}
