package cn.langya.sun.events.impl.player;

import com.cubk.event.impl.Event;

/**
 * @author LangYa
 * @ClassName SlowDownEvent
 * @date 2024/1/13 下午 01:46
 * @Version 1.0
 */

public class SlowDownEvent implements Event {
    public Float forward;
    public Float strafe;

    public SlowDownEvent(float moveForward, float moveStrafing) {
        forward = moveForward;
        strafe = moveStrafing;
    }
}