package cn.langya.sun.event.impl.game;

import cn.langya.sun.event.Event;



public class TickEvent extends Event.StateEvent {

    private final int ticks;

    public TickEvent(int ticks) {
        this.ticks = ticks;
    }

    // @Exclude(Strategy.NAME_REMAPPING)
    public int getTicks() {
        return ticks;
    }

}
