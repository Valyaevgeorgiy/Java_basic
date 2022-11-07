package org.example;

public class Main_2 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("The different states of a thread are:");

        for (Thread.State state: Thread.State.values()) {
            System.out.print(state + " ");
        }

        System.out.print("\nLet's get practical:\n");
        MyThread t = new MyThread("Thread States");
        t.start();

        //wait 500 milliseconds for the thread to die
        Thread.sleep(500);
        t.printState();

        t.join(3000);
        t.printState();

        //Notify thread to wake up
        t.threadNotify();
        t.printState();

        //wait forever for the thread to die
        Thread.sleep(10);
        Thread.currentThread().interrupt();
        t.printState();
    }
}
