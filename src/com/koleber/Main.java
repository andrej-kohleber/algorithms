package com.koleber;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream("/Users/andrej/Desktop/INPUT.TXT"), 128 * 1024 * 1024));
       
        int[] a = { -3, 4, 10, 0, 11, 15, 22, 100, -100};
       
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exchange(a, j, j - 1);
            }
        }
        
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
       
        PrintWriter printWriter = new PrintWriter("/Users/andrej/Desktop/OUTPUT.TXT");
       
        printWriter.close();
    }
    
    private static boolean less(int i, int j) {
        return i < j;
    }
    
    private static void exchange(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
