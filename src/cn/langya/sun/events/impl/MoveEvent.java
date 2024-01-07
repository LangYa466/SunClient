package cn.langya.sun.events.impl;

import com.cubk.event.impl.Event;

/**
 * @author LangYa
 * @ClassName MoveEvent
 * @date 2023/12/28 20:26
 * @Version 1.0
 */

public class MoveEvent implements Event {
    public double x;
    public double y;
    public double z;

    public MoveEvent(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

}
