package cn.langya.sun.modules.impl.move;

import com.cubk.event.annotations.EventTarget;
import cn.langya.sun.events.impl.UpdateEvent;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;

public class Sprint extends Module
{
    public Sprint() {
        super("自动疾跑", Category.Move);
        this.setState(true);
    }
    
    @EventTarget
    public void onUpdate(final UpdateEvent event) {
        Sprint.mc.gameSettings.keyBindSprint.pressed = true;
    }
}
