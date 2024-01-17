package cn.langya.sun.modules.impl.player;

import cn.langya.sun.events.impl.AttackEvent;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.values.DoubleValue;
import com.cubk.event.annotations.EventTarget;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.world.GameType;

/**
 * @author LangYa
 * @ClassName Reach
 * @date 2024/1/17 ÏÂÎç 09:03
 * @Version 1.0
 */

public class Reach extends Module {

    final DoubleValue range = new DoubleValue("Range", 3.3, 7, 3);

    public Reach() {
        super("Reach", Category.Player);
        add(range);
    }

    @EventTarget
    void onAttack(AttackEvent e) {
        if (e.target.getDistanceToEntity(mc.player) >= range.get()) {
            mc.playerController.syncCurrentPlayItem();
            mc.playerController.connection.sendPacket(new CPacketUseEntity(e.target));

            if (mc.playerController.getCurrentGameType() != GameType.SPECTATOR) {
                mc.player.attackTargetEntityWithCurrentItem(e.target);
                mc.player.resetCooldown();
            }
        }
    }
}