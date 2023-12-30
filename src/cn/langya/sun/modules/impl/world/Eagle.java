package cn.langya.sun.modules.impl.world;

import cn.enaium.cf4m.annotation.Event;
import cn.langya.sun.events.impl.Render2DEvent;
import cn.langya.sun.events.impl.UpdateEvent;
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
        super("安全搭路", true, Category.WORLD);
    }


    public static Block getBlock(final BlockPos pos) {
        return mc.world.getBlockState(pos).getBlock();
    }

    public static Block getBlockUnderPlayer(final EntityPlayer player) {
        return getBlock(new BlockPos(player.posX, player.posY - 1.0, player.posZ));
    }

    @Event
    public void onUpdate(UpdateEvent event) {

        if(!isEnabled()) return;

        if (getBlockUnderPlayer(mc.player) instanceof BlockAir) {
            if (mc.player.onGround) {
                KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(), true);
            }
        } else {
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(), false);
        }
    }




    @Event
    public void onRender2D(Render2DEvent event) {
        if(!isEnabled()) return;

        final Item helditem = mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem();
        final int y = sr.getScaledHeight() - 90 + 19;

        if(helditem instanceof ItemBlock) {
            FontManager.drawString( "方块:"+helditem.getBlockCount(), sr.getScaledWidth() / 2 - 8, y - 19, Color.WHITE);
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
