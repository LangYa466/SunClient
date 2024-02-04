package cn.langya.sun.modules.impl.combat;

import cn.langya.sun.events.impl.player.UpdateEvent;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.values.BoolValue;
import cn.langya.sun.values.DoubleValue;
import com.cubk.event.annotations.EventTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;

/**
 * @author LangYa
 * @date 2024/2/4 ÏÂÎç 07:31
 */

public class KillAura extends Module {

    public static final DoubleValue groundRangeValue = new DoubleValue("GroudRange",3.0,6,3.0);
    public static final DoubleValue airRangeValue = new DoubleValue("AirRange",3.0,6,3.0);
    public static final BoolValue rotationValue = new BoolValue("Rotation",true);
    public static final BoolValue swingValue = new BoolValue("Swing",true);
    public static final BoolValue keepSprintValue = new BoolValue("KeepSprint",false);

    public KillAura() {
        super("KillAura", Category.Combat);
    }

    @EventTarget
    void onU(UpdateEvent e) {

        for (final Entity entity : mc.world.loadedEntityList) {

            if(!entity.isDead && entity instanceof EntityLivingBase && entity != mc.player && mc.player.getDistanceToEntity(entity) >= getRange()) {
                attackEntity((EntityLivingBase) entity);
            }

        }

    }

    static void attackEntity(EntityLivingBase e) {

        if(mc.player.getAttackingEntity() != null) {
            return;
        }

        if(swingValue.get()) mc.player.swingArm(EnumHand.MAIN_HAND);

        mc.playerController.attackEntity(mc.player,e);

    }

    static void doRotation() {
        
    }

    public static double getRange() { return mc.player.onGround ? groundRangeValue.get() : airRangeValue.get(); }




}
