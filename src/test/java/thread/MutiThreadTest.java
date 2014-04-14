/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-4-1
 * description: 
 */

package thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MutiThreadTest {
    static ApplicationContext context = null;
    static String[] path = new String[] { "/persistence-beans.xml", "/controllers.xml" };
    static Map<String, String> countMap = new HashMap<String, String>();
    static Map<String, String> countMap2 = new HashMap<String, String>();
    static Set<String> countSet = new HashSet<String>();
    static List<String> list = new ArrayList<String>();

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext(path);
    }

    @After
    public void tearDown() throws Exception {
        context = null;
    }

    /**
     * JUNIT会运行这个方法，是主线程
     */
    @Test
    public void testThreadJunit() throws Throwable {
        //TestRunnable，实例化自定义的7个线程  
        TestRunnable tr1 = new ThreadA();
        TestRunnable tr2 = new ThreadB();
        TestRunnable tr3 = new ThreadC();
        TestRunnable tr4 = new ThreadD();
        TestRunnable tr5 = new ThreadE();
        TestRunnable tr6 = new ThreadF();
        TestRunnable tr7 = new ThreadG();
        //必须声明为一个数组，把该数组当参数传递给 MultiThreadedTestRunner  
        TestRunnable[] trs = { tr1, tr2, tr3, tr4, tr5, tr6, tr7 };
        //不需改动  
        MultiThreadedTestRunner mttr = new MultiThreadedTestRunner(trs);
        //执行MTTR和7线程  
        mttr.runTestRunnables();
    }

    /**
     * 要运行多线程，首先要实现自定义的线程</br> 如下我定义了A,B,C,D,E,F,G七个线程</br>
     * 注意:自定义线程必须要继承TestRunnable</br> 并且覆盖runTest()方法
     * 
     */
    private class ThreadA extends TestRunnable {
        @Override
        public void runTest() throws Throwable {
            //线程要调用的方法或者要执行的操作  
            myCommMethod2();
        }
    }

    private class ThreadB extends TestRunnable {
        @Override
        public void runTest() throws Throwable {
            myCommMethod2();
        }
    }

    private class ThreadC extends TestRunnable {
        @Override
        public void runTest() throws Throwable {
            myCommMethod2();
        }
    }

    private class ThreadD extends TestRunnable {
        @Override
        public void runTest() throws Throwable {
            myCommMethod2();
        }
    }

    private class ThreadE extends TestRunnable {
        @Override
        public void runTest() throws Throwable {
            myCommMethod2();
        }
    }

    private class ThreadF extends TestRunnable {
        @Override
        public void runTest() throws Throwable {
            myCommMethod2();
        }
    }

    private class ThreadG extends TestRunnable {
        @Override
        public void runTest() throws Throwable {
            myCommMethod2();
        }
    }

    /**
     * 线程要调用的方法。在此方法中</br> 实现你的多线程代码测试。
     * 
     * @throws Exception
     */
    public void myCommMethod2() throws Exception {
        System.out.println("线程===" + Thread.currentThread().getId() + "执行myCommMethod2操作开始");
        for (int i = 0; i < 10; i++) {
            int a = i * 5;
            System.out.println(a);
        }
        System.out.println("线程===" + Thread.currentThread().getId() + "执行myCommMethod2操作结束");
    }
}
