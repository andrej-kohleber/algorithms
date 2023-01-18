package com.koleber.main;

import java.util.Iterator;

public class ResizingArrayStack<T> implements Iterable<T> {
    
    private static final int DEFAULT_CAPACITY = 50;
    private T[] a;
    private int length;

    public ResizingArrayStack() {
        a = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ResizingArrayStack(int cap) {
        a = (T[]) new Object[cap];
    }
    
    public boolean isEmpty() {
        return length == 0;
    }
    
    public int size() {
        return length;
    }
    
    public void push(T item) {
        if (length == a.length) resize(2 * a.length);
        a[length++] = item;
    }
    
    public T pop() {
        T item = a[--length];
        a[length] = null;
        if (length > 0 && length == a.length / 4) resize(a.length / 2);
        return item;
    }
    
    public void resize(int max) {
        T[] temp = (T[]) new Object[max];
        for (int i = 0; i < length; i++) 
            temp[i] = a[i];
        a = temp;
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<T> {

        private int i = length;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            return a[--i];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        ResizingArrayStack<String> s = new ResizingArrayStack(10);
        s.push("test1");
        s.push("test2");
        
        for (String test : s) {
            System.out.println(test);
        }
        int size = s.size();
        int c = 0;
        String pop = s.pop();
        int k = s.size();
    }
}
