package com.koleber.concurrency;

import java.io.FileNotFoundException;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch is a universal synchronization aid allows starting other threads at once after some events occur
 * for example starting some threads after some initialization has been done.
 */
public class CountDownLatchExample {

    public static void main(String[] args) throws FileNotFoundException {
        CountDownLatch startSignal = new CountDownLatch(1);
        new Thread(new Worker(startSignal)).start();
        init();
        startSignal.countDown();
    }

    public static void init() {
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(1000);
                System.out.println("Initializing");
            }
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }


    static class Worker implements Runnable {

        private CountDownLatch startSignal;

        public Worker(CountDownLatch startSignal) {
            this.startSignal = startSignal;
        }

        @Override
        public void run() {
            try {
                startSignal.await();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Worker is in progress");
            System.out.println("Useful work done");
        }
    }
}
