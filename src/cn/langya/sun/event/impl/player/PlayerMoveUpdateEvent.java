package cn.langya.sun.event.impl.player;

import cn.langya.sun.event.Event;
import cn.langya.sun.utils.MovementUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

@Getter
@Setter
@AllArgsConstructor
public class PlayerMoveUpdateEvent extends Event {

    private float strafe, forward, friction, yaw, pitch;

    public void applyMotion(double speed, float strafeMotion) {
        float remainder = 1 - strafeMotion;
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        strafeMotion *= 0.91;
        if (player.onGround) {
            MovementUtils.setSpeed(speed);
        } else {
            player.motionX = player.motionX * strafeMotion;
            player.motionZ = player.motionZ * strafeMotion;
            friction = (float) speed * remainder;
        }
    }

}