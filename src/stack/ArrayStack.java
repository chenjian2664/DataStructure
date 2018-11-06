package stack;

import array.Array;

/**
 * Created by chenjian
 * 2018/7/8 9:56
 */
public class ArrayStack<E> implements Stack<E> {
    private Array<E> array;

    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayStack() {
        array = new Array<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" Stack: ");
        stringBuilder.append('[');
        for (int i = 0; i < array.getSize(); ++i) {
            stringBuilder.append(array.get(i));
            if (i != array.getSize() - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("] top");
        return stringBuilder.toString();
    }


        public static boolean isValid(String s) {
            if (s == null || s.isEmpty()) {
                return true;
            }
            int len = s.length();
            for (int i = 0; i < len / 2; ++i) {
                if (s.charAt(i) != (s.charAt(len - 1 - i))) {
                    return false;
                }
            }

            return true;
        }

    public static void main(String[] args) {
//        ArrayStack<Integer> stack = new ArrayStack<>();
//
//        for (int i = 0; i < 5; ++i) {
//            stack.push(i);
//            System.out.println(stack);
//        }
//
//        stack.pop();
//        System.out.println(stack);

        String s = "()";
        System.out.println(isValid(s));

    }

}

