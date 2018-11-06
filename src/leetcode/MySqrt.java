package leetcode;

/**
 * Created by chenjian
 * 2018/11/6 22:37
 */
public class MySqrt {
    public static int mySqrt(int x) {
        long tmp;
        for (int i = 0; (tmp = i * i) <= x; ++i) {
            if ( tmp + 2 * i >= x )
                return i;
        }
        return 0;
    }

    public static void main(String[] args) {
        int i = 2147395600;
//        int k = 46341 * 46341;
        System.out.println(mySqrt(8));
        System.out.println(mySqrt(2147395600));
//        System.out.println(Math.sqrt(2147395600));
    }
}
