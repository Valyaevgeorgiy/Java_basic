package org.example;

public class MyThread extends Thread {
    private final Object obj;

    public MyThread(String name) {
        setName(name);
        obj = new Object();
    }

    @Override
    public void run() {
        printState();
        try {
            //Make thread sleep for 2000 milliseconds
            sleep(2000, 20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        synchronized (obj) {
            try {
                //make the thread wait until it is notified
                obj.wait();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }

        printState();
    }

    @Override public synchronized void start() {
        printState();
        super.start();
    }

    public void printState() {
        //print the current state of the thread
        System.out.println("Thread name: " + Thread.currentThread().getName());
        System.out.println("Thread States: " + getState());
    }

    public void threadNotify() {
        synchronized (obj) {
            obj.notify();
        }
    }
}
