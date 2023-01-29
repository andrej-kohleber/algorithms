package com.koleber.sort;

import java.util.Comparator;
import java.util.Set;

public class Transaction {
    
    private final String who;
    private final Date when;
    private final double amount;

    public Transaction(String who, Date when, double amount) {
        this.who = who;
        this.when = when;
        this.amount = amount;
    }
    

    public static class WhoOrder implements Comparator<Transaction> {
        
        @Override
        public int compare(Transaction v, Transaction w) {
            return v.who.compareTo(w.who);
        }
    }

    public static class WhenOrder implements Comparator<Transaction> {

        @Override
        public int compare(Transaction v, Transaction w) {
            return v.when.compareTo(w.when);
        }
    }

    public static class HowMuchOrder implements Comparator<Transaction> {

        @Override
        public int compare(Transaction v, Transaction w) {
            if (v.amount < w.amount) return -1;
            if (v.amount > w.amount) return +1;
            return 0;
        }
    }

    public static void main(String[] args) {
        Transaction t1 = new Transaction("Koleber Andrej", new Date(2, 10, 2001), 10000);
        Transaction t2 = new Transaction("Smith John", new Date(12, 10, 2001), 5423);
        Transaction t3 = new Transaction("Koleber Andrej", new Date(3, 10, 2001), 9001);
        
        Transaction[] transactions = {t1, t2, t3};
        Insertion.sort(transactions, new Transaction.HowMuchOrder());
        Insertion.sort(transactions, new Transaction.WhenOrder());
        Insertion.sort(transactions, new Transaction.WhoOrder());
    }
}
