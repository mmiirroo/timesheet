/*
 * copyright (C) 2006-2014 the original author or authors.

 * date: 2014-7-2
 */

package proxy;

public class ForumServiceImpl implements ForumService {

    @Override
    public void removeTopic(int topicId) {
        System.out.println("simulated remove topic:"+topicId);
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeForum(int forumId) {
        System.out.println("simulated remove forum :"+forumId);
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }        
    }

}
