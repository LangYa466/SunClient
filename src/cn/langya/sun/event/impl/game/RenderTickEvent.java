package cn.langya.sun.event.impl.game;

import cn.langya.sun.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author cedo
 * @since 03/30/2022
 */
@AllArgsConstructor
@Getter
public class RenderTickEvent extends Event.StateEvent {
    private final float ticks;
}
