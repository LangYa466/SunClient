package cn.langya.sun.event.impl.player;

import cn.langya.sun.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JumpFixEvent extends Event {
    private float yaw;
}
