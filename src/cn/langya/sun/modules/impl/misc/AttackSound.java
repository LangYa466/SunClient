package cn.langya.sun.modules.impl.misc;

import cn.langya.sun.events.impl.PacketSendEvent;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import com.cubk.event.annotations.EventTarget;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.util.SoundCategory;

/**
 * @author LangYa
 * @ClassName AttackSound
 * @date 2024/1/17 ÉÏÎç 11:08
 * @Version 1.0
 */

public class AttackSound extends Module {
    public AttackSound() {
        super("AttackSound",Category.Misc);
    }

    @EventTarget
    void onPacketSend(PacketSendEvent e) {
        if(e.packet instanceof CPacketAnimation && !mc.player.isUsingItem()) {
            mc.world.playSound(mc.player, mc.player.posX,mc.player.posY,mc.player.posZ, SoundEvents.BLOCK_WOOD_PRESSPLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.8F);
        }
    }
}
