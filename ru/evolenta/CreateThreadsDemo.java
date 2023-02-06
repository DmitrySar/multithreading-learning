package ru.evolenta;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class CreateThreadsDemo {
    public static void main(String[] args) {
        //создание потоков
        Runnable worker = () -> IntStream.rangeClosed(1, 10).forEach(CreateThreadsDemo::sleepThread);
        List<Thread> threads = new ArrayList<>();
        IntStream.rangeClosed(1, 10).forEach(n -> threads.add(new Thread(worker)));
        threads.forEach(Thread::start);
        threads.forEach(CreateThreadsDemo::joinThread);
        System.out.println("finish");
    }

    private static void sleepThread(Integer n) {
        System.out.print(Thread.currentThread().getName() + " " + n + "\n");
        try {
            //Thread.sleep(500);
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void joinThread(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
