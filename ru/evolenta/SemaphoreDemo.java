package ru.evolenta;

import java.util.List;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        // Create a Semaphore with two permits to issue.
        Semaphore semaphore = new Semaphore(2, true);
        /*
            The public void acquire() method will obtain a permit and reduce the number of available permits by one.
            If there are no permits left, the calling thread will be blocked.

            If a permit is available, the public boolean tryAcquire() method acquires a permit and decrements
            the counter similar to acquire(). Then, it returns true. Otherwise, no permit is obtained,
            and false is returned.
         */
        semaphore.acquire();
        System.out.println("+++++++++++++++");
        /*
            A thread calling the release() function willingly gives up its access to the shared resource while
            incrementing the counter of the Semaphore by one
         */
        semaphore.release();

        Runnable task = () -> {
            try {
                semaphore.acquire();
                System.out.println("Executing task by thread: " + Thread.currentThread().getName());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        };

        List<Thread> threads = List.of(new Thread(task), new Thread(task), new Thread(task));
        threads.forEach(Thread::start);
        threads.forEach(SemaphoreDemo::joinThread);

    }

    private static void joinThread(Thread t) {
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
