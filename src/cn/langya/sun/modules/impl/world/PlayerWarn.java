// by paimon
package cn.langya.sun.modules.impl.world;

import cn.langya.sun.events.impl.TickEvent;
import cn.langya.sun.events.impl.WorldLoadEvent;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.modules.impl.misc.Teams;
import cn.langya.sun.ui.impl.notification.NotificationManager;
import cn.langya.sun.utils.player.HYTUtils;
import com.cubk.event.annotations.EventTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerWarn extends Module
{
    public static List<Entity> flaggedEntity;

    public PlayerWarn() {
        super("PlayerTracker", Category.World);
    }

    @EventTarget
    public void onWorld(final WorldLoadEvent e) {
        PlayerWarn.flaggedEntity.clear();
    }

    @EventTarget
    public void onTick(final TickEvent e) {
        if (mc.world == null || mc.world.loadedEntityList.isEmpty()) {
            return;
        }
        if (HYTUtils.isInLobby()) {
            return;
        }
        if (mc.player.ticksExisted % 6 == 0) {
            for (final Entity ent : mc.world.loadedEntityList) {
                if (ent instanceof EntityPlayer && ent != mc.player) {
                    final EntityPlayer player = (EntityPlayer)ent;
                    if (HYTUtils.isStrength(player) > 0 && !PlayerWarn.flaggedEntity.contains(player) && !Teams.isSameTeam((Entity)player)) {
                        PlayerWarn.flaggedEntity.add((Entity)player);
                        NotificationManager.add("有新的傻逼", player.getName() + " 是傻逼力量狗，快去斩杀他的亲妈吧", TrayIcon.MessageType.WARNING);
                    }
                    if (HYTUtils.isRegen(player) > 0 && !PlayerWarn.flaggedEntity.contains(player) && !Teams.isSameTeam((Entity)player)) {
                        PlayerWarn.flaggedEntity.add((Entity)player);
                        NotificationManager.add("有新的傻逼", player.getName() + " 是傻逼生命恢复狗，快去斩杀他的亲妈吧", TrayIcon.MessageType.WARNING);
                    }
                    if (HYTUtils.isHoldingGodAxe(player) && !PlayerWarn.flaggedEntity.contains(player) && !Teams.isSameTeam((Entity)player)) {
                        PlayerWarn.flaggedEntity.add((Entity)player);
                        NotificationManager.add("有新的傻逼", player.getName() + " 正在使用秒人斧击杀他的母亲，主播小心点别被杀了", TrayIcon.MessageType.WARNING);
                    }
                    if (HYTUtils.isKBBall(player.getHeldItem(EnumHand.MAIN_HAND)) && !PlayerWarn.flaggedEntity.contains(player) && !Teams.isSameTeam((Entity)player)) {
                        PlayerWarn.flaggedEntity.add((Entity)player);
                        NotificationManager.add("有新的傻逼", player.getName() + " 正在使用击退球把他的老母打到十里开外，主播小心点", TrayIcon.MessageType.WARNING);
                    }
                    if (HYTUtils.hasEatenGoldenApple(player) <= 0 || PlayerWarn.flaggedEntity.contains(player) || Teams.isSameTeam((Entity)player)) {
                        continue;
                    }
                    PlayerWarn.flaggedEntity.add((Entity)player);
                    NotificationManager.add("有新的傻逼", player.getName() + " 吃了附魔金苹果换取锁血和他的老妈子决斗，主播等他锁血没了再去殴打他吧", TrayIcon.MessageType.WARNING);
                }
            }
        }
    }

    static {
        PlayerWarn.flaggedEntity = new ArrayList<Entity>();
    }
}