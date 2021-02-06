package com.mimaraslan._004_Synchronization;

class PrintDemo {
    public void printCount() {
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println("Counter:  "  + i );
            }
        } catch (Exception e) {
            System.out.println("Thread interrupted.");
        }
    }
}

class ThreadDemo extends Thread {
    private Thread myThread;
    private String threadName;
          PrintDemo printDemo;

    ThreadDemo(String threadName, PrintDemo printDemo) {
        this.threadName = threadName;
        this.printDemo = printDemo;
    }

    public void run() {
        synchronized(printDemo) {
            printDemo.printCount();
        }
        System.out.println("Thread " +  threadName + " exiting.");
    }

    public void start () {
        System.out.println("Starting " +  threadName );
        if (myThread == null) {
            myThread = new Thread (this, threadName);
            myThread.start ();
        }
    }
}

public class TestThread {
    public static void main(String args[]) {
        PrintDemo printDemo = new PrintDemo();

        ThreadDemo T1 = new ThreadDemo("A ", printDemo);
        ThreadDemo T2 = new ThreadDemo("B ", printDemo);

        T1.start();
        T2.start();

        // wait for threads to end
        try {
            T1.join();
            T2.join();
        } catch (Exception e) {
            System.out.println("Interrupted");
        }
    }
}
