package ru.evolenta;

import java.util.stream.LongStream;

public class ParallelStreamDemo {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long result = LongStream
                .range(1, 1_000)
                .parallel()
                .filter(n -> n % 3 == 0)
                .reduce((a, b) -> (long) (Math.log(a) + Math.log(b)))
                .getAsLong();
        long stop = System.currentTimeMillis();
        System.out.println("result: " + result);
        System.out.println("calculate time: " + (stop - start) + "ms");
    }
}
