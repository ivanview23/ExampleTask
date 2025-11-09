package org.example.patr5;

public class DeadLockExample {
    private static final Object lockFirst = new Object();
    private static final Object lockTwo = new Object();

    static class ThreadFirst extends Thread {
        @Override
        public void run() {
            synchronized (lockFirst) {
                System.out.println(getName() + " захватил lockFirst");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lockTwo) {
                    System.out.println(getName() + " захватил lockTwo");
                }
            }
        }
    }

    static class ThreadTwo extends Thread {

        @Override
        public void run() {
            synchronized (lockTwo) {
                System.out.println(getName() + " захватил lockTwo");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lockFirst) {
                    System.out.println(getName() + " захватил lockFirst");
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadFirst thread1 = new ThreadFirst();
        ThreadTwo thread2 = new ThreadTwo();

        thread1.setName("ThreadFirst");
        thread2.setName("ThreadTwo");

        thread1.start();
        thread2.start();
    }
}
