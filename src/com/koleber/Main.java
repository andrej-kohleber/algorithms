package com.koleber;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("/Users/andrej/Desktop/INPUT.TXT"));
        int n = scanner.nextInt();
        int[] a = new int[n];
        
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        
        for (int i = 0; i < n / 2; i++) {
            int tmp = a[i];
            a[i] = a[n - 1 - i];
            a[n - 1 - i] = tmp;
        }
        
        PrintWriter printWriter = new PrintWriter("/Users/andrej/Desktop/OUTPUT.TXT");
        for (int i = 0; i < n; i++) {
            printWriter.print(a[i] + " ");
        }
        printWriter.close();
    }
}
