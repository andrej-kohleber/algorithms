package com.koleber.concurrency;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerBlockingQueue {
    
    public static void main(String[] args) {
        BlockingQueue<Integer> q = new LinkedBlockingQueue<>(1);
        Producer producer = new Producer(q);
        Consumer consumer = new Consumer(q);
        new Thread(producer).start();
        new Thread(consumer).start();
    }

    static class Consumer implements Runnable {

        private BlockingQueue<Integer> queue;

        public Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    consume(queue.take());
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        public void consume(int number) {
            System.out.println("Consumed " + number);
        }
    }

    static class Producer implements Runnable {

        private BlockingQueue<Integer> queue;
        private final Random random = new Random();

        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    queue.put(produce());
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        public int produce() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            int number = random.nextInt(10);
            System.out.println("produced number " + number);
            return number;
        }
    }
}
