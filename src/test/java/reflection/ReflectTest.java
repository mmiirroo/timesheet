package reflection;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.timesheet.domain.Task;

/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-7-2
 */

public class ReflectTest {
    public static void main(String[] args) throws Exception {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class<?> clazz = loader.loadClass("org.timesheet.domain.Task");
        
        Constructor<?> cons=clazz.getDeclaredConstructor((Class[])null);
        Task task = (Task)cons.newInstance();
        Method setDescription = clazz.getMethod("setDescription", String.class);
        setDescription.invoke(task, "desc");
        System.out.println(task.getDescription());
        
        Field desc = clazz.getDeclaredField("description");
        desc.setAccessible(true);
        desc.set(task, "not");
        System.out.println(task.getDescription());
    }
}
