package ru.evolenta;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        CompletableFuture<Void> task = CompletableFuture.runAsync(() -> System.out.println("run async"));

        CompletableFuture<String> supplier1 = CompletableFuture.supplyAsync(() -> "return parameter 1");
        CompletableFuture<String> supplier2 = CompletableFuture.supplyAsync(() -> "return parameter 2");

        supplier1
                .thenCombine(supplier2, (s1, s2) -> s1 + "\t" + s2)
                .thenApply(t -> "t: " + t)
                .thenAccept(res -> System.out.println("res: " + res));

    }
}

/*
CompletableFuture - это класс в Java, представляющий собой асинхронный вычислительный процесс, который может вернуть результат.
    Вот наиболее часто используемые методы в CompletableFuture:

runAsync: выполняет задачу асинхронно, без возврата результата.

supplyAsync: выполняет задачу асинхронно и возвращает CompletableFuture, который можно использовать для получения результата задачи.

thenApply: регистрирует функцию, которая будет выполнена после завершения вычислений, представленных CompletableFuture.
    Функция принимает результат вычислений в качестве входных данных и возвращает новый результат.

thenAccept: регистрирует функцию, которая будет выполнена после завершения вычислений, представленных CompletableFuture.
    Функция принимает результат вычислений в качестве входных данных, но не возвращает никакого результата.
    Этот метод полезен, когда необходимо выполнить какую-то действие после завершения вычислений, но не нужно возвращать новый результат.

thenCombine: позволяет объединить результаты двух CompletableFuture, выполненных независимо, используя заданную функцию.

thenRun: регистрирует функцию, которая будет выполнена после завершения вычислений, представленных CompletableFuture.
    Функция не принимает входные данные и не возвращает результат.

exceptionally: позволяет обрабатывать исключения, возникающие в процессе вычислений, используя заданную функцию.
    Это полезно, когда необходимо выполнить какие-то действия в случае исключения, вместо того, чтобы пробрасывать исключение дальше.
 */