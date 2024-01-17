package cn.langya.sun.modules.impl.misc;

import cn.langya.sun.events.impl.UpdateEvent;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import com.cubk.event.annotations.EventTarget;

/**
 * @author LangYa
 * @ClassName NoClickDelay
 * @date 2024/1/17 обнГ 08:49
 * @Version 1.0
 */

public class NoClickDelay extends Module {
    public NoClickDelay() {
        super("NoClickDelay", Category.Misc);
    }

    @EventTarget
    void onUpdate(UpdateEvent e) {
        if (mc.player != null && mc.world != null) {
            mc.leftClickCounter = 0;
        }
    }
}
