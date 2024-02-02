package cn.langya.sun.events.impl.player;

import com.cubk.event.impl.Event;
import net.minecraft.client.Minecraft;

/**
 * @author LangYa
 * @ClassName UpdateEvent
 * @date 2023/12/28 20:26
 * @Version 1.0
 */

public class UpdateEvent implements Event {
    private float yaw;
    public float pitch;
    public boolean onGround;
    public boolean pre;

    public UpdateEvent(boolean pre,float yaw,float pitch,boolean onGround) {
        this.pre = pre;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
        Minecraft mc = Minecraft.getMinecraft();
        mc.player.prevRenderYawOffset = yaw;
        mc.player.renderYawOffset = yaw;
        mc.player.prevRotationYawHead = yaw;
        mc.player.rotationYawHead = yaw;
    }
}
