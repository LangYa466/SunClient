package cn.langya.sun.modules.impl.move;

import cn.langya.sun.events.impl.MoveEvent;
import cn.langya.sun.events.impl.SlowDownEvent;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.values.StringValue;
import com.cubk.event.annotations.EventTarget;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

/**
 * @author LangYa
 * @ClassName NoSlow
 * @date 2024/1/13 下午 01:34
 * @Version 1.0
 */

public class NoSlow extends Module {

    public final StringValue mode = new StringValue("Mode", "Hyt");

    public boolean helditem() {
        return mc.player.getHeldItem(EnumHand.MAIN_HAND) instanceof ItemStack;
    }

    public NoSlow() {
        super("NoSlow", Category.Move);
        mode.getValues().add("Hyt");
        mode.getValues().add("Vanilla");
    }

    @EventTarget
    public void onSlowDown(SlowDownEvent e) {
        if (mode.get().equals("Vanilla") && helditem()) {
            e.forward = 1.0f;
            e.strafe = 1.0f;
        }
    }

    @EventTarget
    public void onMove(MoveEvent e) {
        if (mode.get().equals("Hyt")) {

        }
    }
}
