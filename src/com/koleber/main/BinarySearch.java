
package com.koleber.main;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;


/******************************************************************************
 *  Data files:   https://algs4.cs.princeton.edu/11model/tinyAllowlist.txt
 *                https://algs4.cs.princeton.edu/11model/tinyText.txt
 *                https://algs4.cs.princeton.edu/11model/largeAllowlist.txt
 *                https://algs4.cs.princeton.edu/11model/largeText.txt
 ******************************************************************************/

public class BinarySearch {
    
    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                hi = mid - 1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return - 1;
    }
    
    public static void main(String[] args) {
        int[] whitelist = In.readInts("https://algs4.cs.princeton.edu/11model/largeAllowlist.txt");
        Arrays.sort(whitelist);
        StdOut.print("Enter key: ");
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (rank(key, whitelist) == -1) {
                StdOut.print("Key: " + key + " not found\n");
                StdOut.print("Enter key: ");
            } else {
                StdOut.print("Key: " + key + " found\n");
                StdOut.print("Enter key: ");
            }
        }
    }
}
