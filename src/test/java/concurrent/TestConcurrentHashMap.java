/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-5-12
 */

package concurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;

public class TestConcurrentHashMap {

    @Test
    public void testSegmentFor() {
        ConcurrentHashMap<Integer, String> concurrentMap = new ConcurrentHashMap<Integer, String>(100, 0.8F, 32);
        concurrentMap.put(33, "test");
        System.out.println((8589934591L >>> 27) & 31);
        System.out.println(8589934591L % 32);
        System.out.println(8589934591L / 134217728 & 31);

    }

    @Test
    public void testConcurrentModificationException() {
        List<String> myList = new ArrayList<String>();
        myList.add("1");
        myList.add("2");
        myList.add("3");
        myList.add("4");
        myList.add("5");

        Iterator<String> it = myList.iterator();
        while (it.hasNext()) {
            String value = it.next();
            System.out.println("List Value:" + value);
            if (value.equals("3"))
                myList.remove(value);
        }

        Map<String, String> myMap = new HashMap<String, String>();
        myMap.put("1", "1");
        myMap.put("2", "2");
        myMap.put("3", "3");

        Iterator<String> it1 = myMap.keySet().iterator();
        while (it1.hasNext()) {
            String key = it1.next();
            System.out.println("Map Value:" + myMap.get(key));
            if (key.equals("2")) {
                myMap.put("1", "4");
                // myMap.put("4", "4");
            }
        }

    }

    @Test
    public void testCopyOnWriteArrayList() {
        List<String> myList = new CopyOnWriteArrayList<String>();
        myList.add("1");
        myList.add("2");
        myList.add("3");
        myList.add("4");
        myList.add("5");

        Iterator<String> it = myList.iterator();
        while (it.hasNext()) {
            String value = it.next();
            System.out.println("List Value:" + value);
            if (value.equals("3"))
                myList.remove(value);
        }

        Map<String, String> myMap = new HashMap<String, String>();
        myMap.put("1", "1");
        myMap.put("2", "2");
        myMap.put("3", "3");

        Iterator<String> it1 = myMap.keySet().iterator();
        while (it1.hasNext()) {
            String key = it1.next();
            System.out.println("Map Value:" + myMap.get(key));
            if (key.equals("2")) {
                myMap.put("1", "4");
                // myMap.put("4", "4");
            }
        }

    }
    
    @Test
    public void testShift(){
        int sshift = 0; 
        int ssize = 1; 
        while(ssize < 3) { 
            ++sshift; 
            ssize <<= 1; 
        } 
        System.out.println("ssize:"+ssize);
        System.out.println("sshift:"+sshift);      
    }
}
