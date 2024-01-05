//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Administrator\Downloads\Minecraft1.12.2 Mappings"!

//Decompiled by Procyon!

package cn.langya.sun.modules.impl.world;

import cn.enaium.cf4m.annotation.Event;
import cn.langya.sun.events.impl.PacketReadEvent;
import cn.langya.sun.events.impl.PacketSendEvent;
import cn.langya.sun.events.impl.UpdateEvent;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.ui.impl.notification.NotificationManager;
import cn.langya.sun.ui.impl.notification.NotificationType;
import cn.langya.sun.utils.ClientUtils;
import cn.langya.sun.values.BoolValue;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.network.play.server.*;
import org.lwjgl.util.vector.Vector2f;


public class Stuck extends Module
{
    private static Stuck INSTANCE;
    public BoolValue antiSB;
    private double x;
    private double y;
    private double z;
    private double motionX;
    private double motionY;
    private double motionZ;
    private boolean onGround;
    private Vector2f rotation;
    
    public Stuck() {
        super("Stuck", Category.WORLD);
        this.antiSB = new BoolValue("Anti SB", true);
        this.onGround = false;
        Stuck.INSTANCE = this;
    }
    
    public void onEnable() {
        if (mc.player == null) {
            return;
        }
        this.onGround = mc.player.onGround;
        this.x = mc.player.posX;
        this.y = mc.player.posY;
        this.z = mc.player.posZ;
        this.motionX = mc.player.motionX;
        this.motionY = mc.player.motionY;
        this.motionZ = mc.player.motionZ;
        this.rotation = new Vector2f(mc.player.rotationYaw, mc.player.rotationPitch);
        final float f = mc.gameSettings.mouseSensitivity * 0.6f + 0.2f;
        final float gcd = f * f * f * 1.2f;
        final Vector2f rotation = this.rotation;
        rotation.x -= this.rotation.x % gcd;
        final Vector2f rotation2 = this.rotation;
        rotation2.y -= this.rotation.y % gcd;
    }
    
    public void onDisable() {
        if (this.antiSB.get() && !mc.player.onGround) {
            ClientUtils.chatlog("You can't disable this module now!");
            this.setState(true);
        }
    }
    
    @Event
    public void onPacket(final PacketSendEvent event) {
        if (event.packet instanceof CPacketPlayerTryUseItem) {
            final CPacketPlayerTryUseItem packet = (CPacketPlayerTryUseItem)event.packet;
            final Vector2f current = new Vector2f(mc.player.rotationYaw, mc.player.rotationPitch);
            final float f = mc.gameSettings.mouseSensitivity * 0.6f + 0.2f;
            final float gcd = f * f * f * 1.2f;
            final Vector2f vector2f = current;
            vector2f.x -= current.x % gcd;
            final Vector2f vector2f2 = current;
            vector2f2.y -= current.y % gcd;
            if (this.rotation.equals(current)) {
                return;
            }
            this.rotation = current;
            event.cancel();
             mc.getConnection().getNetworkManager().sendPacket((Packet<?>)new SPacketPlayerPosLook(current.x, current.y, this.onGround));
            mc.getConnection().getNetworkManager().sendPacket((Packet<?>)new CPacketPlayerTryUseItem(mc.player.getHeldItem()));
        }
        if (event.packet instanceof CPacketPlayer) {
            event.cancel();
        }
    }
    
    @Event
    public void onPacketR(final PacketReadEvent event) {
        if (event.packet instanceof CPacketPlayerTryUseItem) {
            this.setState(false);
        }
    }
    
    @Event
    public void onUpdate(final UpdateEvent event) {
        mc.player.motionX = 0.0;
        mc.player.motionY = 0.0;
        mc.player.motionZ = 0.0;
        mc.player.setPosition(this.x, this.y, this.z);
    }
    
    public static boolean isStuck() {
        return Stuck.INSTANCE.state;
    }
    
    public static void throwPearl(final Vector2f current) {
        if (!Stuck.INSTANCE.state) {
            return;
        }
        mc.player.rotationYaw = current.x;
        mc.player.rotationPitch = current.y;
        final float f = mc.gameSettings.mouseSensitivity * 0.6f + 0.2f;
        final float gcd = f * f * f * 1.2f;
        current.x -= current.x % gcd;
        current.y -= current.y % gcd;
        if (!Stuck.INSTANCE.rotation.equals((Tuple2f)current)) {
             mc.getConnection().getNetworkManager().sendPacket((Packet<?>)new SPacketPlayerPosLook(current.x, current.y, Stuck.INSTANCE.onGround));
        }
        Stuck.INSTANCE.rotation = current;
         mc.getConnection().getNetworkManager().sendPacket((Packet<?>)new CPacketPlayerTryUseItem(mc.player.getHeldItem()));
    }
}
