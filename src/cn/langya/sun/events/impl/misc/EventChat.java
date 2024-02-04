package cn.langya.sun.events.impl.misc;

import com.cubk.event.impl.Event;

/**
 * @author LangYa
 * @ClassName ChatEvent
 * @date 2024/1/9 下午 08:21
 * @Version 1.0
 */

public class EventChat implements Event {
    private String message;
    public EventChat(String message) {
        this.message = message;
    }
}
