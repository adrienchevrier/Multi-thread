package dinningPhilo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by adrien on 20/02/17.
 * Dinning philosophers problem
 */
public class PhilosopherDinner {

    private final ReentrantLock[] forks;

    public PhilosopherDinner(int forkCount) {
        ReentrantLock[] forks = new ReentrantLock[forkCount];
        for (int i = 0; i < forkCount; i++) {
            forks[i] = new ReentrantLock();
        }
        this.forks = forks;
    }

    public void eat(int index) {
        ReentrantLock fork1 = forks[index];
        ReentrantLock fork2 = forks[(index + 1) % forks.length];
            if (fork1.tryLock()){
                fork2.lock();
            }

            fork2.tryLock();
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
    When we philosophers are eating after a while,
    a philosopher may lock a fork and wait for the next one to be available,
    it is possible that the 1st fork a philosopher A is waiting for, is the 2nd fork for a philosopher B,
    in this case when the philosopher eating releases its fork, philosopher A will not take it because
    he needs another fork first that is held by philospher B until he has another fork and so on.
    So the released fork will be given back to the eating philosopher which will eat infinitely.

    A way to solve this problem is to make the philosophers don't wait for a 1st fork to be available, and after
    getting it wait for another fork to be available.
    This can be implemented with "if (fork1.tryLock()){"
    instead of "fork2.lock();"
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
