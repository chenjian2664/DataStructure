package map;

/**
 * Created by chenjian
 * 2018/8/11 10:29
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V>{

    private class Node{
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
    private Node root;
    private int size;


    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
        ++size;
    }

    private Node add(Node node, K key, V value) {
        if (null == node) {
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0){
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        return node;
    }

    //辅助函数 从以node为根节点的map中返回以K 为key的节点
    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return getNode(node.right, key);
        } else {
            return node;
        }
     }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (null == node) {
            throw new IllegalArgumentException("NODE does not exists");
        }
        root = remove(root, key);
        --size;
        return node.value;
    }

    //从根为node的map中删除K为key的元素
    //并返回根元素
    private Node remove(Node node, K key) {
        if (null == node) {
            return null;
        }
        //该node就是要删除的node
        if (key.compareTo(node.key) == 0) {
            //只有左孩子
            if (null == node.right) {
                Node leftNode = node.left;
                node.left = null;
                return leftNode;
            }
            //只有右孩子
            if (null == node.left) {
                Node rightNode = node.right;
                node.right = null;
                return rightNode;
            }
            //左右孩子都有
            //让右孩子中最小元素替代当前元素
            Node tmp = removeMinNode(node);
            tmp.left = node.left;
            tmp.right = node.right;
            node.left = node.right = null;
            return tmp;
        } else if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        } else {
            node.right = remove(node.right, key);
        }
        return node;
    }
    //从node为根节点中删除最小元素 最后返回根元素
//    private Node removeMin(Node node) {
//        if (null == node) {
//            return null;
//        }
//        node.left = removeMinNode(node.left);
//        return node;
//    }
    private Node removeMinNode(Node node) {
        if (null == node) {
            return null;
        }
        if (null == node.left) {
             Node rightNode = node.right;
             node.right = null;
             return rightNode;
        }
        node.left = removeMinNode(node.left);
        return node;
    }


    @Override
    public boolean contains(K key) {
        return null == getNode(root, key);
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return null == node ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (null == node) {
            throw new IllegalArgumentException("NODE does not exits");
        }
        node.value = newValue;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return 0 == size;
    }
}
