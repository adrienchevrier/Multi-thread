package executorService;

import java.util.NavigableMap;
import java.util.concurrent.*;

/**
 * Created by adrien on 25/02/17.
 */
public class Multiplier {

    /*ATTRIBUTES*/
    private final int N;
    private ExecutorService srv;
    private Future[] futures;

    /*CONSTRUCTOR*/
    public Multiplier() {
        N = 20;
        futures = new Future[N];
        srv = Executors.newFixedThreadPool(4);
    }

    /*Multiplies and prints the given value*/
    public int multiply(int n){
        return n*10;
    }

    /*Function handles multiply service*/
    public void executor(){
        for (int i = 0; i < N; i++) {
            int finalI = i;
            /*submit handles multithreading while calling multiply method*/
            futures[i]=srv.submit(new Callable<Integer>() {

                //method performs action when executor submits task
                @Override
                public Integer call() throws Exception {
                    int multi_n = multiply(finalI);// run multiply method
                    System.out.println(multi_n);
                    return multi_n;
                }
            });
        }
    }
    public static void main(String[] args) {
        Multiplier multiplier = new Multiplier();// new multiplier class
        System.out.println("values calculated");
        multiplier.executor();// launch executor

        //print data calculated
        System.out.println("Futures:");
        for (int i = 0; i < multiplier.N; i++) {
            try {
                System.out.println(multiplier.futures[i].get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                System.err.println("IO exception");
            }
            multiplier.srv.shutdown();//shutdown service after printing futures
        }

    }
}
