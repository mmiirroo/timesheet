package channel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * basic channel operation
 *
 */
public class ChannelManager {
    private List<Channel> channelList = new ArrayList<Channel>();
    private List<Channel> favouriteChannelList = new ArrayList<Channel>();

    public ChannelManager() {

    }

    public boolean addChannel(Channel channel) {
        return this.channelList.add(channel);
    }

    public boolean removeChannel(Channel channel) {
        return this.channelList.remove(channel);
    }

    public void sortChannelList() {
        Collections.sort(this.channelList);
    }

    public boolean addFavouriteChannel(Channel channel) {
        return this.channelList.add(channel);
    }

    public boolean removeFavouriteChannel(Channel channel) {
        return this.channelList.remove(channel);
    }

    public List<Channel> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<Channel> channelList) {
        this.channelList = channelList;
    }

    public List<Channel> getFavouriteChannelList() {
        return favouriteChannelList;
    }

    public void setFavouriteChannelList(List<Channel> favouriteChannelList) {
        this.favouriteChannelList = favouriteChannelList;
    }
    
    public static void main(String[] args) {
        Channel channel = new Channel();
        channel.addObserver(new ModuleAChannelObserver());
        channel.setName("name changed");
     }
}
