package ru.evolenta;

import java.util.concurrent.atomic.AtomicLong;

public class InterruptedDemo {

    private static final AtomicLong count = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
        Thread th = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                count.incrementAndGet();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    System.err.println(e.getLocalizedMessage());
                    break;
                }
            }
        });

        th.start();

        Thread.sleep(500);
        th.interrupt();
        System.out.println(count);
    }
}
