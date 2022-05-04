package com.texnedo.architecture.threading;

import java.util.Scanner;

public class OnesAndZerosPrintMultithreaded {
    private static volatile boolean shouldExit = false;
    private static int lastValue = -1;
    private static final Object lastValueGuard = new Object();
    private static boolean shouldPrintOneSignaled = false;
    private static boolean shouldPrintZeroSignaled = false;

    public static void main(String[] args) throws InterruptedException {
        useEventBasedWaiting();
    }

    public static void useEventBasedWaiting() throws InterruptedException {
        final Object shouldPrintOne = new Object();
        final Object shouldPrintZero = new Object();
        final Thread oneWriter = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!shouldExit) {
                    synchronized (shouldPrintOne) {
                        if (shouldExit) {
                            break;
                        }
                        if (!shouldPrintOneSignaled) {
                            try {
                                shouldPrintOne.wait();
                            } catch (InterruptedException ex) {
                                break;
                            }
                        }
                        if (shouldExit) {
                            break;
                        }
                        if (!shouldPrintOneSignaled) {
                            continue;
                        }
                        System.out.print(1);
                        shouldPrintOneSignaled = false;
                        synchronized (shouldPrintZero) {
                            shouldPrintZeroSignaled = true;
                            shouldPrintZero.notify();
                        }
                    }
                }
            }
        });
        final Thread zeroWriter = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!shouldExit) {
                    synchronized (shouldPrintZero) {
                        if (shouldExit) {
                            break;
                        }
                        if (!shouldPrintZeroSignaled) {
                            try {
                                shouldPrintZero.wait();
                            } catch (InterruptedException ex) {
                                break;
                            }
                        }
                        if (shouldExit) {
                            break;
                        }
                        if (!shouldPrintZeroSignaled) {
                            continue;
                        }
                        System.out.print(0);
                        shouldPrintZeroSignaled = false;
                        synchronized (shouldPrintOne) {
                            shouldPrintOneSignaled = true;
                            shouldPrintOne.notify();
                        }
                    }
                }
            }
        });
        oneWriter.start();
        zeroWriter.start();
        synchronized (shouldPrintZero) {
            shouldPrintZeroSignaled = true;
            shouldPrintZero.notify();
        }
        final Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        shouldExit = true;
        synchronized (shouldPrintZero) {
            shouldPrintZeroSignaled = false;
            shouldPrintZero.notify();
        }
        synchronized (shouldPrintOne) {
            shouldPrintOneSignaled = false;
            shouldPrintOne.notify();
        }
        oneWriter.join();
        zeroWriter.join();
    }

    public static void useSharedVariableState() throws InterruptedException {
        final Thread oneWriter = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!shouldExit) {
                    synchronized (lastValueGuard) {
                        if (shouldExit) {
                            break;
                        }
                        if (lastValue == 0) {
                            System.out.print(1);
                            lastValue = 1;
                        }
                    }
                }
            }
        });
        final Thread zeroWriter = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!shouldExit) {
                    synchronized (lastValueGuard) {
                        if (shouldExit) {
                            break;
                        }
                        if (lastValue == 1 || lastValue == -1) {
                            System.out.print(0);
                            lastValue = 0;
                        }
                    }
                }
            }
        });
        oneWriter.start();
        zeroWriter.start();
        final Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        shouldExit = true;
        oneWriter.join();
        zeroWriter.join();
    }
}
