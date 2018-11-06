package segmenttree;

import java.util.*;

/**
 * Created by chenjian
 * 2018/8/19 19:25
 */
public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4 * getSize()];
        buildSegmentTree(0, 0, data.length - 1);
    }

    // 在treeIndex的位置创建表示区间[l...r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        int leftTree = leftChild(treeIndex);
        int rightTree = rightChild(treeIndex);
        int mid = l + (r - l) / 2;

        buildSegmentTree(leftTree, l, mid);
        buildSegmentTree(rightTree, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftTree], tree[rightTree]);
    }

    public int getSize() {
        return data.length;
    }
    public E get(int index) {
        if (index < 0 || index > getSize()) {
            throw new IllegalArgumentException("wrong index");
        }
        return data[index];
    }
    // 返回完全二叉树的数组表示中，一个索引表示的元素的左孩子的节点的索引
    private int leftChild(int index) {
        return 2 * index + 1;
    }
    private int rightChild(int index) {
        return 2 * index + 2;
    }
    // 返回区间[queryL, queryR]的值
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("index is illegal.");
        return query(0, 0, data.length - 1, queryL, queryR);
    }
    // 在以treeID为根的线段树中[l...r]的范围， 搜索区间[queryL, queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR)
            return tree[treeIndex];
        int mid = l + (r - l) / 2;
        if (queryL > mid) {
            return query(rightChild(treeIndex), mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftChild(treeIndex), l, mid, queryL, queryR);
        }
        E leftResult = query(leftChild(treeIndex), l, mid, queryL, mid);
        E rightResult = query(rightChild(treeIndex), mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    // 将index位置的值更新为e
    private void set(int index, E e) {
        if (null == tree)
            throw new IllegalArgumentException("SegmentTree is empty");
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    // 将以treeIndex为根的线段树更新index位置的值为e
    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }
        int leftIndex = leftChild(treeIndex);
        int rightIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;
        if (index > mid)
            // 右边子树
            set(rightIndex, mid + 1, r, index, e);
        else
            set(leftIndex, l, mid, index, e);

        tree[treeIndex] = merger.merge(tree[leftIndex], tree[rightIndex]);
    }

    @Override
    public String toString() {
        return "SegmentTree{" +
                "data=" + Arrays.toString(data) +
                ", tree=" + Arrays.toString(tree) +
                ", merger=" + merger +
                '}';
    }

    /*public static int reverseBits(int n) {
            for (int i = 0; i < 16; ++i) {
                if ((n & (1 << i)) != (n & (1 << 31-i))) {
                    int temp = 1 << i;
                    temp |= 1 << 31 - i;
                    n ^= temp;
                }
            }
            return n;
        }*/
    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 1, 4, -3, 5, 6};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (e1, e2) -> e1 + e2);
        System.out.println(segmentTree.query(1, 3));
//        Random random = new Random();
//        for (int i = 0; i < 10; ++i)
//        System.out.println(random.nextInt(2));
//        Set<Integer> set = new HashSet<>();
//        set.add(1);
//        set.add(8);
//        set.add(2);
//        set.add(3);
//        for (int i : set) {
//            System.out.println(i);
//        }
//List<Integer> list = new ArrayList<>();
//        List<Integer> list = new ArrayList<Integer>();
//        System.out.println(SegmentTree.reverseBits(43261596));
    }
}
