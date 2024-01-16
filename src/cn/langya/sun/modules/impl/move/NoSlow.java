package cn.langya.sun.modules.impl.move;

import cn.langya.sun.events.impl.MoveEvent;
import cn.langya.sun.events.impl.SlowDownEvent;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.values.StringValue;
import com.cubk.event.annotations.EventTarget;
import io.netty.buffer.Unpooled;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.util.EnumHand;

/**
 * @author LangYa
 * @ClassName NoSlow
 * @date 2024/1/13 下午 01:34
 * @Version 1.0
 */

public class NoSlow extends Module {

    public final StringValue mode = new StringValue("Mode", "Hyt");

    boolean slow = false;

    public NoSlow() {
        super("NoSlow", Category.Move);
        mode.getValues().add("Hyt");
        mode.getValues().add("Vanilla");
        add(mode);
    }

    @EventTarget
    public void onSlowDown(SlowDownEvent e) {
        if (mc.player.getHeldItem(EnumHand.MAIN_HAND) != null && mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemSword){
            e.forward = 1.0f;
            e.strafe = 1.0f;
            slow = true;
        } else {
            slow = false;
        }
    }

    @EventTarget
    public void onMove(MoveEvent e) {
        if (mode.get().equals("Hyt") && mc.player.getHeldItem(EnumHand.MAIN_HAND) != null && mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemSword && slow) {
            mc.getConnection().sendPacket(new CPacketHeldItemChange((mc.player.inventory.currentItem + 1) % 9));
            mc.getConnection().sendPacket(new CPacketCustomPayload("test", new PacketBuffer(Unpooled.wrappedBuffer(new byte[]{1}))));
            mc.getConnection().sendPacket(new CPacketHeldItemChange((mc.player.inventory.currentItem)));
        }
    }
}