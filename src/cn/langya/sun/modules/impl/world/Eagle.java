package cn.langya.sun.modules.impl.world;

import cn.langya.sun.events.impl.player.EventUpdate;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import com.cubk.event.annotations.EventTarget;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class Eagle extends Module {

    public Eagle() {
        super("Eagle", true, Category.World);
    }

    public static Block getBlock(final BlockPos pos) {
        return mc.world.getBlockState(pos).getBlock();
    }

    public static Block getBlockUnderPlayer(final EntityPlayer player) {
        return getBlock(new BlockPos(player.posX, player.posY - 1.0, player.posZ));
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {

        if (getBlockUnderPlayer(mc.player) instanceof BlockAir) {
            if (mc.player.onGround) {
                KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(), true);
            }
        } else {
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(), false);
        }

    }

    public void onEnable() {
        if (mc.player == null) {
            return;
        }
        mc.player.setSneaking(false);
        super.onEnable();
    }

    public void onDisable() {
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(), false);
        super.onDisable();
    }

}
