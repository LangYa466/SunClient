package cn.langya.sun.event.impl.player;

import cn.langya.sun.event.Event;

public class ClickEvent extends Event
{
    boolean fake;

    public ClickEvent(boolean fake) { this.fake = fake; }

    public boolean isFake() { return fake; }
}
