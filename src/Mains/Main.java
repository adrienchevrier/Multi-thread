package Mains;

import CounterThread.CounterThread;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> counter = new ArrayList<>(4);
        CounterThread t1 = new CounterThread(counter);


        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread (t1);
        Thread thread3 = new Thread (t1);
        Thread thread4 = new Thread (t1);
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
    }
}
