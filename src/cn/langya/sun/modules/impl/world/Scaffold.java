package cn.langya.sun.modules.impl.world;

import cn.langya.sun.events.impl.player.EventUpdate;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.values.BoolValue;
import com.cubk.event.annotations.EventTarget;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @author LangYa
 * @date 2024/2/1 ÏÂÎç 04:34
 */

public class Scaffold extends Module {

    BoolValue tower = new BoolValue("Tower",true);

    public Scaffold() {
        super("Scaffold", Category.World);
    }

    public static Block getBlock(final BlockPos pos) {
        return mc.world.getBlockState(pos).getBlock();
    }

    public static Block getBlockUnderPlayer(final EntityPlayer player) {
        return getBlock(new BlockPos(player.posX, player.posY - 1.0, player.posZ));
    }


    @EventTarget
    void onU(EventUpdate e) {

        if (getBlockUnderPlayer(mc.player) instanceof BlockAir) {
            if (mc.player.onGround) {
                doRotation(e);
                KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(), true);
                placeBlock();
            }
        } else {
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(), false);
        }

    }

    private void placeBlock() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (Exception ignored) {}

        robot.mousePress(MouseEvent.BUTTON3_MASK);
        robot.mouseRelease(MouseEvent.BUTTON3_MASK);
    }

    private void doRotation(EventUpdate e) {
        e.setYaw(270);
        e.pitch = 80;

        if(mc.player.motionX == 0 && mc.player.motionZ == 0 && tower.get()) {
            e.setYaw(180);
            e.pitch = 90;
        }

    }

}
