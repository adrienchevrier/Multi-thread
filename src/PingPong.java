
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// The aim of this class is to print "ping" then "pong" in the console
// To do so, the programmer uses a sleep() fonction to make sure that
// the ping() functions runs first (the scheduler choses ping first becausee of the sleep
 //to print "ping", then the pong() function runs
// so that "ping pong" is printed

public class PingPong {

    private boolean sent = false;
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();
    

    private void ping() {
        sent = true;
        System.out.println("ping");
    }

    private void pong() throws InterruptedException {
        while (sent == false) {
            Thread.sleep(100); // since it has to sleep for 100ms, the scheduler choses the ping() function first
        }
        System.out.println("pong");
    }

    public static void main(String[] args) throws InterruptedException {
        PingPong pingPong = new PingPong();
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(2000); // please check at the top of the file
                } catch (InterruptedException e) {
                    new AssertionError(e);
                }
                pingPong.ping();
            }

        }).start();
        pingPong.pong();
    }

}
