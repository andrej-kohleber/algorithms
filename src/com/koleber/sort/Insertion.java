package com.koleber.sort;

import java.util.Comparator;

public class Insertion {

    public static void sort(Comparable[] a) {
        int length = a.length;
        for (int i = 1; i < length; i++)
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
                exchange(a, j, j - 1);
    }

    public static void sort(Object[] a, Comparator c) {
        int length = a.length;
        for (int i = 1; i < length; i++)
            for (int j = i; j > 0 && less(c, a[j], a[j - 1]); j--)
                exchange(a, j, j - 1);
    }

    private static boolean less(Comparator c, Object v, Object w) {
        return c.compare(v, w) < 0;
    }
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exchange(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static void exchange(Object[] a, int i, int j) {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] arr = {10, 9, 8, 7, 4, 5, 11, 0, 33};
        show(arr);
        sort(arr);
        show(arr);
        assert isSorted(arr);
    }
}
