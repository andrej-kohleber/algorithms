package com.koleber.strings;

public class LSD {

    public static void main(String[] args) {
        String[] a = {
                "1ZEQ313",
                "2WEO522",
                "4UWJ261",
                "4TWW169",
                "3REO388",
                "2UKP897",
                "1RNR716",
                "4NZA625",
                "3EYF534",
                "2WYS123",
        };
        
        String[] a1 = {
                "z",
                "y",
                "y",
                "x",
                "w",
                "v",
                "u",
                "t",
                "s",
                "r",
                "q",
        };
        
        sort1(a1, 1);
        int c = 0;
    }

    public static void sort1(String[] a, int W) {
        int N = a.length;
        int R = 256;

        String[] aux = new String[N];
        int[] count = new int[R + 1];
        for (int i = 0; i < N; i++)
            count[a[i].charAt(0) + 1]++;

        for (int r = 0; r < R; r++)
            count[r + 1] += count[r];

        for (int i = 0; i < N; i++)
            aux[count[a[i].charAt(0)]++] = a[i];

        for (int i = 0; i < N; i++)
            a[i] = aux[i];
        
    }
    
    public static void sort(String[] a, int W) {
        int N = a.length;
        int R = 256;
        
        String[] aux = new String[N];
        for (int d = W - 1; d >= 0; d--) {
            int[] count = new int[R + 1];
            for (int i = 0; i < N; i++) 
                count[a[i].charAt(d) + 1]++;
            
            for (int r = 0; r < R; r++)
                count[r + 1] += count[r];
            
            for (int i = 0; i < N; i++)
                aux[count[a[i].charAt(d)]++] = a[i];
            
            for (int i = 0; i < N; i++)
                a[i] = aux[i];
        }
    }
}
