package cn.langya.sun.modules.impl.move;

import com.cubk.event.annotations.EventTarget;
import cn.langya.sun.events.impl.UpdateEvent;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class Sprint extends Module
{
    public Sprint() {
        super("Sprint", Category.Move, Keyboard.KEY_Z);
    }
    
    @EventTarget
    public void onUpdate(final UpdateEvent event) {
        Sprint.mc.gameSettings.keyBindSprint.pressed = true;
    }
}
