package cn.langya.sun.events.impl;

import com.cubk.event.impl.Event;

/**
 * @author LangYa
 * @ClassName Render3DEvent
 * @date 2023/12/28 20:26
 * @Version 1.0
 */

public class Render3DEvent implements Event {
    public float ticks;
    public Render3DEvent(final float ticks) {
        this.ticks = ticks;
    }
}
