package dinningPhilo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by adrien on 20/02/17.
 * Dinning philosophers problem
 */
public class PhilosopherDinner {

    private final ReentrantLock[] forks;
//OK
    public PhilosopherDinner(int forkCount) {
        ReentrantLock[] forks = new ReentrantLock[forkCount];
        for (int i = 0; i < forkCount; i++) {
            forks[i] = new ReentrantLock();
        }
        this.forks = forks;
    }

    public void eat(int index) {
        //get mutex for each fork of 1 philosopher
        ReentrantLock fork1 = forks[index];
        ReentrantLock fork2 = forks[(index + 1) % forks.length];
            //get fork with lower number first
            if (index<((index + 1) % forks.length)){
                fork1.lock();
                fork2.lock();
            }else {
                fork2.lock();
                fork1.lock();
            }
            // if all forks are taken, eat and release
            if (fork1.isLocked() && fork2.isLocked()) {
                try {
                    System.out.println("philosopher " + index + " eat");
                } finally {
                    fork1.unlock();
                    fork2.unlock();
                }
            }
    }
    /*
    When philosophers are eating after a while,
    a philosopher may lock a fork and wait for the next one to be available,
    it is possible that the 1st fork a philosopher A is waiting for, is the 2nd fork for a philosopher B,
    in this case when the philosopher eating releases its fork, philosopher A will not take it because
    he needs another fork first that is held by philosopher B until he has another fork and so on.
    So the released fork will be given back to the eating philosopher which will eat infinitely.

    A way to solve this problem is to make the philosophers always take the fork with lowest index first.
    In that case Each philosopher will take 1 fork except the last one, he waits for the fork with lowest index
     which is held by the first philosopher. Therefore the last philosopher will take no fork, the fork with highest
     index is now available for the 4th philosopher which can eat. After eating he releases his forks and the previous
     philosopher can eat.
     */
    public static void main(String[] args) {
        PhilosopherDinner dinner = new PhilosopherDinner(5);
        for (int i = 0; i < 5; i++) {
            final int philosopher = i;
            new Thread(new Runnable() {

                @Override
                public void run() {
                    for (; ; ) {
                        dinner.eat(philosopher);
                    }
                }

            }).start();
        }
    }
}
