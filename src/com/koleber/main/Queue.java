package com.koleber.main;

import java.util.Iterator;

public class Queue<T> implements Iterable<T> {
    
    private Node first; //link to the "oldest" node
    private Node last;  //link to the "freshest" node
    private int size;

    private class Node {
        T item;
        Node next;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public void enqueue(T item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) first = last;
        else oldLast.next = last;
        size++;
    }
    
    public T dequeue() {
        T item = first.item;
        first = first.next;
        if (isEmpty()) first = last;
        size--;
        return item;
    }


    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<T> {
        
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current == null;
        }

        @Override
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        queue.enqueue("test1");
        queue.enqueue("test2");
        queue.enqueue("test3");

        String dequeue = queue.dequeue();
        int c = 0;
    }
}
