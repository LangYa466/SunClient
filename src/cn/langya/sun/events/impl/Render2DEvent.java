package cn.langya.sun.events.impl;


import com.cubk.event.impl.Event;

/**
 * @author LangYa
 * @ClassName Render2DEvent
 * @date 2023/12/28 20:06
 * @Version 1.0
 */

public class Render2DEvent implements Event {
    public float partialTicks;

    public Render2DEvent(final float partialTicks) {
        this.partialTicks = partialTicks;
    }

}
