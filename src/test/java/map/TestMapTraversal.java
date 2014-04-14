/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-4-9
 * description: 
 */

package map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestMapTraversal {

    private static Map<String, String> hashMapSmaller;
    private static Map<String, String> hashMapBigger;
    private static Map<String, String> treeMapSmaller;
    private static Map<String, String> treeMapBigger;
    private Long beginTime;
    private Long endTime;

    @Before
    public void logBeginTime() throws Exception {
    }

    @After
    public  void logEndTime() throws Exception {
    }

    @BeforeClass
    public static void setUp() throws Exception {
 
    }

    @Test
    public void testHashMapSmallerTraversal() {
       initHashMapSmaller();
       
       beginTime = System.currentTimeMillis();
       Iterator<String> iter = hashMapSmaller.keySet().iterator();
       while(iter.hasNext()){
           String key = iter.next();
           hashMapSmaller.get(key);
       }
       endTime = System.currentTimeMillis();
       System.out.println("keyset iterator time consumed:" + (endTime - beginTime));
       
       beginTime = System.currentTimeMillis();
       for(String key:hashMapSmaller.keySet()){
           hashMapSmaller.get(key);
       }
       endTime = System.currentTimeMillis();
       System.out.println("keyset for time consumed:" + (endTime - beginTime));
       
       beginTime = System.currentTimeMillis();
       Iterator<Entry<String,String>> iteratorEntry = hashMapSmaller.entrySet().iterator();
       while(iteratorEntry.hasNext()){
           Entry<String,String> entry = iteratorEntry.next();
           entry.getKey();
           entry.getValue();
       }       
       endTime = System.currentTimeMillis();
       System.out.println("entrySet iterator time consumed:" + (endTime - beginTime));
       
       beginTime = System.currentTimeMillis();
       for(Entry<String,String> entry:hashMapSmaller.entrySet()) {
           entry.getKey();
           entry.getValue();
       }
       endTime = System.currentTimeMillis();
       System.out.println("entrySet for time consumed:" + (endTime - beginTime));
    }

    @Test
    public void testHashMapBiggerTraversal() {
       initHashMapBigger();
       
       beginTime = System.currentTimeMillis();
       Iterator<String> iter = hashMapBigger.keySet().iterator();
       while(iter.hasNext()){
           String key = iter.next();
           hashMapBigger.get(key);
       }
       endTime = System.currentTimeMillis();
       System.out.println("keyset iterator time consumed:" + (endTime - beginTime));
       
       beginTime = System.currentTimeMillis();
       for(String key:hashMapBigger.keySet()){
           hashMapBigger.get(key);
       }
       endTime = System.currentTimeMillis();
       System.out.println("keyset for time consumed:" + (endTime - beginTime));
       
       beginTime = System.currentTimeMillis();
       Iterator<Entry<String,String>> iteratorEntry = hashMapBigger.entrySet().iterator();
       while(iteratorEntry.hasNext()){
           Entry<String,String> entry = iteratorEntry.next();
           entry.getKey();
           entry.getValue();
       }       
       endTime = System.currentTimeMillis();
       System.out.println("entrySet iterator time consumed:" + (endTime - beginTime));
       
       beginTime = System.currentTimeMillis();
       for(Entry<String,String> entry:hashMapBigger.entrySet()) {
           entry.getKey();
           entry.getValue();
       }
       endTime = System.currentTimeMillis();
       System.out.println("entrySet for time consumed:" + (endTime - beginTime));
    }

    
    @Test
    public void testTreeMapTraversal() {
    }

    @AfterClass
    public static void tearDown() throws Exception {
        hashMapSmaller = null;
        hashMapBigger = null;
        treeMapSmaller = null;
        treeMapBigger = null;
    }

    private void initHashMapSmaller() {
        hashMapSmaller = new HashMap<String, String>();
        String key, value;

        for (int i = 1; i <= 1000000; i++) {
            key = "" + i;
            value = "value";
            hashMapSmaller.put(key, value);
        }
    }
    
    private void initHashMapBigger() {
        hashMapBigger = new HashMap<String, String>();
        String key, value;
        for (int i = 1; i <= 5000000; i++) {
            key = "" + i;
            value = "value";
            hashMapBigger.put(key, value);
        }
    }
    
    @SuppressWarnings("unused")
    private static void initTreeMap() {
        treeMapSmaller = new TreeMap<String, String>();
        treeMapBigger = new TreeMap<String, String>();
        String key, value;

        for (int i = 1; i <= 1000000; i++) {
            key = "" + i;
            value = "value";
            treeMapSmaller.put(key, value);
        }

        for (int i = 1; i <= 50000000; i++) {
            key = "" + i;
            value = "value";
            treeMapBigger.put(key, value);
        }
    }
}
