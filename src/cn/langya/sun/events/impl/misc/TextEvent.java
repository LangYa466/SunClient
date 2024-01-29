package cn.langya.sun.events.impl.misc;

import com.cubk.event.impl.Event;

/**
 * @author LangYa
 * @date 2024/1/29 обнГ 03:19
 */

public class TextEvent implements Event {
    public String text;
    public TextEvent(String text) {
        this.text = text;
    }
}
