package BST;

import queue.ArrayQueue;
import stack.ArrayStack;

/**
 * Created by chenjian
 * 2018/7/22 8:52
 */
public class BST<E extends Comparable<E>> {
    private class Node {
        public E e;
        public Node left;
        public Node right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }
    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public BST(E e) {
        root.e = e;
        size = 1;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
//        if (null == root) {
//            root = new Node(e);
//            ++size;
//        } else {
//            add(root, e);
//        }
        root = add(root, e);
    }

    // 向以根为root的树中添加元素e
    private Node add(Node node, E e) {
//        if (e.equals(root.e)) {
//            return;
//        } else if (e.compareTo(root.e) < 0 && null == root.left) {
//            root.left = new Node(e);
//            ++size;
//            return;
//        } else if (e.compareTo(root.e) > 0 && null == root.right) {
//            root.right = new Node(e);
//            ++size;
//            return;
//        }
//        if (e.compareTo(root.e) < 0) {
//            add(root.left, e);
//        } else {
//            add(root.right, e);
//        }
        if (null == node) {
            ++size;
            return new Node(e);
        } else if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (null == node) {
            return false;
        }
        if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            return contains(node.right, e);
        }
        return true;
    }

    public void preOrder() {
        preOrder(root);
    }
    private void preOrder(Node r) {
        if (null == r) {
//            System.out.println(" over..");
            return;
        }
        System.out.println(r.e);
        preOrder(r.left);
        preOrder(r.right);
    }

    public void preOrderNR() {
        if (null == root) {
            return;
        }
        ArrayStack<Node> stack = new ArrayStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node temp = stack.pop();
            System.out.println(temp.e);

            if (null != temp.right) {
                stack.push(temp.right);
            }
            if (null != temp.left) {
                stack.push(temp.left);
            }
        }
    }

    // 寻找到最小元素
    public Node findMin() {
        return findMin(root);
    }
    private Node findMin(Node node) {
        if (null == node) {
            return node;
        }
        if (null == node.left) {
            return node;
        }
        return findMin(node.left);
    }

    public Node findMax() {
        return findMax(root);
    }
    private Node findMax(Node node) {
        if (null == node) {
            return node;
        }
        if (null == node.right) {
            return node;
        }

        return findMax(node.right);
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (null == node) {
            return;
        }

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    // 后续遍历一个应用 释放内存
    private void postOrder(Node root) {
        if (null == root) {
            return;
        }

        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.e);
    }

    public void levelOrder() {
        if (null == root) {
            return;
        }
        ArrayQueue<Node> queue = new ArrayQueue<>();
        queue.enQueue(root);
        Node temp = null;
        // 记录当前需要换层的节点
        Node last = root;
        // 记录下一个需要换层的节点
        Node nextLast = null;
        while (!queue.isEmpty()) {
            temp = queue.deQueue();
            System.out.print(temp.e + " ");

            if (null != temp.left) {
                queue.enQueue(temp.left);
                nextLast = temp.left;
            }
            if (null != temp.right) {
                queue.enQueue(temp.right);
                nextLast = temp.right;
            }

            if (temp.equals(last)) {
                System.out.println();
                last = nextLast;
            }
        }
    }

    // 删除最小元素
    public Node removeMinimum() {
        if (null == root) {
            throw new IllegalArgumentException("BST is empty");
        }
        Node cur = root;
        Node next = root.left;
        if (null == next) {
            return null;
        }
        while (null != next.left) {
            cur = cur.left;
            next = next.left;
        }
        cur.left = next.right;
        --size;
        return next;
    }

    // 删除二叉树中任意元素
    public Node remove(E e) {
        return remove(root, e);
    }

    private Node remove(Node root, E e) {
        if (null == root) {
            return null;
        }
        //TODO
        // 目标元素比当前节点元素小 往左走
        if (e.compareTo(root.e) < 0) {
            root.left = remove(root.left, e);
            return root.left;
        } else if (e.compareTo(root.e) > 0) {//往右走
            root.right = remove(root.right, e);
            return root.right;
        } else { // 删除该节点
            // 1.该节点只有左孩子
            if (null == root.right) {
                Node retLeft = root.left;
                root.left = null;
                --size;
                return retLeft;
            }
            // 2.该节点只有右孩子
            if (null == root.left) {
                Node retRight = root.right;
                root.right = null;
                --size;
                return retRight;
            }
            //3.该节点左右都有孩子 考虑让右子树中最小元素替代当前元素 然后删除右子树最小元素
            Node rightMin = removeMinimunRecursion(root.right);
            rightMin.right = root.right;
            rightMin.left = root.left;
            root.left = root.right = null;
            return rightMin;
        }
    }

    // 删除最小元素递归
    public Node removeMinimunRecursion(Node root) {
        if (null == root) {
            throw new IllegalArgumentException("BST is empty");
        }
//        Node node = root;
//        while (null != node.left) {
//            node = node.left;
//        }

        root = removeMinimunRecursionHelper(root);
        --size;
        return root;
    }

    // 删除以root为根节点的最小元素并返回跟节点
    private Node removeMinimunRecursionHelper(Node node) {
        if (null == node.left) {
            Node rightNode = node.right;
            node.right = null;
            return rightNode;
        }

        node.left = removeMinimunRecursionHelper(node.left);

        return node;
    }

    // ceil 寻找大于目标元素的最小元素
    public Node ceil(E e) {
        return ceil(root, e);
    }
    private Node ceil(Node node, E e) {
        if (null == node) {
            return node;
        }
        // 目标元素小于当前元素 往左走
        if (e.compareTo(node.e) <= 0) {
            // 如果目标元素比左子树最大值小那么从左子树中寻找节点
            Node leftMax = findMax(node.left);
            if (null == leftMax) {
                return node;
            }
            if (e.compareTo(leftMax.e) < 0) {
                return ceil(node.left, e);
            } else {
                // 如果目标元素比左子树最大值都大那么 根节点就是要找的节点
                return node;
            }
        } else  {
            return ceil(node.right, e);
        }
//        return null;
    }

    // floor 寻找小于目标元素的最大元素
    public Node floor(E e) {
        return floor(root, e);
    }
    private Node floor(Node node, E e) {
        if (null == node) {
            return node;
        }

        if (e.compareTo(node.e) >= 0) {
            Node rightMin = findMin(node.right);
            if (null == rightMin) {
                return node;
            }
            if (e.compareTo(rightMin.e) < 0) {
                return node;
            } else {
                return floor(node.right, e);
            }
        } else {
            return floor(node.left, e);
        }
//        return null;
    }

    public static void main(String[] args) {
        BST<Integer> root = new BST<Integer>();
        int nums[] = new int[]{5, 3, 6, 8, 4, 2};
//        root.myPrint(root.root);
//        System.out.println(root.contains(7));
//        System.out.println(root.contains(8));
        for (int num : nums) {
            root.add(num);
        }

        System.out.println(root.ceil(10) == null ? -1 : root.ceil(10).e);
        System.out.println(root.floor(7).e);
//        root.preOrder(root.root);
//        root.preOrder();
//        root.levelOrder();
//        System.out.println();
//        BST.Node node = root.removeMinimum();
//        root.levelOrder();
//        System.out.println(node.e);
//        root.preOrderNR();
//        root.inOrder();
//        System.out.println();
//        root.postOrder();
    }
}
