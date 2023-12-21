package cn.langya.sun.event.impl.render;

import cn.langya.sun.event.Event;



public class Render3DEvent extends Event {

    private float ticks;

    public Render3DEvent(float ticks) {
        this.ticks = ticks;
    }

    // @Exclude(Strategy.NAME_REMAPPING)
    public float getTicks() {
        return ticks;
    }

    public void setTicks(float ticks) {
        this.ticks = ticks;
    }

}
