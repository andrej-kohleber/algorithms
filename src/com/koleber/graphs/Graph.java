package com.koleber.graphs;

import com.koleber.main.Bag;
import edu.princeton.cs.algs4.In;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    
    private final int v; //number of vertices
    private int e; // number of edges
    private Bag<Integer>[] adj; //lists of adjacent vertices
    
    public Graph(int v) {
        this.v = v;
        this.e = 0;
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<>();
        }
    }

    public Graph(In in) {
        this(in.readInt());
        int edges = in.readInt();
        for (int i = 0; i < edges; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }
    
    public int v() {
        return v;
    }
    
    public int e() {
        return e;
    }
    
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        e++;
    }     
    
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
    
    public static int degree(Graph g, int v) {
        int degree = 0;
        for (int w : g.adj(v)) degree++;
        return degree;
    }
    
    public static int maxDegree(Graph g) {
        int max = 0;
        for (int v = 0; v < g.v(); v++) 
            if (degree(g, v) > max) 
                max = degree(g, v);
        return max;
    }

    @Override
    public String toString() {
        String s = v + " vertices, " + e + " edges\n";
        for (int i = 0; i < v; i++) {
            s += v + ": ";
            for (int w : this.adj(i))
                s += w + " ";
            s += "\n";
        }
        return s;
    }

    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        Graph graph = new Graph(new In("https://algs4.cs.princeton.edu/41graph/tinyG.txt"));
        
        int c = 0;

        

//        int s = Integer.parseInt()
        List<String> list = new ArrayList<>();
        int y = 0;
    }
}
