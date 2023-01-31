package com.koleber.search;

import com.koleber.main.Queue;
import edu.princeton.cs.algs4.StdOut;

public class BST<K extends Comparable<K>, V> {
    
    private Node root;
    
    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private int size;

        public Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }
    
    public int size() {
        return size(root);
    }
    
    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }
    
    public V get(K key) {
        return get(root, key);
    }
    
    private V get(Node x, K key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.value;
    }
        
    public void put(K key, V value) {
        root = put(root, key, value);
    }
    
    private Node put(Node x, K key, V value) {
        if (x == null) return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, value);
        else if (cmp > 0) x.right = put(x.right, key, value);
        else x.value = value;
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }
    
    public K min() {
        return min(root).key;
    }
    
    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public K max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }
    
    public K floor(K key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }
    
    private Node floor(Node x, K key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }
    
    public K select(int k) {
        return select(root, k).key;
    }
    
    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > 0) return select(x.left, k);
        else if (t < 0) return select(x.right, k - t - 1);
        else return x;
    }
    
    public int rank(K key) {
        return rank(key, root);
    }
    
    public int rank(K key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }
    
    public void deleteMin() {
        root = deleteMin(root);
    }
    
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }
    
    public void delete(K key) {
        root = delete(root, key);
    }
    
    private Node delete(Node x, K key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }
    
    private void print(Node x) {
        if (x == null) return;
        print(x.left);
        StdOut.println(x.key);
        print(x.right);
    }
    
    public Iterable<K> keys() {
        return keys(min(), max());
    }
    
    public Iterable<K> keys(K lo, K hi) {
        Queue<K> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }
    
    private void keys(Node x, Queue<K> queue, K lo, K hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }
}
