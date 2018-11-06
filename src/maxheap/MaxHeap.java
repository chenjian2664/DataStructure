package maxheap;

import array.Array;

import java.util.Random;

/**
 * Created by chenjian
 * 2018/8/19 10:35
 */
public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap() {
        data = new Array<>();
    }
    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
        for (int i = parent(size() - 1); i >= 0; -- i) {
            siftDown(i);
        }
    }


    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int parent(int index) {
        if (index <= 0) {
            throw new IllegalArgumentException("wrong index to find parent");
        }
        return (index - 1) / 2;
    }

    public int leftChild(int index) {
        return index * 2 + 1;
    }

    public int rightChild(int index) {
        return index * 2 + 2;
    }

    public void add(E e) {
         data.addLast(e);
         siftUp(size() - 1);
    }

    public void siftUp(int i) {
        E temp = data.get(i);
        while (i > 0 && data.get(parent(i)).compareTo(temp) < 0) {
            data.set(i, data.get(parent(i)));
            i = parent(i);
        }
        data.set(i, temp);
    }

    public void siftDown(int i) {
        //先判断左孩子 如果左孩子没有那么右孩子一定没有
        while (leftChild(i) < size()) {
            E temp = data.get(i);
            int pivot = leftChild(i);
            if (pivot + 1 < size() && data.get(pivot).compareTo(data.get(pivot + 1)) < 0){
                ++pivot;//当右孩子存在并且左孩子的值小于有孩子的时候 让pivot指向右孩子
            }
            if (temp.compareTo(data.get(pivot)) < 0) {
                data.swap(i, pivot);
                i = pivot;
            } else {
                break;
            }
        }
    }

    public E findMax() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Heap is empty ");
        }
        return data.get(0);
    }

    //取出堆顶元素
    public E extractMax() {
        E result = findMax();
        data.set(0, data.getLast());
        data.removeLast();
        siftDown(0);
        return result;
    }

    //取出堆顶元素并替换成新元素
    public E replace(E e) {
        E temp = findMax();
        data.set(0, e);
        siftDown(0);
        return temp;
    }

    public static void main(String[] args) {
        int n = 1000000;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; ++i) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = maxHeap.extractMax();
        }

        for (int i = 1; i < n; ++i) {
            if (arr[i - 1] < arr[i]) {
                throw new IllegalArgumentException("Error");
            }
        }
        System.out.println("success");

        for (int i = 0; i < 20; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}

