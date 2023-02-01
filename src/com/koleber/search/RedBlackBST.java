package com.koleber.search;

public class RedBlackBST<K extends Comparable<K>, V> {
    
    private static final boolean RED = true; 
    private static final boolean BLACK = false;
    
    private Node root;
    
    private class Node {
        K key;
        V value;
        Node left, right;
        int size;
        boolean color;
        
        Node(K key, V value, int size, boolean color) {
            this.key = key;
            this.value = value;
            this.size = size;
            this.color = color;
        }
    }
    
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }
    
    public int size() {
        return size(root);
    }
    
    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }
    
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }
    
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }
    
    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
    
    public void put(K key, V value) {
        root = put(root, key, value);
        root.color = BLACK;
    }
    
    private Node put(Node h, K key, V value) {
        if (h == null) return new Node(key, value, 1, RED);
        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, value);
        else if (cmp > 0) h.right = put(h.right, key, value);
        else h.value = value;
        
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        
        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    public static void main(String[] args) {
        
        
        RedBlackBST<String, Integer> redBlackBST = new RedBlackBST<>();
        redBlackBST.put("S", 1);
        redBlackBST.put("E", 1);
        redBlackBST.put("A", 1);
        redBlackBST.put("R", 1);
        redBlackBST.put("C", 1);
        redBlackBST.put("H", 1);
        redBlackBST.put("X", 1);
        redBlackBST.put("M", 1);
        redBlackBST.put("P", 1);
        redBlackBST.put("L", 1);

        RedBlackBST<String, Integer> redBlackBST2 = new RedBlackBST<>();
        redBlackBST2.put("A", 1);
        redBlackBST2.put("C", 1);
        redBlackBST2.put("E", 1);
        redBlackBST2.put("H", 1);
        redBlackBST2.put("L", 1);
        redBlackBST2.put("M", 1);
        redBlackBST2.put("P", 1);
        redBlackBST2.put("R", 1);
        redBlackBST2.put("S", 1);
        redBlackBST2.put("X", 1);
        
        int c = 0;
    }
}
