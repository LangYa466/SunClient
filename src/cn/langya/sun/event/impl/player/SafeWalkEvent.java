package cn.langya.sun.event.impl.player;

import cn.langya.sun.event.Event;



public class SafeWalkEvent extends Event {

    private boolean safe;

    public boolean isSafe() {
        return this.safe;
    }

    // @Exclude(Strategy.NAME_REMAPPING)
    public void setSafe(boolean safe) {
        this.safe = safe;
    }

}
