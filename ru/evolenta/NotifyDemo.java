package ru.evolenta;

import java.util.concurrent.atomic.AtomicInteger;

public class NotifyDemo {
    private static final AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Runnable pullSum = () -> {
            synchronized (lock) {
                while (count.get() < 2_000_000) {
                    try {
                        lock.wait();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                count.compareAndSet(2_000_000, 1_000_000);
            }
        };

        Runnable pushSum = () -> {
            synchronized (lock) {
                while (count.incrementAndGet() < 2_000_000) {
                    System.out.print(count.get() + "\r");
                    lock.notifyAll();
                }
            }
        };


        Thread th2 = new Thread(pullSum);
        Thread th1 = new Thread(pushSum);

        th1.setName("push");
        th2.setName("pull");

        //th1.setDaemon(true);
        //th2.setDaemon(true);

        th1.start();
        th2.start();

        th1.join();
        th2.join();

        System.out.println();
        System.out.println(count.get());

    }
}
