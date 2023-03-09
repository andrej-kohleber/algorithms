package com.koleber.concurrency;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class ProducerConsumerWaitNotify {

    private static volatile boolean runningProducer;
    private static volatile boolean runningConsumer;
    
    private static final Random random = new Random();
    
    private static final Producer producer = new Producer();
    private static final Consumer consumer = new Consumer();
    
    private static final LinkedList<String> list = new LinkedList<>();
    
    
    private static ExecutorService producerService;
    private static ExecutorService consumerService;
    
    
      
  
    
    public static void main(String[] args) throws InterruptedException {
        if (runningConsumer || runningConsumer) {
            System.out.println("Producer or Consumer already running");
        }
        
        runningProducer = true;
        producerService = Executors.newSingleThreadExecutor();
        producerService.execute(producer);
        
        runningConsumer = true;
        consumerService = Executors.newSingleThreadExecutor();
        consumerService.execute(consumer);
        
        Thread.sleep(5 * 1000);
        
        
    }
    
    static class Producer implements Runnable {
        
        @Override 
        public void run() {
            while (runningProducer) {
                synchronized (list) {
                    while (!list.isEmpty()) {
                        System.out.println("List is not empty producer waiting");
                        try {
                            list.wait();
                        } catch(InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                
                synchronized (list) {
                    try {
                        int number = ThreadLocalRandom.current().nextInt(1000, 3000);
                        Thread.sleep(number);
                        String randomString = "Random number: " + number;
                        list.add(randomString);
                        System.out.println("Added " + randomString);
                        list.notifyAll();                         
                    } catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    static class Consumer implements Runnable {

        @Override
        public void run() {
            while (runningConsumer) {
                synchronized (list) {
                    while (list.isEmpty()) {
                        System.out.println("Queue is empty consumer is waiting...");
                        try {
                            list.wait();
                        } catch(InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                
                synchronized (list) {
                    try {
                        Thread.sleep(1000);
                        String randomString = list.poll();
                        System.out.println("Random string retrieved " + randomString);
                        list.notifyAll();
                    } catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    
                }
            }
        }
    }
}
