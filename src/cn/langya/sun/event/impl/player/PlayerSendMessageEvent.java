package cn.langya.sun.event.impl.player;

import cn.langya.sun.event.Event;



public class PlayerSendMessageEvent extends Event {
    private final String message;

    public PlayerSendMessageEvent(String message) {
        this.message = message;
    }

    // @Exclude(Strategy.NAME_REMAPPING)
    public String getMessage() {
        return message;
    }

}
