package com.koleber.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class Transfer implements Callable<Boolean> {

    private Account a1;
    private Account a2;
    private int amount;
    private final Object tieLock = new Object();

    public Transfer(Account a1, Account a2, int amount) {
        this.a1 = a1;
        this.a2 = a2;
        this.amount = amount;
    }

    @Override
    public Boolean call() throws Exception {
        if (a1.getBalance() < amount) {
            throw new Exception("Insufficient funds");
        }
        int fromHash = System.identityHashCode(a1);
        int toHash = System.identityHashCode(a2);

        if (fromHash < toHash) {
            synchronized (a1) {
                Thread.sleep(100);
                System.out.println("looked a1 " + a1.getClass());
                synchronized (a2) {
                    System.out.println("looked a2");
                    a1.withdraw(amount);
                    a2.deposit(amount);
                }
            }
        } else if (fromHash > toHash) {
            synchronized (a2) {
                Thread.sleep(100);
                System.out.println("looked a1 " + a1.getClass());
                synchronized (a1) {
                    System.out.println("looked a2");
                    a1.withdraw(amount);
                    a2.deposit(amount);
                }
            }
        } else {
            synchronized (tieLock) {
                synchronized (a1) {
                    synchronized (a2) {
                        System.out.println("looked a2");
                        a1.withdraw(amount);
                        a2.deposit(amount);
                    }
                }
            }
        }
        System.out.println("transfer completed");
        return true;
    }
}
