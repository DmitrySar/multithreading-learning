package ru.evolenta;

import java.util.stream.IntStream;

//volatile
class VolatileExample {
    volatile int x = 0;
    public static final int THREAD_COUNT = 100;

    public void increaseX() {
        x = x + 1;
    }

    public static void main(String[] args) {
        final VolatileExample counter = new VolatileExample();
        IntStream.range(0, THREAD_COUNT).forEach(i -> new Thread(counter::increaseX).start());
        while (counter.x < THREAD_COUNT) {
        } // busy wait
        System.out.println(counter.x);
    }
}

public class VolatileDemo {

    //synchronized
    private static volatile int count = 2_000_000;

    public static void main(String[] args) throws InterruptedException {
        Runnable decrement = () -> IntStream.range(0, 2_000_000).forEach(n -> decrement());
        Runnable increment = () -> IntStream.range(0, 2_000_000).forEach(n -> increment());

        Thread th1 = new Thread(decrement);
        Thread th2 = new Thread(increment);
        th1.start();
        th2.start();
        th1.join();
        th2.join();

        System.out.println(count);

    }

    private static synchronized void increment() {
        count++;
    }

    private static synchronized void decrement() {
        count--;
    }
}
