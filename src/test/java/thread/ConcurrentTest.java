/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-2-27
 * description: 
 */

package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ConcurrentTest {
    private static int THREAD_NUM = 20;
    private static int CLIENT_NUM = 50;
    
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        
        final Semaphore semaphore = new Semaphore(THREAD_NUM);
        for(int index = 0;index<CLIENT_NUM;index++) {
            final int NO = index;
            Runnable run = new Runnable(){

                @Override
                public void run() {
                    try{
                        semaphore.acquire();
                        System.out.println("Thread:" + NO);  
                        semaphore.release();
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            executor.execute(run);
        }
        executor.shutdown();
    }
}
