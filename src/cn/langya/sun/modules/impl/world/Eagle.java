package cn.langya.sun.modules.impl.world;

import com.cubk.event.annotations.EventTarget;
import cn.langya.sun.events.impl.render.Render2DEvent;
import cn.langya.sun.events.impl.player.UpdateEvent;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.ui.FontManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

import java.awt.*;

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

    String sb(Item helditem) {
        if(helditem.getBlockCount() >1) {
            return "Blocks:" + helditem.getBlockCount();
        } else {
            return "Block:" + helditem.getBlockCount();
        }
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {

        if (getBlockUnderPlayer(mc.player) instanceof BlockAir) {
            if (mc.player.onGround) {
                KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(), true);
            }
        } else {
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(), false);
        }

    }

    @EventTarget
    public void onRender2D(Render2DEvent event) {
        if(!isEnabled()) return;

        final Item helditem = mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem();
        final int y = sr.getScaledHeight() - 90 + 19;

        if(helditem instanceof ItemBlock) {
            FontManager.drawString(sb(helditem), sr.getScaledWidth() / 2 - 8, y - 19, Color.WHITE);
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
