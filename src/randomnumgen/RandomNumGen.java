package randomnumgen;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by adrien on 25/02/17.
 * Make the given code thread safe using an AtomicLong
 */
public class RandomNumGen {
    private long x;
    private AtomicLong atomiX;

    /*Thread calling next function*/
    class AtomicThread implements Runnable{
        RandomNumGen rng = new RandomNumGen(System.currentTimeMillis());
        /*run prints result of AtomicNext function*/
        @Override
        public void run() {
            System.out.println(rng.atomicNext());
        }//print number
    }

    /*CONSTRUCTOR*/
    public RandomNumGen(long seed) {
        if (seed == 0) {
            throw new IllegalArgumentException("seed == 0");
        }
        atomiX = new AtomicLong(seed);
    }
    public RandomNumGen(){}


    /*calculates next atomiX*/
    public long atomicNext(){   // Marsaglia's XorShift
        long prev;
        long next;
        //Loop while atomiX changes during computation of next value
        do {
            prev = atomiX.get();
            next = prev;
            next ^= next >>> 12;
            next ^= next << 25;
            next ^= next >>> 27;
        }while (!atomiX.compareAndSet(prev,next));
        return next*2685821657736338717L;// return number
    }

    public static void main(String[] args) throws InterruptedException {
        //create threads
        for(int i = 0; i < 1000; i++) {
            new Thread(new RandomNumGen().new AtomicThread()).start();
            Thread.sleep(5);
        }
    }
}
