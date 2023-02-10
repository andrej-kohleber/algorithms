package com.koleber.concurrency;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;


/**
 * CyclicBarrier is a universal synchronization aid which allows a set of threads to wait for each other to reach a 
 * common barrier point
 */
public class CyclicBarrierExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable barrierAction = () -> System.out.println("BarrierAction");
        CyclicBarrier barrier = new CyclicBarrier(2, barrierAction);

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(new Worker(barrier, i));
            threads.add(thread);
            thread.start();
        }


        for (Thread thread : threads) {
            thread.join();
        }

    }

    static class Worker implements Runnable {

        private CyclicBarrier barrier;
        private int i;

        public Worker(CyclicBarrier barrier, int i) {
            this.barrier = barrier;
            this.i = i;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
                System.out.println("Work before barrier");
                barrier.await();
                System.out.println("Work after barrier");
            } catch (InterruptedException ignore) {
            } catch (BrokenBarrierException ignore) {
            }
        }
    }
}
