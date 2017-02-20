package CounterThread;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;



/**
 *
 * @author Nicolas
 */
public class CounterThread implements Runnable {

        //Array list containing the counter values
       private ArrayList<Integer> counter = new ArrayList<>(4);

    public CounterThread(ArrayList<Integer> counter) {
        this.counter = counter;
    }

    // we implement Runnable.
    //Calls the method adding counted values
        @Override
        public void run() {
            add_value();
        
        // We observe that each thread doesn't run to the end in a single raw
        // For example some threads stop at 780 value, and another one is scheduled
        // The different threads run for a while, stop, let other threads run
            // and restart where they stopped after a while.

        /*
        When we try to make different threads on a single shared resource(an array list)
        We have a ConcurrentModificationException because different threads try to modify a resource
        at the same time. This can ba handled with a synchronized function
         */
    }

    /*
    Add values to the array list
     */
    private synchronized void add_value(){
        int val ;
        Thread thread = Thread.currentThread();
        for (val = 1; val <1001; val++) {
            counter.add(val);
            System.out.println("cpt value:" + val + "thread ID:" + thread.getId());

        }
        System.out.println(counter);
    }
    
}
