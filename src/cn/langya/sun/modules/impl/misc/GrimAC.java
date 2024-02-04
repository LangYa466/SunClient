package cn.langya.sun.modules.impl.misc;

// 魔改了派蒙神的东西

import java.text.DecimalFormat;
import com.cubk.event.annotations.EventTarget;
import cn.langya.sun.events.impl.misc.EventPacketRead;
import cn.langya.sun.events.impl.world.EventWorldLoad;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.utils.ClientUtils;
import cn.langya.sun.values.BoolValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.server.SPacketEntity;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;

public class GrimAC extends Module {
    public BoolValue reachValue = new BoolValue("Reach", true);
    public BoolValue noslowAValue = new BoolValue("NoSlowA", true);
    public BoolValue velocityValue = new BoolValue("Velocity", true);
    public static final DecimalFormat DF_1 = new DecimalFormat("0.000000");
    int vl;
    public GrimAC() {
        super("GrimAC", Category.Misc);
        add(reachValue, noslowAValue, velocityValue);
    }

    @Override
    public void onEnable() {
        vl = 0;
    }

    @EventTarget
    public void onWorld(EventWorldLoad event) {
        vl = 0;
    }

    @EventTarget
    public void onPacket(EventPacketRead event) {
        if(mc.player == null) return;

        if (mc.player.ticksExisted % 6 == 0) {
            SPacketEntityStatus s19;
            if (event.packet instanceof SPacketEntityStatus && reachValue.get() && (s19 = (SPacketEntityStatus)event.packet).getOpCode() == 2) {
                new Thread(() -> checkCombatHurt(s19.getEntity(mc.world))).start();
            }
            if (event.packet instanceof SPacketEntity && noslowAValue.get()) {
                SPacketEntity packet = (SPacketEntity)event.packet;
                Entity entity = packet.getEntity(mc.world);
                    if (!(entity instanceof EntityPlayer && entity.getName().equals(mc.player.getName()))) {
                    return;
                }
                new Thread(() -> checkPlayer((EntityPlayer)entity)).start();
            }
        }
    }


    private void checkCombatHurt(Entity entity) {
        if (!(entity instanceof EntityLivingBase)) {
            return;
        }
        if(entity == mc.player) {
            return;
        }
        EntityPlayer attacker = null;
        int attackerCount = 0;
        for (Entity worldEntity : mc.world.getLoadedEntityList()) {
            if (!(worldEntity instanceof EntityPlayer) || worldEntity.getDistanceToEntity(entity) > 7.0f || (worldEntity).equals(entity)) continue;
            ++attackerCount;
            attacker = (EntityPlayer)worldEntity;
        }
        if (attacker == null || attacker.equals(entity)) {
            return;
        }
        if(entity.getName().equals(mc.player.getName())) {
            return;
        }
        double reach = attacker.getDistanceToEntity(entity);
        String prefix = TextFormatting.GRAY + "[" + TextFormatting.AQUA + "GrimAC" + TextFormatting.GRAY + "] " + TextFormatting.RESET + TextFormatting.GRAY + attacker.getName() + TextFormatting.WHITE + " failed ";
        if (reach > 3.0 && ((EntityLivingBase) entity).getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemSword) {
            ClientUtils.chatlog(prefix + TextFormatting.AQUA + "Reach" + TextFormatting.WHITE + " (vl:" + attackerCount + ".0)" + TextFormatting.GRAY + ": " + DF_1.format(reach) + " blocks");
        }

    }

    private void checkPlayer(EntityPlayer player) {
        if (player.equals(mc.player)) {
            return;
        }
        String prefix = TextFormatting.GRAY + "[" + TextFormatting.AQUA + "GrimAC" + TextFormatting.GRAY + "] " + TextFormatting.RESET + TextFormatting.GRAY + player.getName() + TextFormatting.WHITE + " failed ";
        if (player.isUsingItem() && (player.posX - player.lastTickPosX > 0.2 || player.posZ - player.lastTickPosZ > 0.2)) {
            ClientUtils.chatlog(prefix + TextFormatting.AQUA + "NoSlowA (Prediction)" + TextFormatting.WHITE + " (vl:" + this.vl + ".0)");
            ++vl;

        }

        if(player.motionY >= 0 && player.hurtTime > 0 && velocityValue.get()) {
            ClientUtils.chatlog(prefix + TextFormatting.AQUA + "VelocityA" + TextFormatting.WHITE + " (vl:" + this.vl + ".0)");
        }

        if (!mc.world.loadedEntityList.contains(player) || !player.isEntityAlive()) {
            vl = 0;
        }
    }
}
