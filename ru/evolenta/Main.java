package ru.evolenta;

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                IntStream.rangeClosed(10, 20).forEach(System.out::print);
            }
        };

        new Thread(runnable).start();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        IntStream.range(0, 10).forEach(System.out::print);
    }
}
