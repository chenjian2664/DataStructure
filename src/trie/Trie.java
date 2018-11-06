package trie;

import java.util.Collections;
import java.util.TreeMap;

/**
 * Created by chenjian
 * 2018/10/14 15:46
 */
public class Trie {
    private class Node {
        public boolean isWord;
        public TreeMap<Character, Node> next;
        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }
        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }
    public int getSize() {
        return size;
    }
    public Trie() {
        root = new Node();
        size = 0;
    }
    // 向Trie中添加一个单词word
    public void add(String word) {
        //handle null
        if (null == word || word.length() == 0)
            return;
        Node cur = root;
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if (null == cur.next.get(ch)) { // 不存在,添加
                cur.next.put(ch, new Node());
            }
            cur = cur.next.get(ch);
        }
        if (!cur.isWord) {
            cur.isWord = true;
            ++size;
        }
    }

    // 查询单词word是否存在
    public boolean contains(String word) {
        Node cur = root;
        if (null == word || word.length() == 0)
            return true;
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if (null == cur.next.get(ch)) {
                return false;
            }
            cur = cur.next.get(ch);
        }

        return cur.isWord;
    }

    // 查询是否有单词是以prefix为前缀
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); ++i) {
            char ch = prefix.charAt(i);
            if (null == cur.next.get(ch))
                return false;
            cur = cur.next.get(ch);
        }
        return true;
    }

}
