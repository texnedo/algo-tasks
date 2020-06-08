package com.texnedo;

public class PrintInOrder {
    static class Foo {
        private int counter = 1;
        private final Object sync = new Object();

        public Foo() {

        }

        public void first(Runnable printFirst) throws InterruptedException {
            synchronized (sync) {
                while (counter != 1) {
                    sync.wait();
                }
                printFirst.run();
                counter = 2;
                sync.notifyAll();
            }
        }

        public void second(Runnable printSecond) throws InterruptedException {
            synchronized (sync) {
                while (counter != 2) {
                    sync.wait();
                }
                printSecond.run();
                counter = 3;
                sync.notifyAll();
            }
        }

        public void third(Runnable printThird) throws InterruptedException {
            synchronized (sync) {
                while (counter != 3) {
                    sync.wait();
                }
                printThird.run();
                counter = 1;
                sync.notifyAll();
            }
        }
    }
}
