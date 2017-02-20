/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

/**
 *
 * @author Nicolas
 */
public class CounterThread {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
        Runnable runnable = new Runnable () {
            
            // we implement Runnable. 
            @Override
            public void run() {
                //int getpid();
                
                for (int cpt = 1; cpt <1001; cpt++)
                    
                System.out.println("cpt value:" +cpt + "thread ID:");
            }
        };
        
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread (runnable);
        Thread thread3 = new Thread (runnable);
        Thread thread4 = new Thread (runnable);
        
        // each of the thread calls the run method by doing thread.start()
        
        thread1.start(); 
        thread2.start();
        thread3.start();
        thread4.start();
        
        // We observe that each thread doesn't run to the end 
        // like for example some threads stop at 780 value, and another one is scheduled
        // so we think the program doesn't do what it has to since threads stop eachother before the end of each
        
    }
    
}
