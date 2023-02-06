package ru.evolenta;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AtomicDemo {

    private static final AtomicInteger count = new AtomicInteger(2_000_000);

    public static void main(String[] args) throws InterruptedException {
        Runnable decrement = () -> IntStream.range(0, 2_000_000).forEach(n -> count.decrementAndGet());
        Runnable increment = () -> IntStream.range(0, 2_000_000).forEach(n -> count.incrementAndGet());

        Thread th1 = new Thread(decrement);
        Thread th2 = new Thread(increment);
        th1.start();
        th2.start();
        th1.join();
        th2.join();

        System.out.println(count);

    }
}
