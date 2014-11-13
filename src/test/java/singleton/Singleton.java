package singleton;

/**
 * http://en.wikipedia.org/wiki/Singleton_pattern
 *
 */
public class Singleton {
    // Private constructor. Prevents instantiation from other class
    private Singleton() {
        
    }
    
    /**
     * Initializes singleton
     * 
     * SingletonHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to SingletonHolder.INSTANCE, not before.
     */
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    
    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
