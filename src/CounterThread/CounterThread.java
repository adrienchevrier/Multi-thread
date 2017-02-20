package CounterThread;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte0.runnable;

/**
 *
 * @author Nicolas
 */
public class CounterThread implements Runnable {
        //GIIIIIIIIIIIIIIIIIIIIIIIIIIIIT
        // we implement Runnable.
        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            for (int cpt = 1; cpt <1001; cpt++)

                System.out.println("cpt value:" +cpt + "thread ID:" +thread.getId());


        
        // We observe that each thread doesn't run to the end 
        // like for example some threads stop at 780 value, and another one is scheduled
        // so we think the program doesn't do what it has to since threads stop eachother before the end of each
        
    }
    
}
