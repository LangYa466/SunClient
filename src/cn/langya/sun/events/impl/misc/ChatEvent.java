package cn.langya.sun.events.impl.misc;

import com.cubk.event.impl.Event;

/**
 * @author LangYa
 * @ClassName ChatEvent
 * @date 2024/1/9 下午 08:21
 * @Version 1.0
 */

public class ChatEvent implements Event {
    private String message;
    public ChatEvent(String message) {
        this.message = message;
    }
}
