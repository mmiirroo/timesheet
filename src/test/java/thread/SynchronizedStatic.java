/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-2-21
 * description: http://blog.csdn.net/bornforit/article/details/6898877
 */

package thread;

public class SynchronizedStatic implements Runnable {  
    static boolean flag = true;  
    public static synchronized void test0() {//同步监视器是该类本身  
        for (int i = 0; i < 1000; i++) {  
            System.out.println("test0: " + Thread.currentThread().getName()  
                    + " " + i);  
        }  
    }  
    public void test1() {  
        synchronized (SynchronizedStatic.class) {//和静态方法具有了相同的同步监视器
//        synchronized (this) {//同步监视器是this，即调用该方法的Java对象。  
            for (int i = 0; i < 1000; i++) {  
                System.out.println("test1: " + Thread.currentThread().getName()  
                        + " " + i);  
            }  
        }  
    }  
    public void run() {  
        if (flag) {  
            flag = false;  
            test0();  
        } else {  
            flag = true;  
            test1();  
        }  
    }  
    public static void main(String args[]) throws InterruptedException {  
        SynchronizedStatic ss = new SynchronizedStatic();  
        new Thread(ss).start();  
        Thread.sleep(1);  
        new Thread(ss).start();  
    }  
}  