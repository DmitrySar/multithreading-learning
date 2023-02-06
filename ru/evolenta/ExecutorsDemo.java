package ru.evolenta;

import java.util.concurrent.*;

public class ExecutorsDemo {
    //executor.svg
    //Creating executors
    private static final ExecutorService executor = Executors
            .newFixedThreadPool(4);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //Submitting tasks
        executor.submit(() -> System.out.println(Thread.currentThread().getName()));

        /*
            Stopping executors
            There are two methods for stopping executors:
            void shutdown() waits until all running tasks are completed and prohibits submitting of new tasks;
            List<Runnable> shutdownNow() immediately stops all running tasks and returns a list of the tasks that were awaiting execution.
            Note that shutdown() does not block the current thread unlike join() of Thread. If you need to wait until the execution is complete, you can invoke awaitTermination(...) with the specified waiting time.
         */

        executor.shutdown();

        boolean terminated = executor.awaitTermination(60, TimeUnit.MILLISECONDS);

        if (terminated) {
            System.out.println("The executor was successfully stopped");
        } else {
            System.out.println("Timeout elapsed before termination");
        }

    }
}
