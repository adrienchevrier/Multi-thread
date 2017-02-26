
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// The aim of this class is to print "ping" then "pong" in the console
// To do so, the programmer uses a sleep() fonction to make sure that
// the ping() functions runs first (the scheduler choses ping first becausee of the sleep
 //to print "ping", then the pong() function runs
// so that "ping pong" is printed

public class PingPong {

    int count = 0;
    final Lock lock = new ReentrantLock();
   // final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    private void ping() {
        lock.lock();
        try {
        
        System.out.println("ping");
        count = 1;
        }
        
        finally {
            
           lock.unlock();
           
        }      
       }       
    

    private void pong() throws InterruptedException {
       lock.lock();
        try {
        while ( count !=1) {
        //    Thread.sleep(100); // since it has to sleep for 100ms, the scheduler choses the ping() function first
        notEmpty.await();
        
        }
        notEmpty.signal();
        
        // 
        System.out.println("pong");
       
        }    
        finally {
        //System.out.println("pong");
       lock.unlock();
        }
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
        System.out.println("coucou");
        pingPong.pong();
    }

}
