package cn.langya.sun.events.impl.misc;

import com.cubk.event.impl.Event;

/**
 * @author LangYa
 * @date 2024/1/29 обнГ 03:19
 */

public class EventText implements Event {
    public String text;
    public EventText(String text) {
        this.text = text;
    }
}
