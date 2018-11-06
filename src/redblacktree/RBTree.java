package redblacktree;



import queue.ArrayQueue;
import queue.Queue;

import java.util.Stack;
import java.util.TreeMap;

/**
 * 红黑树
 * Created by chenjian
 * 2018/10/28 16:17
 */
public class RBTree<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public boolean color;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = null;
            color = RED;
        }
    }
    private int size;
    private Node root;
    public RBTree() {
        root = null;
        size = 0;
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    // 向红黑树中添加一个新的元素
    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK; // 最终根节点是黑色
    }
    // 判断节点node的颜色
    private boolean isRed(Node node) {
        if (node == null)
            return BLACK;
        return node.color;
    }
    // 红黑树左旋转 返回旋转后的根节点
    //   node                        x
    //  /   \                      /   \
    // T1    x     ------->     node    T3
    //      / \                /   \
    //     T2  T3             T1    T2
    private Node leftRotate(Node node) {
        Node x = node.right;
        // 左旋转
        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;

        return  x;
    }
    // 颜色翻转
    private void flipColors(Node node) {

        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }
    // 红黑树右旋转 返回旋转后的根节点
    //      node                    x
    //     /   \                   / \
    //    x     T2  ------->      y   node
    //   / \                         /   \
    //  y   T1                      T1    T2
    private Node rightRotate(Node node) {
        Node x = node.left;
        // 右旋转
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    // 向以node为根的红黑树中添加一个元素e
    // 返回插入新节点后红黑树的根
    private Node add(Node node, K key, V value) {
        if (node == null) {
            ++size;
            return new Node(key,value);
        }
        if (key.compareTo(node.key) < 0) {
            // 往左子树中添加元素
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            // 往右子树中添加元素
            node.right = add(node.right, key, value);
        }

        if (isRed(node.right) && !isRed(node.left))
            node = leftRotate(node);

        if (isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);

        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        return node;
    }
    // 看二分搜索树中是否包含元素e
    public boolean contains(K key) {
        return contains(root, key);
    }
    // 向以根为node的二分搜索树中添加元素e
    private boolean contains(Node node, K key) {
        if (null == node)
            return false;
        if (key.compareTo(node.key) == 0)
            return true;
        else if (key.compareTo(node.key) < 0)
            return contains(node.left, key);
        else // e.compareTo(node.e) > 0
            return contains(node.right, key);
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
        System.out.println(node.key + " : " + node.value);
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
            System.out.println(cur.key + " : " + cur.value);

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
                System.out.println(temp.key + " : " + temp.value);
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
                Node tmp = stack.pop();
                System.out.println(tmp.key + " : " + tmp.value);
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
            System.out.println(current.key + " : " + current.value);
            if (current.left != null)
                queue.enQueue(current.left);
            if (current.right != null)
                queue.enQueue(current.right );
        }
    }
    // 寻找二分搜索树中最小值
    public V minimum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");
        return minimum(root).value;
    }
    // 寻找二分搜索树最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        // 从左子树中继续寻找最小值所在的节点
        return minimum(node.left);
    }

    // 寻找二分搜索树中的最大值
    public V maximum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");
        return minimum(root).value;
    }
    // 寻找最大值所在节点
    private Node maximum(Node node) {
        if (node.right == null)
            return node;
        return maximum(node.right);
    }
    // 寻找二分搜索树最小值， 非递归算法
    public V minimumNonRecursion() {
        if (root == null)
            throw new IllegalArgumentException("BST is empty");
        Node current = root;
        while (current.left != null)
            current = current.left;
        return current.value;
    }
    // 删除二分搜索树最小值
    public V removeMinimum() {
        V e = minimum();
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
    // 删除二分搜索树中key为键的节点
    public void remove(K key) {
        root = remove(root, key);
    }

    // 删除二分搜索树中node为根,key为键值的节点
    // 返回新二分搜索树的根
    private Node remove(Node node, K key) {
        if (node == null)
            return null;
        Node retNode = null;
        if (key.compareTo(node.key) == 0) {
            // 只有右子树
            if (node.left == null) {
                Node rightNode = node.right;
                node = null;
                --size;
                retNode = rightNode;
            }
            // 只有左子树
            if (node.right == null) {
                Node leftNode = node.left;
                node = null;
                --size;
                retNode = leftNode;
            }
            // 左右子树都存在
            // 可左旋或者右旋
            // 这里选择左旋 右旋也一样
            // 1.找到以待删除节点右子树为根二分搜素树的最小值node
            // 2.删除最小节点
            // 3.将待删除节点替换成最小节点node
            Node minNode = minimum(node.right);
            minNode.right = removeMinimum(node.right);
            minNode.left = node.left;
            node.left = node.right = null;
            /*node.key = minNode.key;
            node.value = minNode.value;*/

            retNode = minNode;
        } else if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        }
        return retNode;
    }

    public static void main(String[] args) {
        int[] nums = {5, 3, 6, 8, 4, 2};
        RBTree<Integer, Integer> bst2 = new RBTree<Integer, Integer>();
        for (int i : nums)
            bst2.add(i, i);
//        bst2.preOrder(bst2.root);
//        bst2.inOrderNonRecursion();
        System.out.println();
//        bst2.postOrderNonRecursion();
        bst2.levelOrder();
        bst2.inOrderNonRecursion();
    }
}
