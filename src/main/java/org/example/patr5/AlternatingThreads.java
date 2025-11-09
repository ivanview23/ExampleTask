package org.example.patr5;

public class AlternatingThreads {
    private static final Object lock = new Object();
    private static boolean isOnesTurn = true;

    static class ThreadFirst implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    while (!isOnesTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.println("1");
                    isOnesTurn = false;
                    lock.notifyAll();
                }
            }
        }
    }

    static class ThreadTwo implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    while (isOnesTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.println("2");
                    isOnesTurn = true;
                    lock.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new ThreadFirst());
        Thread thread2 = new Thread(new ThreadTwo());

        thread1.start();
        thread2.start();
    }
}