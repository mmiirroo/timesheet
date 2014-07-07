/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-7-3
 */

package proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();
    
    public Object getProxy(Class<?> clazz){
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
        
    }
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("begin "+obj.getClass().getName()+"."+method.getName());
        Object result = proxy.invoke(obj, args);
        System.out.println("end "+obj.getClass().getName()+"."+method.getName());
        return result;
    }

}
