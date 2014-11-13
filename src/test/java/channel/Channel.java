package channel;

import java.util.Observable;

/**
 * channel info which is comparable, observable
 * 
 */
public class Channel extends Observable implements Comparable<Channel> {
    private String name;
    // primary key
    private Integer channelNo;

    public Channel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        super.setChanged();
        super.notifyObservers();
    }

    public Integer getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(Integer channelNo) {
        this.channelNo = channelNo;
    }

    @Override
    public String toString() {
        return "Channel [name=" + name + ", channelNo=" + channelNo + "]";
    }

    @Override
    public int compareTo(Channel other) {
        return this.getChannelNo() - other.getChannelNo();
    }
}
