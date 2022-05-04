package com.texnedo;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.IntConsumer;

public class FizzBuzzMultithreaded {
    private final int n;
    private int index = 1;
    private final Object sync = new Object();

    public FizzBuzzMultithreaded(int n) {
        this.n = n;
    }

    enum Operation {
        FIZZ,
        BUZZ,
        FIZZBUZZ,
        NUMBER,
        NO_OP
    }

    private Operation getNextCurrentOperation() {
        if (n < 0) {
            return Operation.NO_OP;
        }
        if (index > n) {
            return Operation.NO_OP;
        }
        Operation operation;
        if (index % 3 == 0 && index % 5 == 0) {
            operation = Operation.FIZZBUZZ;
        } else if (index % 3 == 0) {
            operation = Operation.FIZZ;
        } else if (index % 5 == 0) {
            operation = Operation.BUZZ;
        } else {
            operation = Operation.NUMBER;
        }
        return operation;
    }

    private void moveNext() {
        index++;
    }

    public boolean completed() {
        synchronized (sync) {
            return getNextCurrentOperation() == Operation.NO_OP;
        }
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        synchronized (sync) {
            if (getNextCurrentOperation() == Operation.FIZZ) {
                printFizz.run();
                moveNext();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        synchronized (sync) {
            if (getNextCurrentOperation() == Operation.BUZZ) {
                printBuzz.run();
                moveNext();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        synchronized (sync) {
            if (getNextCurrentOperation() == Operation.FIZZBUZZ) {
                printFizzBuzz.run();
                moveNext();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        synchronized (sync) {
            if (getNextCurrentOperation() == Operation.NUMBER) {
                printNumber.accept(index);
                moveNext();
            }
        }
    }

    public static void main(String[] args) {
        final FizzBuzzMultithreaded fizzBuzz = new FizzBuzzMultithreaded(15);
        Thread[] threads = new Thread[4];
        threads[0] = new Thread(() -> {
            while (!fizzBuzz.completed()) {
                try {
                    fizzBuzz.buzz(() -> System.out.print("Buzz "));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Completed " + threads[0].getName());
        });
        threads[1] = new Thread(() -> {
            while (!fizzBuzz.completed()) {
                try {
                    fizzBuzz.fizz(() -> System.out.print("Fizz "));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Completed " + threads[1].getName());
        });
        threads[2] = new Thread(() -> {
            while (!fizzBuzz.completed()) {
                try {
                    fizzBuzz.fizzbuzz(() -> System.out.print("FizzBuzz "));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Completed " + threads[2].getName());
        });
        threads[3] = new Thread(() -> {
            while (!fizzBuzz.completed()) {
                try {
                    fizzBuzz.number(value -> {
                        System.out.print(value);
                        System.out.print(" ");
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Completed " + threads[3].getName());
        });
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }
}
