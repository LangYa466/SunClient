package cn.langya.sun.events.impl.render;


import com.cubk.event.impl.Event;

/**
 * @author LangYa
 * @ClassName Render2DEvent
 * @date 2023/12/28 20:06
 * @Version 1.0
 */

public class EventRender2D implements Event {
    public float partialTicks;

    public EventRender2D(final float partialTicks) {
        this.partialTicks = partialTicks;
    }

}
