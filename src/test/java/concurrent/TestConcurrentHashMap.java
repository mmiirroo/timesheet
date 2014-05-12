/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-5-12
 */

package concurrent;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class TestConcurrentHashMap {

    @Test
    public void testSegmentFor(){
        ConcurrentHashMap<Integer, String> concurrentMap = new ConcurrentHashMap<Integer, String>(100,0.8F,32);
        concurrentMap.put(33, "test");
        System.out.println((8589934591L >>> 27) & 31);
        System.out.println(8589934591L % 32 );
        System.out.println(8589934591L/134217728 & 31);

    }
}
