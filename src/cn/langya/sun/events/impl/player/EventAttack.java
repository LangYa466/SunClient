package cn.langya.sun.events.impl.player;

import com.cubk.event.impl.Event;
import net.minecraft.entity.Entity;

/**
 * @author LangYa
 * @ClassName AttackEvent
 * @date 2023/12/28 20:26
 * @Version 1.0
 */

public class EventAttack implements Event {
    public Entity target;
    public boolean cancelled;

    public void cancel() {
        this.cancelled = true;
    }

    public EventAttack(final Entity entity) {
        this.target = entity;
    }

}
