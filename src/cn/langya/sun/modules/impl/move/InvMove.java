// By PaiMon
package cn.langya.sun.modules.impl.move;

import cn.enaium.cf4m.annotation.Event;
import cn.langya.sun.events.impl.UpdateEvent;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.ui.screen.clickgui.ClickGui;
import net.minecraft.client.settings.*;
import org.lwjgl.input.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.gui.inventory.*;
import java.util.*;

public class InvMove extends Module
{
    private static final List<KeyBinding> keys;
    
    public InvMove() {
        super("InvMove", Category.Move);
    }
    
    public static void updateStates() {
        if (mc.currentScreen != null) {
            for (final KeyBinding k : keys) {
                k.pressed =GameSettings.isKeyDown(k);
                if (Keyboard.isKeyDown(Keyboard.KEY_UP) && mc.player.rotationPitch > -90.0f) {
                    final EntityPlayerSP thePlayer = mc.player;
                    thePlayer.rotationPitch -= 5.0f;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) && mc.player.rotationPitch < 90.0f) {
                    final EntityPlayerSP thePlayer2 = mc.player;
                    thePlayer2.rotationPitch += 5.0f;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
                    final EntityPlayerSP thePlayer3 = mc.player;
                    thePlayer3.rotationYaw -= 5.0f;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                    final EntityPlayerSP thePlayer4 = mc.player;
                    thePlayer4.rotationYaw += 5.0f;
                }
            }
        }
    }
    
    @Event
    public void onMotion(final UpdateEvent event) {
        if (mc.currentScreen instanceof GuiContainer || mc.currentScreen instanceof ClickGui ) {
            updateStates();
        }
    }
    
    static {
        keys = Arrays.asList(mc.gameSettings.keyBindForward, mc.gameSettings.keyBindBack, mc.gameSettings.keyBindLeft, mc.gameSettings.keyBindRight, mc.gameSettings.keyBindJump);
    }
}
