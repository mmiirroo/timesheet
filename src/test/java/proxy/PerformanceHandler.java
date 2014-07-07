/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-7-2
 */

package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PerformanceHandler implements InvocationHandler {
    private Object target;
    
    public PerformanceHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke begin:"+target.getClass().getName()+"."+method.getName());
        Object obj = method.invoke(target, args);
        System.out.println("invoke end:"+target.getClass().getName()+"."+method.getName());
        return obj;
    }
}
