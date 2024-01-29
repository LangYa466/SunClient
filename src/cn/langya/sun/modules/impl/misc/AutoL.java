package cn.langya.sun.modules.impl.misc;

import cn.langya.sun.events.impl.player.AttackEvent;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import com.cubk.event.annotations.EventTarget;

/**
 * @author LangYa
 * @ClassName AutoL
 * @date 2024/1/14 下午 03:54
 * @Version 1.0
 */

public class AutoL extends Module {
    public AutoL() {
        super("AutoL", Category.Misc);
    }

    public static int kill;

    @EventTarget
    void onAttack(AttackEvent e) {
        if(e.target.isDead) ++ kill;
    }
}
