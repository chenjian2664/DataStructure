package BST;



import queue.ArrayQueue;
import queue.Queue;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by chenjian
 * 2018/10/21 14:29
 */
public class BST2<E extends Comparable<E>> {
    private class Node {
        public E e;
        public Node left, right;
        public Node(E e) {
            this.e = e;
            left = right = null;
        }
    }
    private int size;
    private Node root;
    public BST2() {
        root = null;
        size = 0;
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    // 向二分搜索树中添加一个新的元素
    public void add(E e) {
        root = add(root, e);
    }
    // 向以node为根的树中添加一个元素e
    private Node add(Node node, E e) {
        if (node == null) {
            ++size;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {
            // 往左子树中添加元素
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            // 往右子树中添加元素
            node.right = add(node.right, e);
        }
        //什么都不做
        return node;
    }
    // 看二分搜索树中是否包含元素e
    public boolean contains(E e) {
        return contains(root, e);
    }
    // 向以根为node的二分搜索树中添加元素e
    private boolean contains(Node node, E e) {
        if (null == node)
            return false;
        if (e.compareTo(node.e) == 0)
            return true;
        else if (e.compareTo(node.e) < 0)
            return contains(node.left, e);
        else // e.compareTo(node.e) > 0
            return contains(node.right, e);
    }

    // 二分搜索树的前序遍历
    public void preOreder() {
        preOrder(root);
    }

    // 前序遍历以node为根的二分搜索树， 递归算法
    private void preOrder(Node node) {
        if (null == node)
            return;
        /**访问该节点*/
        preOrder(node.left);
        preOrder(node.right);
        System.out.println(node.e);
    }

    // 前序遍历二分搜索树， 非递归算法
    private void preOrderNonRecursion() {
        if (null == root)
            return;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            /** 访问节点 */
            System.out.println(cur.e);

            if (null != cur.right)
                stack.push(cur.right);
            if (null != cur.left)
                stack.push(cur.left);
        }
    }

    // 中序遍历二分搜索树， 非递归算法
    public void inOrderNonRecursion(){
        if (null == root)
            return;
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                /** 访问该节点 */
                Node temp = stack.pop();
                System.out.println(temp.e);
                cur = temp.right;
            }
        }
    }
    // 后续遍历二分搜索树， 非递归算法
    public void postOrderNonRecursion() {
        if (null == root)
            return;
        Stack<Node> stack = new Stack<>();
        Stack<Boolean> flag = new Stack<>();
        Node node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                flag.push(false);
                node = node.left;
            }
            while (!stack.isEmpty() && flag.peek()) {
                flag.pop();
                // deal
                System.out.println(stack.pop().e);
            }
            if (!stack.isEmpty()) {
                flag.pop();
                flag.push(true);
                node = stack.peek().right;
            }
        }
    }

    // 层序遍历
    public void levelOrder() {
        if (null == root)
            return;
        Queue<Node> queue = new ArrayQueue<>();
        queue.enQueue(root);
        while (!queue.isEmpty()) {
            Node current = queue.deQueue();
            // deal
            System.out.println(current.e);
            if (current.left != null)
                queue.enQueue(current.left);
            if (current.right != null)
                queue.enQueue(current.right );
        }
    }
    // 寻找二分搜索树中最小值
    public E minimum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");
        return minimum(root).e;
    }
    // 寻找二分搜索树最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        // 从左子树中继续寻找最小值所在的节点
        return minimum(node.left);
    }

    // 寻找二分搜索树中的最大值
    public E maximum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");
        return minimum(root).e;
    }
    // 寻找最大值所在节点
    private Node maximum(Node node) {
        if (node.right == null)
            return node;
        return maximum(node.right);
    }
    // 寻找二分搜索树最小值， 非递归算法
    public E minimumNonRecursion() {
        if (root == null)
            throw new IllegalArgumentException("BST is empty");
        Node current = root;
        while (current.left != null)
            current = current.left;
        return current.e;
    }
    // 删除二分搜索树最小值
    public E removeMinimum() {
        E e = minimum();
        root = removeMinimum(root);
        return e;
    }
    // 从node为根的二分搜索树中删除最小元素
    // 返回删除这个元素后新的二分搜索树的根
    private Node removeMinimum(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            --size;
            return rightNode;
        }
        node.left = removeMinimum(node.left);
        return node;
    }

    public static void main(String[] args) {
        int[] nums = {5, 3, 6, 8, 4, 2};
        BST2<Integer> bst2 = new BST2<Integer>();
        for (int i : nums)
            bst2.add(i);
//        bst2.preOrder(bst2.root);
//        bst2.inOrderNonRecursion();
        System.out.println();
//        bst2.postOrderNonRecursion();
        bst2.levelOrder();
    }
}
