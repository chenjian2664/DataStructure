package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by chenjian
 * 2018/9/23 9:16
 */
public class SpiralOrder {
    static List<Integer> result = new ArrayList<>();

    static int[][] input1 = {{1,2,3,4},{5, 6, 7, 8}, {9, 10, 11, 12}};
    private static List<Integer> sprialOrder(int[][] matrix) {
        if (null == matrix)
            return null;
        int m, n;
        if ((m = matrix.length) == 0 || (n = matrix[0].length) == 0)
            return Collections.emptyList();
        int[][] dx = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        boolean[][] f = new boolean[m][n];
        int cur = 0;
        int i = 0, j = 0;
        int count = 0;
        while (count++ < m * n) {
            if (i + dx[cur][0] >= m || i + dx[cur][0] < 0
                    || j + dx[cur][1] >= n || j + dx[cur][1] < 0
                    || f[i + dx[cur][0]][j + dx[cur][1]]) {
                cur = (cur + 1) % 4;
            }
            result.add(matrix[i][j]);

            f[i][j] = true;

            i += dx[cur][0];
            j += dx[cur][1];
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> list = sprialOrder(input1);
        list.forEach(System.out::println);
    }
}
