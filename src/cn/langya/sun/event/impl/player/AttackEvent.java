package cn.langya.sun.event.impl.player;

import cn.langya.sun.event.Event;
import lombok.AllArgsConstructor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

@AllArgsConstructor
public class AttackEvent extends Event {

    private final EntityLivingBase targetEntity;

    public Entity getTargetEntity() {
        return targetEntity;
    }

}
