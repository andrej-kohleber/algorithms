package com.koleber;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream("/Users/andrej/Desktop/INPUT.TXT"), 128 * 1024 * 1024));
        PrintWriter printWriter = new PrintWriter("/Users/andrej/Desktop/OUTPUT.TXT");
        
        int size = scanner.nextInt();
        int[] arr = new int[201];
        for (int i = 0; i < size; i++) {
            arr[scanner.nextInt() + 100]++;
        }
        
        for (int i = -100; i < 101; i++) {
            int cnt = arr[i + 100];

            for (int j = 0; j < cnt; j++) {
                printWriter.print(i + " ");
                System.out.print(i + " ");
            }
        }
       
        printWriter.close();
    }
}
