package com.koleber.main;

import java.util.Iterator;

public class Stack<T> implements Iterable<T> {

    private int size;
    private Node first;
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public int size() {
        return size;
    }
    
    public void push(T item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        size++;
    }
    
    public T pop() {
        T item = first.item;
        first = first.next;
        size--;
        return item;
    }
    
    
    private class Node {
        T item;
        Node next;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }
    
    private class StackIterator implements Iterator<T> {
        
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
        Stack<String> stack = new Stack<>();
        stack.push("test1");
        stack.push("test2");
        stack.push("test3");

        String pop = stack.pop();

        for (String item : stack) {
            System.out.println(item);
        }
    }
}
