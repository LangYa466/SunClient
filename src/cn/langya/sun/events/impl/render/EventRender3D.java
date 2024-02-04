package cn.langya.sun.events.impl.render;

import com.cubk.event.impl.Event;

/**
 * @author LangYa
 * @ClassName Render3DEvent
 * @date 2023/12/28 20:26
 * @Version 1.0
 */

public class EventRender3D implements Event {
    public float ticks;
    public EventRender3D(final float ticks) {
        this.ticks = ticks;
    }
}
