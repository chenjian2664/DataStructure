package avl;



import queue.ArrayQueue;
import queue.Queue;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by chenjian
 * 2018/10/21 14:29
 */
public class AVLTree<K extends Comparable<K>, V> {
    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public  int height;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = null;
            height = 1;
        }
    }
    private int size;
    private Node root;
    public AVLTree() {
        root = null;
        size = 0;
    }
    private int getHeight(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }
    // 判断是否是二分搜索树
    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        inorder(root, keys);
        for (int i = 1; i < keys.size(); ++i)
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0)
                return false;
        return true;
    }
    // 判断是否是平衡二叉树
    public boolean isBalanced() {
        return isBalanced(root);
    }
    private boolean isBalanced(Node node) {
        if (node == null)
            return true;
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1)
            return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }
    private void inorder(Node node, ArrayList<K> keys) {
        if (node == null)
            return;
        inorder(node.left, keys);
        keys.add(node.key);
        inorder(node.right, keys);
    }
    // 获得节点的平衡因子
    private int getBalanceFactor(Node node) {
//        return Math.abs(node.left.height - node.right.height);
        return getHeight(node.left) - getHeight(node.right);
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    // 向二分搜索树中添加一个新的元素
    public void add(K key, V value) {
        root = add(root, key, value);
    }
    // 向以node为根的树中添加一个元素e
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
        // 维护height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        // 平衡维护
        // LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            // 右旋转
            return rightRotate(node);
        }
        // RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }
        // LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }
    // LL
    // 对y进行右旋转操作， 返回旋转后的根节点x
    //        y                                 x
    //      /   \                              /  \
    //     x      T4    向右旋转y               z    y
    //    / \          ------------>         / \   / \
    //   z   T3                             T1  T2 T3 T4
    //  /  \
    // T1   T2
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;
        // 向右旋转过程
        x.right = y;
        y.left = T3;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), y.height) + 1;
        return x;
    }
    // RR
    // 对y进行左旋转操作， 返回旋转后的根节点x
    //          y                               x
    //         /  \                            / \
    //        T4   x        向左旋转            y   z
    //            / \     ---------->        / \  / \
    //           T3  z                      T4 T3 T1 T2
    //              / \
    //             T1  T2
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T3 = x.left;
        // 想左旋转过程
        x.left = y;
        y.right = T3;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.left)) + 1;
        return x;
    }
    // 删除key所在节点
    public void remove(K key) {
        root = remove(root, key);
    }
    // 从node为根的二分搜索树中删除key所在节点
    private Node remove(Node node, K key) {
        if (node == null)
            return null;
        Node retNode;
        if (key.compareTo(node.key) == 0) {
            // 左子树为空
            if (node.left == null) {
                Node rightNode = node.right;
                node = null;
                --size;
                retNode = rightNode;
            } else
            // 右子树为空
            if (node.right == null) {
                Node leftNode = node.left;
                node = null;
                --size;
                retNode = leftNode;
            } else {
                // 左右子树都不为空
                Node successor = minimum(node.right);

//            successor.right = removeMinimum(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
            }
        } else if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else {// key.compareTo(node.key) > 0
            node.right = remove(node.right, key);
            retNode = node;
        }
        if (retNode == null)
            return null;
        // 维护height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);
        // 平衡维护
        // LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            // 右旋转
            return rightRotate(retNode);
        }
        // RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }
        // LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(node);
        }
        // RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
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
        else // e.compareTo(node.key) > 0
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
//    // 删除二分搜索树最小值
//    public V removeMinimum() {
//        V e = minimum();
//        root = removeMinimum(root);
//        return e;
//    }
//    // 从node为根的二分搜索树中删除最小元素
//    // 返回删除这个元素后新的二分搜索树的根
//    private Node removeMinimum(Node node) {
//        if (node.left == null) {
//            Node rightNode = node.right;
//            node.right = null;
//            --size;
//            return rightNode;
//        }
//        node.left = removeMinimum(node.left);
//        return node;
//    }
    //

    public static void main(String[] args) {
        int[] nums = {5, 3, 6, 8, 4, 2};
        AVLTree<Integer, Integer> bst2 = new AVLTree<Integer, Integer>();
        for (int i : nums)
            bst2.add(i, i);
//        bst2.preOrder(bst2.root);
//        bst2.inOrderNonRecursion();
        System.out.println(bst2.isBalanced());
//        bst2.postOrderNonRecursion();
//        bst2.levelOrder();
//        bst2.inOrderNonRecursion();
        bst2.remove(8);
        bst2.remove(5);
        System.out.println(bst2.isBalanced());
        bst2.inOrderNonRecursion();
    }
}
