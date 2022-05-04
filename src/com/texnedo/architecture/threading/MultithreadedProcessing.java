package com.texnedo.architecture.threading;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MultithreadedProcessing {
    private static final class Task {
        final long created;
        final int weight;
        final String message;

        Task(int weight, String message) {
            this.created = System.nanoTime();
            this.weight = weight;
            this.message = message;
        }

        @Override
        public String toString() {
            return "Task{" +
                    "created=" + created +
                    ", weight=" + weight +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

    private static class ProcessingContext {
        final ConcurrentLinkedQueue<Task> queue = new ConcurrentLinkedQueue<>();
        final Object exitNotify = new Object();
        private volatile boolean shouldExit = false;
        private final LinkedList<Integer> queueSizeWindow = new LinkedList<>();
        private int queueSizeWindowSum = 0;
        private int maxQueueSize = 0;
        private double maxAvgQueueSize = 0;

        void collectStats() throws InterruptedException {
            while (!shouldExit) {
                synchronized (this) {
                    int currentCount = queue.size();
                    queueSizeWindow.add(currentCount);
                    queueSizeWindowSum += currentCount;
                    if (queueSizeWindow.size() > 50) {
                        int oldest = queueSizeWindow.removeFirst();
                        queueSizeWindowSum -= oldest;
                    }
                    if (maxQueueSize < currentCount) {
                        maxQueueSize = currentCount;
                    }
                    double average = ((double) queueSizeWindowSum) / queueSizeWindow.size();
                    if (maxAvgQueueSize < average) {
                        maxAvgQueueSize = average;
                    }
                }
                synchronized (exitNotify) {
                    exitNotify.wait(100);
                }
            }
            System.out.println("Collect stats exited");
        }

        void printStats() throws InterruptedException {
            while (!shouldExit) {
                synchronized (this) {
                    double average = ((double) queueSizeWindowSum) / queueSizeWindow.size();
                    System.out.println(String.format("Processing queue avg size: %f, max avg size: %f, max size: %d",
                            average, maxAvgQueueSize, maxQueueSize));
                }
                synchronized (exitNotify) {
                    exitNotify.wait(2000);
                }
            }
            System.out.println("Print stats exited");
        }

        void stopProcessing() {
            shouldExit = true;
            synchronized (exitNotify) {
                exitNotify.notifyAll();
            }
        }

        boolean shouldExit() {
            return shouldExit;
        }
    }

    private static class Processor {
        private final long id;
        private final ProcessingContext context;

        private Processor(long id, ProcessingContext context) {
            this.id = id;
            this.context = context;
        }

        void process() throws InterruptedException {
            while (!context.shouldExit()) {
                final Task current = context.queue.poll();
                if (current == null) {
                    synchronized (context.exitNotify) {
                        context.exitNotify.wait(100);
                    }
                } else {
                    System.out.println(String.format("Processor %d started task %s", id, current));
                    Thread.sleep(current.weight);
                    System.out.println(String.format("Processor %d completed task %s", id, current));
                }
            }
            System.out.println(String.format("Processor %d exited", id));
        }
    }

    private static class Requester {
        private static final Random rnd = new Random();
        private static AtomicInteger counter = new AtomicInteger(0);
        private final ProcessingContext context;

        private Requester(ProcessingContext context) {
            this.context = context;
        }

        void request(int count) throws InterruptedException {
            if (context.shouldExit()) {
                return;
            }
            for (int i = 0; i < count; i++) {
                final int weight = rnd.nextInt(1000);
                final Task task = new Task(
                        weight,
                        String.format("Task#%d", counter.incrementAndGet()
                        )
                );
                Thread.sleep(weight % 10);
                if (context.shouldExit()) {
                    return;
                }
                context.queue.add(task);
            }
        }
    }

    public static void main(String[] ars) throws IOException, InterruptedException {
        final ProcessingContext context = new ProcessingContext();
        final ThreadPoolExecutor processing = new ThreadPoolExecutor(
                    100,
                100,
                1000,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100)
        );
        processing.prestartAllCoreThreads();
        for (int i = 0; i < processing.getCorePoolSize(); i++) {
            processing.submit(() -> {
                Processor processor = new Processor(Thread.currentThread().getId(), context);
                try {
                    processor.process();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        final ThreadPoolExecutor requesting = new ThreadPoolExecutor(
                2,
                2,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100)
        );
        requesting.prestartAllCoreThreads();
        final ThreadPoolExecutor watching = new ThreadPoolExecutor(
                2,
                2,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100)
        );
        watching.prestartAllCoreThreads();
        watching.submit(() -> {
            try {
                context.collectStats();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        watching.submit(() -> {
            try {
                context.printStats();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        final Scanner scanner = new Scanner(System.in);
        while (true) {
            int count;
            try {
                count = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                count = -1;
            }
            if (count < 0) {
                System.out.println("Termination started");
                context.stopProcessing();
                requesting.shutdown();
                processing.shutdown();
                watching.shutdown();
                requesting.awaitTermination(10, TimeUnit.SECONDS);
                processing.awaitTermination(10, TimeUnit.SECONDS);
                watching.awaitTermination(10, TimeUnit.SECONDS);
                System.out.println("Termination completed");
                break;
            } else {
                final Requester requester = new Requester(context);
                final int localCount = count;
                requesting.submit(() -> {
                    try {
                        requester.request(localCount);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }
}
