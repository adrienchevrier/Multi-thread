package Mains;

import CounterThread.CounterThread;

public class Main {

    public static void main(String[] args) {

        CounterThread t1 = new CounterThread();
        CounterThread t2 = new CounterThread();
        CounterThread t3 = new CounterThread();
        CounterThread t4 = new CounterThread();

        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread (t2);
        Thread thread3 = new Thread (t3);
        Thread thread4 = new Thread (t4);
        // each of the thread calls the run method by doing thread.start()

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            System.out.println("Program finishes");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };
}
