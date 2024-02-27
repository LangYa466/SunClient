package cn.langya.sun.modules.impl.combat;

import cn.langya.sun.events.impl.player.EventUpdate;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.utils.misc.MathUtil;
import cn.langya.sun.utils.misc.TimeUtil;
import cn.langya.sun.utils.player.RotationUtil;
import cn.langya.sun.values.BoolValue;
import cn.langya.sun.values.DoubleValue;
import cn.langya.sun.values.IntValue;
import cn.langya.sun.values.ListValue;
import com.cubk.event.annotations.EventTarget;
import de.florianmichael.viamcp.fixes.AttackOrder;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LangYa
 * @date 2024/2/4 ÏÂÎç 07:31
 */

@Getter
public class KillAura extends Module {

    private static final IntValue minCpsValue = new IntValue("MinCPS", 10, 20, 1);
    private static final IntValue maxCpsValue = new IntValue("MaxCPS", 10, 20, minCpsValue.get());
    private static final DoubleValue groundRangeValue = new DoubleValue("GroudRange", 3.0, 6, 3.0);
    private static final DoubleValue airRangeValue = new DoubleValue("AirRange", 3.0, 6, 3.0);
    private static final ListValue targetModeValue = new ListValue("TargetMode", "Single");
    private static final BoolValue rotationValue = new BoolValue("Rotation", true);
    private static final ListValue rotationModeValue = new ListValue("RotationsMode", "Vanilla");
    private static final BoolValue rayCastValue = new BoolValue("RayCast", true);
    private static final BoolValue swingValue = new BoolValue("Swing", true);
    private static final BoolValue keepSprintValue = new BoolValue("KeepSprint", false);
    private static final BoolValue targetPlayerValue = new BoolValue("TargetPlayer", true);
    private static final BoolValue targetMobValue = new BoolValue("TargetMob", false);

    public KillAura() {
        super("KillAura", Category.Combat);
        add(minCpsValue,maxCpsValue,groundRangeValue, airRangeValue, targetModeValue, rotationValue, rotationModeValue, rayCastValue, swingValue, keepSprintValue);

        targetModeValue.getValues().add("Single");
        targetModeValue.getValues().add("Multi");

        rotationModeValue.getValues().add("Vanilla");
        rotationModeValue.getValues().add("GrimAC");

        targets = new ArrayList<>();

    }

    @Getter
    private static List<EntityLivingBase> targets;
    @Setter
    private static EntityLivingBase target;
    @Setter
    // yaw pitch
    private static float[] rotations = new float[]{0, 0};
    @Getter
    private static TimeUtil attacktimer = new TimeUtil();

    @EventTarget
    void onU(EventUpdate e) {
        targets.clear();

        for (Entity entity : mc.world.loadedEntityList) {
            if (entity instanceof EntityLivingBase && mc.player.getDistanceToEntity(entity) <= getRange() && !entity.isDead && entity != mc.player) {
                targets.add((EntityLivingBase) entity);
            }
        }

        //targets.stream().filter(entity -> mc.player.getDistanceToEntity(entity) >= getRange() || entity.getHealth() > 0 || entity != mc.player);

        if(!targetPlayerValue.get()) {
            targets.removeIf(entity -> entity instanceof EntityPlayer);
        }

        if(!targetMobValue.get()) {
            targets.removeIf(entity -> entity instanceof EntityMob);
        }

        if(targets.isEmpty()) return;

        switch (targetModeValue.get()) {
            case "Single":
                target = targets.get(0);
                break;
            case "Multi":
                for (EntityLivingBase entity : targets) {
                    target = entity; // ?
                }
                break;
        }


        if (rayCastValue.get() && rotationValue.get() && !RotationUtil.isMouseOver(e.getYaw(), e.pitch, target, getRange()))
            return;


        if (rotationValue.get()) e.setRotations(getRotations());

        if(attacktimer.hasTimePassed(1000 / getCps())) {
            attackEntity(target);
            attacktimer.reset();
        }


    }

    static void attackEntity(EntityLivingBase e) {

        if (targetModeValue.get().equals("Single") && mc.player.getAttackingEntity() != null) {
            return;
        }

        AttackOrder.sendFixedAttack(mc.player, e);

    }

    static float[] getRotations() {
        if (rotationValue.get()) {
            switch (rotationModeValue.get()) {
                case "Vanilla":
                    rotations = RotationUtil.getTargetRotations(target);
                    break;
                case "GrimAC":
                    rotations = RotationUtil.getGrimRotations(target);
                    break;
            }
        }

        return rotations;
    }

    static double getRange() {
        return mc.player.onGround ? groundRangeValue.get() : airRangeValue.get();
    }

    static int getCps() {
        final int maxValue = (maxCpsValue.getMaximum() - maxCpsValue.get()) * 20;
        final int minValue = (minCpsValue.getMaximum() - minCpsValue.get()) * 20;
        return MathUtil.getRandomInRange(minValue, maxValue);
    }

}
