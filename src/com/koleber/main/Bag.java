package com.koleber.main;

import java.util.Iterator;

public class Bag<T> implements Iterable<T> {

    private int size;
    private Node first;
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public int size() {
        return size;
    }
    
    public void add(T item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        size++;
    }
    
    private class Node {
        T item;
        Node next;
    }

    @Override
    public Iterator<T> iterator() {
        return new BagIterator();
    }
    
    private class BagIterator implements Iterator<T> {
        
        private Node current  = first;
        
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Bag<String> stack = new Bag<>();
        stack.add("test1");
        stack.add("test2");
        stack.add("test3");

        
        for (String item : stack) {
            System.out.println(item);
        }
    }
}
