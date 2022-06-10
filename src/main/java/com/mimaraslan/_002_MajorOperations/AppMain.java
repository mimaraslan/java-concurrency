package com.mimaraslan._002_MajorOperations;

public class RunnableDemo implements Runnable {

    private Thread myThread;
    private String myThreadName;
            boolean suspended = false;

    public RunnableDemo(String myThreadName) {
        this.myThreadName = myThreadName;
        System.out.println("Creating " + myThreadName);
    }

    @Override
    public void run() {
        System.out.println("Running " + myThreadName);

        try {
            for (int i = 10; i > 0; i--) {
                System.out.println("Thread: " + myThreadName + ", " + i);

                Thread.sleep(300);

                synchronized (this){
                    while (suspended){
                        wait();
                    }
                }

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

    void suspend(){
        suspended = true;
    }

    synchronized void resume(){
        suspended = false;
        notify();
    }

    public static void main(String[] args) {
        RunnableDemo obj1 = new RunnableDemo("A\t");
        obj1.start();

        RunnableDemo obj2 = new RunnableDemo("B\t");
        obj2.start();

        try{
            Thread.sleep(1000);
            obj1.suspend();
            System.out.println("Suspending thread A");

            Thread.sleep(1000);
            obj1.resume();
            System.out.println("Resuming thread A");


            obj2.suspend();
            System.out.println("Suspending thread B");

            Thread.sleep(1000);
            obj2.resume();
            System.out.println("Resuming thread B");

        }catch (InterruptedException e){
            System.out.println("Main thread Interrupted");
        }

        try {
            System.out.println("Waiting for threads to finish.");
            obj1.myThread.join();
            obj2.myThread.join();
        } catch (InterruptedException e){
            System.out.println("Main thread Interrupted");
        }
        System.out.println("Main thread exiting.");

    }

}