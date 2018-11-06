package array;

/**
 * Created by chenjian
 * 2018/7/8 9:58
 */
public class Array<E> {
    private E[] data;
    private int size;

    public Array(E[] arr) {
        size = arr.length;
        data = (E[]) new Object[size];
        for (int i = 0; i < size; ++i) {
            data[i] = arr[i];
        }
    }

    public Array(int capacity) {
        data = (E[])new Object[capacity];
        size = 0;
    }
    public Array() {
        this(10);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getCapacity() {
        return data.length;
    }
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("wrong index to insert");
        }
        if (size >= data.length)
            resize(data.length * 2);

        for (int i = size - 1; i >= index; --i) {
            data[i+1] = data[i];
        }
        data[index] = e;
        ++size;
    }

    // 删除指定索引位置元素
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove Failed. Index is illegal");
        }
        E ret = data[index];
        for (int i = index + 1; i < size; ++i) {
            data[i - 1] = data[i];
        }
        --size;

        if (size == data.length / 4  && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return ret;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        } else {
            System.out.println("no this element");
        }
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    private void resize(int newCapacity) {
        E[] newContent = (E[])new Object[newCapacity];
        for (int i = 0; i < size; ++i) {
            newContent[i] = data[i];
        }
        data = newContent;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("array.Array: size = %d, capacity = %d\n", size, data.length));
        stringBuilder.append('[');
        for (int i = 0; i < size; ++i) {
            stringBuilder.append(data[i]);
            if (i != size - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public void swap(int i, int j) {
        if (i < 0 || j < 0 || i >= size || j >= size) {
            throw new IllegalArgumentException("illegal index ");
        }
        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illeagal");
        }

        return data[index];
    }

    public E getLast() {
        return get(size - 1);
    }

    public E getFirst() {
        return get(0);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index Illegal");
        }

        data[index] = e;
    }

    public boolean contains(int e) {
        for (E e1 : data) {
            if (e1.equals(e)) {
                return true;
            }
        }
        return false;
    }

    public int find(E e) {
        for (int i = 0; i < size; ++i) {
            if (data[i] == e) {
                return i;
            }
        }
        return -1;
    }
    public boolean contains(E e) {
        int index = find(e);
        if (index == -1) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        Array array = new Array(10);
        for (int i = 0; i < 10; ++i) {
            array.addLast(i);
        }
        System.out.println(array);
        array.add(1, 100);
        array.addFirst(-1);
        array.remove(2);
        array.removeElement(8);
        System.out.println(array);
    }
}
