package cn.langya.sun.modules.impl.misc;

// 魔改了派蒙神的东西

import java.text.DecimalFormat;
import cn.enaium.cf4m.annotation.Event;
import cn.langya.sun.events.impl.PacketReadEvent;
import cn.langya.sun.events.impl.WorldLoadEvent;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.utils.ClientUtils;
import cn.langya.sun.values.BoolValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.SPacketEntity;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.util.text.TextFormatting;

public class GrimAC
        extends Module {
    public BoolValue reachValue = new BoolValue("Reach", true);
    public BoolValue noslowAValue = new BoolValue("NoSlowA", true);
    public BoolValue velocityValue = new BoolValue("Velocity", true);
    public static final DecimalFormat DF_1 = new DecimalFormat("0.000000");
    int vl;

    public GrimAC() {
        super("反作弊", Category.Misc);
    }

    @Override
    public void onEnable() {
        vl = 0;
    }

    @Event
    public void onWorld(WorldLoadEvent event) {
        vl = 0;
    }

    @Event
    public void onPacket(PacketReadEvent event) {
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
        String prefix = ( TextFormatting.GRAY) + "[" + (TextFormatting.AQUA) + "黑客检测器" + (TextFormatting.GRAY) + "] " + (TextFormatting.RESET) + (TextFormatting.GRAY) + attacker.getName() + (TextFormatting.WHITE) + " failed ";
        if (reach > 3.0) {
            ClientUtils.chatlog(prefix + (TextFormatting.AQUA) + "长臂猿" + (TextFormatting.WHITE) + " (次数:" + attackerCount + ".0)" + (TextFormatting.GRAY) + ": " + DF_1.format(reach) + " blocks");
        }

        if(mc.player.hurtTime > 0 && mc.player.motionY >= 0 && velocityValue.get()) {
            ClientUtils.chatlog(prefix + (TextFormatting.AQUA) + "防击退" + (TextFormatting.WHITE) + " (次数:" + attackerCount + ".0)" + (TextFormatting.GRAY) + ": " + DF_1.format(mc.player.motionY) + " blocks");
        }

    }

    private void checkPlayer(EntityPlayer player) {
        if (player.equals(mc.player)) {
            return;
        }
        String prefix =  TextFormatting.GRAY + "[" +  TextFormatting.AQUA + "黑客检测器" +  TextFormatting.GRAY + "] " + (TextFormatting.RESET) + (TextFormatting.GRAY) + player.getName() + (TextFormatting.WHITE) + " failed ";
        if (player.isUsingItem() && (player.posX - player.lastTickPosX > 0.2 || player.posZ - player.lastTickPosZ > 0.2)) {
            ClientUtils.chatlog(prefix +  TextFormatting.AQUA + "无减速" +  TextFormatting.WHITE + " (次数:" + vl + ".0)");
            ++vl;

        }
        if (!mc.world.loadedEntityList.contains(player) || !player.isEntityAlive()) {
            vl = 0;
        }
    }
}
