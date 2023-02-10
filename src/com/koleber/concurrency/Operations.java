package com.koleber.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * run the main method to simulate a deadlock
 * run jps to list all processes get id of all java processes
 * run jstack id to list the dump of all threads (Found one Java-level deadlock)
 * 
 * run jconsole (alternative)
 */
public class Operations {

    private static final Object tieLock = new Object();
    
    public static void main(String[] args) throws InterruptedException {
        
        final Account a = new Account(1000);
        final Account b = new Account(2000);

        AtomicInteger w = new AtomicInteger(10);
        w.addAndGet(10);

        new Thread(() -> {
            try {
                enhancedTransfer(a, b, 500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                enhancedTransfer(b, a, 500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }).start();
        
        
        Lock lock = new ReentrantLock();
        lock.tryLock();
    }
    
    
    
    public static void transfer(Account a1, Account a2, int amount) throws InterruptedException {
        if (a1.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }
        synchronized (a1) {
            Thread.sleep(1000);
            System.out.println("looked a1 " + a1.getClass());

            synchronized (a2) {
                System.out.println("looked a2");
                a1.withdraw(amount);
                a2.deposit(amount);        
            }
        }
    }
    
    public static void enhancedTransfer(Account a1, Account a2, int amount) throws InterruptedException {
        if (a1.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }
        int fromHash = System.identityHashCode(a1);
        int toHash = System.identityHashCode(a2);
        
        if (fromHash < toHash) {
            synchronized (a1) {
                Thread.sleep(1000);
                System.out.println("looked a1 " + a1.getClass());
                synchronized (a2) {
                    System.out.println("looked a2");
                    a1.withdraw(amount);
                    a2.deposit(amount);
                }
            }
        } else if (fromHash > toHash) {
            synchronized (a2) {
                Thread.sleep(1000);
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
                    Thread.sleep(1000);
                    System.out.println("looked a1 " + a1.getClass());
                    synchronized (a2) {
                        System.out.println("looked a2");
                        a1.withdraw(amount);
                        a2.deposit(amount);
                    }
                }
            }
        }
    }

    public static void test()  {
        Lock lock = new ReentrantLock();
        try {
            lock.lock();
        } finally {
            lock.unlock();
        }
        
    }
   
    
    
}
