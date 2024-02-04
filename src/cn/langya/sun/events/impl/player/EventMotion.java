package cn.langya.sun.events.impl.player;

import com.cubk.event.impl.Event;

/**
 * @author LangYa
 * @ClassName MoveEvent
 * @date 2023/12/28 20:26
 * @Version 1.0
 */

public class EventMotion implements Event {
    public double x;
    public double y;
    public double z;
    public boolean pre;


    public EventMotion(final double x, final double y, final double z, final boolean pre) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pre = pre;
    }

}
