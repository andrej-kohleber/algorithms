package com.koleber.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * run the main method to simulate a deadlock
 * run jps to list all processes get id of all java processes
 * run jstack id to list the dump of all threads (Found one Java-level deadlock)
 * 
 * run jconsole (alternative)
 */
public class OperationsSemaphore {
    
    private static Semaphore sem = new Semaphore(2);
    
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new Worker(1));
        service.execute(new Worker(2));
        service.execute(new Worker(3));
        service.execute(new Worker(4));
        
        service.shutdown();
    }
    
    static class Worker extends Thread {
        
        private final int id;

        public Worker(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println(id + " - trying to aquire...");
            try {
                sem.acquire();
                System.out.println(id + " - aquired");
                sleep(5000);
                sem.release();
                System.out.println(id + " - released");
            } catch (InterruptedException e) {
                System.err.println(id + " - interrupted");
            }
        }
    }
}
