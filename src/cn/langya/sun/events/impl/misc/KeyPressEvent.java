package cn.langya.sun.events.impl.misc;

import com.cubk.event.impl.Event;

public class KeyPressEvent implements Event {
    private int keyCode;

    public KeyPressEvent(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }
}
