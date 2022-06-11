package com.mimaraslan._001_implements_runnable;

public class AppMain implements Runnable {

    private Thread myThread;
    private String myThreadName;

    public AppMain(String myThreadName) {
        this.myThreadName = myThreadName;
        System.out.println("Creating " + myThreadName);
    }

    @Override
    public void run() {
        System.out.println("Running " + myThreadName);

        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Thread: " + myThreadName + ", " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread: " + myThreadName + " interrupted.");
        }

        System.out.println("Thread " + myThreadName + " exiting.");
    }

    public void start() {
        System.out.println("Starting " + myThreadName);

        if (myThread == null) {
            myThread = new Thread(this, myThreadName);
            myThread.start();
        }
    }

    public static void main(String[] args) {
        AppMain obj1 = new AppMain("A\t");
        obj1.start();

        AppMain obj2 = new AppMain("B\t");
        obj2.start();
    }

}