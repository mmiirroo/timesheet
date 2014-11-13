package channel;

import java.util.Observable;
import java.util.Observer;

/**
 * handle channel info changed
 *
 */
public class ModuleAChannelObserver implements Observer {
    public static final String TAG = ModuleAChannelObserver.class.getSimpleName();

    @Override
    public void update(Observable o, Object arg) {
        Channel channel = (Channel) o;
        System.out.println(TAG + "handle changed channel info:" + channel.toString());
    }
}
