/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-7-4
 */

package thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleThreadLocal {
    private Map<Thread, Object> valueMap = new ConcurrentHashMap<Thread, Object>();
    public void set(Object newValue){
        valueMap.put(Thread.currentThread(), newValue);
    }
    
    public Object get(){
        Thread currentThread = Thread.currentThread();
        Object o = valueMap.get(currentThread);
        if(o==null&&!valueMap.containsKey(currentThread)){
            o=initialValue();
            valueMap.put(currentThread, o);
        }
        return o;
    }

    public void remove(){
        valueMap.remove(Thread.currentThread());
    }
    private Object initialValue() {
        return null;
    }
}
