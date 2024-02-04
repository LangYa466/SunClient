package cn.langya.sun.events.impl.misc;

import com.cubk.event.impl.Event;

public class EventKeyPress implements Event {
    private int keyCode;

    public EventKeyPress(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }
}
