package com.koleber.main;

public class Euclid {
    
    public static int greatestCommonDivisor(int p, int q) {
        if (q == 0) {
            return p;
        }
        int r = p % q;
        return greatestCommonDivisor(q, r);
    }

    public static void main(String[] args) {
        int gcd = greatestCommonDivisor(7, 57);
    }
}
