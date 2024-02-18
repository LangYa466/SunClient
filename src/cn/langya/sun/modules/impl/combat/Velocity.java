package cn.langya.sun.modules.impl.combat;

import cn.langya.sun.events.impl.player.EventUpdate;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import com.cubk.event.annotations.EventTarget;

/**
 * @author LangYa
 * @date 2024/2/18 обнГ 02:37
 */

public class Velocity extends Module {


    public Velocity()  {
        super("Velocity", Category.Combat);
    }

    @EventTarget
    void onU(EventUpdate e) {

    }

}
