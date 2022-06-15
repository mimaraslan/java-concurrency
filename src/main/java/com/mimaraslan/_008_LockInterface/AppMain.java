package com.mimaraslan._008_LockInterface;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class PrintDemo {
    private final Lock queueLock = new ReentrantLock();

    public void print() {
        queueLock.lock();

        try {
            Long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + "  Time Taken " + (duration / 1000) + " seconds.");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.printf("%s printed the document successfully.\n", Thread.currentThread().getName());
            queueLock.unlock();
        }
    }
}

class ThreadDemo extends Thread {
    PrintDemo  printDemo;

    ThreadDemo(String name,  PrintDemo printDemo) {
        super(name);
        this.printDemo = printDemo;
    }

    @Override
    public void run() {
        System.out.printf(
                "%s starts printing a document\n", Thread.currentThread().getName());
        printDemo.print();
    }
}

public class AppMain {

    public static void main(String args[]) {
        PrintDemo printDemo = new PrintDemo();

        ThreadDemo t1 = new ThreadDemo("Thread1 ", printDemo);
        ThreadDemo t2 = new ThreadDemo("Thread2 ", printDemo);
        ThreadDemo t3 = new ThreadDemo("Thread3 ", printDemo);
        ThreadDemo t4 = new ThreadDemo("Thread4 ", printDemo);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}