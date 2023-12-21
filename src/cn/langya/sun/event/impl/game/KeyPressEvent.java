package cn.langya.sun.event.impl.game;

import cn.langya.sun.event.Event;



public class KeyPressEvent extends Event {

    private final int key;

    public KeyPressEvent(int key) {
        this.key = key;
    }

    // @Exclude(Strategy.NAME_REMAPPING)
    public int getKey() {
        return key;
    }

}
