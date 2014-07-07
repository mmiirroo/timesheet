/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-7-2
 */

package proxy;

import java.lang.reflect.Proxy;

public class TestForumService {
    public static void main(String[] args) {
//        jdkProxy();
        cglibProxy();
    }
    
    static void cglibProxy() {
        CglibProxy proxy = new CglibProxy();
        ForumServiceImpl forumService = (ForumServiceImpl) proxy.getProxy(ForumServiceImpl.class);
        forumService.removeForum(-10);
        forumService.removeTopic(-1020);
    }

    static void jdkProxy(){
        ForumService target = (ForumService) new ForumServiceImpl();
        PerformanceHandler handler = new PerformanceHandler(target);
        ForumService proxy = (ForumService) Proxy.newProxyInstance(target.getClass().getClassLoader(), target
                .getClass().getInterfaces(), handler);
        
        proxy.removeForum(10);
        proxy.removeTopic(1012);
    }
}
