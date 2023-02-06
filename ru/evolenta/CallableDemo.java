package ru.evolenta;

import java.util.Random;
import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //ExecutorService executor = Executors.newSingleThreadExecutor();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        calculateMeasure(executor);

    }

    private static void calculateMeasure(ExecutorService executor) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        calculate(executor);
        long stop = System.currentTimeMillis();
        System.out.println("calculate time: " + (stop - start));
    }

    private static void calculate(ExecutorService executor) throws InterruptedException, ExecutionException {
        Callable<Integer> task = () -> {
            TimeUnit.SECONDS.sleep(3);
            return new Random().nextInt(10);
        };

        Future<Integer> future1 = executor.submit(task);
        Future<Integer> future2 = executor.submit(task);

        //System.out.println(future1.isDone());

        System.out.println(future1.get() + " : " + future2.get());

        executor.shutdown();
    }
}
