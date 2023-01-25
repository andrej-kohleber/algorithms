package com.koleber.sort;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class SortCompare {
    
    public static double time(String alg, Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        if ("Insertion".equalsIgnoreCase(alg)) Insertion.sort(a);
        if ("Selection".equalsIgnoreCase(alg)) Selection.sort(a);
        if ("Shell".equalsIgnoreCase(alg)) Shell.sort(a);
        return timer.elapsedTime();
    }
    
    public static double timeRandomInput(String alg, int length, int numberOfArrays) {
        double total = 0.0;
        Double[] a = new Double[length];
        for (int t = 0; t < numberOfArrays; t++) {
            for (int i = 0; i < length; i++) 
                a[i] = StdRandom.uniform();
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        int arrLength = 100000;
        double t1 = timeRandomInput("selection", arrLength, 1);
        double t2 = timeRandomInput("insertion", arrLength, 1);
        double t3 = timeRandomInput("shell", arrLength, 1);
        System.out.println("For " + arrLength + " randomly generated Doubles time Selection " + t1);
        System.out.println("For " + arrLength + " randomly generated Doubles time Insertion " + t2);
        System.out.println("For " + arrLength + " randomly generated Doubles time Shell " + t3);
    }
}
