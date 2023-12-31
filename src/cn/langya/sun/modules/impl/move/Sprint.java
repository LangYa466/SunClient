package cn.langya.sun.modules.impl.move;

import cn.enaium.cf4m.annotation.Event;
import cn.langya.sun.events.impl.UpdateEvent;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;

public class Sprint extends Module
{
    public Sprint() {
        super("Sprint", Category.Move);
        this.setState(true);
    }
    
    @Event
    public void onUpdate(final UpdateEvent event) {
        Sprint.mc.gameSettings.keyBindSprint.pressed = true;
    }
}
