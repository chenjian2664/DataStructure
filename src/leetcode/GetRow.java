package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjian
 * 2018/10/14 23:31
 */
public class GetRow {
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        return getRow(list, rowIndex);
    }
    private static List<Integer> getRow(List<Integer> list, int rowIndex) {
        if (rowIndex == 0) {
            return list;
        }
        List<Integer> collect = new ArrayList<>();
        collect.add(1);
        for (int i = 0; i < list.size() - 1; ++i) {
            collect.add(list.get(i) + list.get(i + 1));
        }
        collect.add(1);
        return getRow(collect, rowIndex - 1);
    }

    public static void main(String[] args) {
        List<Integer> list = getRow(4);
        for (int i : list)
            System.out.print(i + " ");
        System.out.println();
    }
}
