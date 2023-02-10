package com.koleber.concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
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
public class Operations2 {

    private static final Object tieLock = new Object();
    
    public static void main(String[] args) throws InterruptedException {

        final Account a = new Account(1000000);
        final Account b = new Account(2000000);

//        ExecutorService executorService = Executors.newCachedThreadPool();
//        for (int i = 0; i < 1000; i++) {
//            Future<Boolean> submit = executorService.submit(new Transfer(a, b, 500));
//        }
//        executorService.shutdown();
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        
        scheduledExecutorService.scheduleAtFixedRate(new Test(), 1, 1, TimeUnit.SECONDS);

    }
    
    static class Test implements Runnable {
        @Override
        public void run() {
            System.out.println("hello world");
        }
    }
    
    public static void transfer(Account a1, Account a2, int amount) throws InterruptedException {
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
}
