package com.koleber.main;

public class ArrayReverseOrder {
    
    public static void reverseOrder(int[] a) {
        int length = a.length;
        for (int i = 0; i < length / 2; i++) {
            int temp = a[i];
            a[i] = a[length - 1 - i];
            a[length - i - 1] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        reverseOrder(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
