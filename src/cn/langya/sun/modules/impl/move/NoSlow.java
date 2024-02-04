package cn.langya.sun.modules.impl.move;

import cn.langya.sun.events.impl.player.EventMotion;
import cn.langya.sun.events.impl.player.EventSlowDown;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.values.ListValue;
import com.cubk.event.annotations.EventTarget;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.CPacketConfirmTransaction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

/**
 * @author LangYa
 * @ClassName NoSlow
 * @date 2024/1/13 下午 01:34
 * @Version 1.0
 */

public class NoSlow extends Module {

    public final ListValue mode = new ListValue("Mode", "Hyt");

    boolean slow = false;

    public NoSlow() {
        super("NoSlow", Category.Move);
        mode.getValues().add("Hyt");
        mode.getValues().add("Vanilla");
        add(mode);
    }

    @EventTarget
    public void onSlowDown(EventSlowDown e) {
        if(mc.player == null) return;

        if (mc.player.getHeldItem(EnumHand.MAIN_HAND) != null && mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemSword){
            e.forward = 1.0f;
            e.strafe = 1.0f;
            slow = true;
        } else {
            slow = false;
        }
    }

    @EventTarget
    public void onMove(EventMotion e) {
        if(mc.player == null) return;

        if (mode.get().equals("Hyt") && mc.player.getHeldItem(EnumHand.MAIN_HAND) != null && mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemSword && slow) {
            mc.getConnection().sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
            mc.getConnection().sendPacket(new CPacketHeldItemChange((mc.player.inventory.currentItem + 1) % 9));
            mc.getConnection().sendPacket(new CPacketConfirmTransaction(Integer.MAX_VALUE, (short)32767, true));
            mc.getConnection().sendPacket(new CPacketHeldItemChange((mc.player.inventory.currentItem)));
        }
    }
}