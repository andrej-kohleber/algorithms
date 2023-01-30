package com.koleber;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("/Users/andrej/Desktop/INPUT.TXT"));
        String string = "10"; //scanner.nextLine();
        
        int max = 0;
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '0') {
                count++;
            }
            if (string.charAt(i) == '1' || i == string.length() - 1) {
                if (count > max) {
                    max = count;
                }
                count = 0;
            }
        }
        
        PrintWriter printWriter = new PrintWriter("/Users/andrej/Desktop/OUTPUT.TXT");
        printWriter.println(max);
        printWriter.close();
    }
}
