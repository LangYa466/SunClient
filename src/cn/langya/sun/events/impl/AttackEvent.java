package cn.langya.sun.events.impl;

import com.cubk.event.impl.Event;
import net.minecraft.entity.Entity;

/**
 * @author LangYa
 * @ClassName AttackEvent
 * @date 2023/12/28 20:26
 * @Version 1.0
 */

public class AttackEvent implements Event {
    public Entity target;

    public AttackEvent(final Entity entity) {
        this.target = entity;
    }

}
