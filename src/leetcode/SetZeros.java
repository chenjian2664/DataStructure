package leetcode;

/**
 * Created by chenjian
 * 2018/10/20 17:33
 */
public class SetZeros {
    public static void setZeroes(int[][] matrix) {
        if (null == matrix || matrix.length <= 0 || matrix[0].length <= 0)
            return;
        int row = matrix.length;
        int col = matrix[0].length;
        boolean[][] flags = new boolean[row][col];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (matrix[i][j] == 0) {
                    for (int k = 0; k < row; ++k)
                        flags[k][j] = true;
                    for (int l = 0; l < col; ++l)
                        flags[i][l] = true;
                }
            }
        }
        for (int i = 0; i < row; ++i)
            for (int j = 0; j < col; ++j)
                if (flags[i][j])
                    matrix[i][j] = 0;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };
        setZeroes(matrix);
        for (int[] m : matrix) {
            for (int i : m)
                System.out.print(i + " ");
            System.out.println();
        }
    }
}
