/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-5-5
 */

package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskExample {
    static FutureTask<String> future = new FutureTask<String>(new Callable<String>() {

        @Override
        public String call() throws Exception {
            return getPageContent();
        }
    });

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        new Thread(future).start();
        doOwnThing();
        System.out.println(future.get());
    }

    public static String doOwnThing() {
        return "Do Own Thing";
    }

    public static String getPageContent() {
        return "testPageContent and provide that the operation is a time exhausted thing...";
    }
}
