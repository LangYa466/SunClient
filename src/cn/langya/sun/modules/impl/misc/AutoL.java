package cn.langya.sun.modules.impl.misc;

import cn.langya.sun.events.impl.player.EventAttack;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.utils.player.HYTUtils;
import cn.langya.sun.values.BoolValue;
import com.cubk.event.annotations.EventTarget;
import net.minecraft.util.text.TextComponentString;

/**
 * @author LangYa
 * @ClassName AutoL
 * @date 2024/1/14 下午 03:54
 * @Version 1.0
 */

public class AutoL extends Module {
    public static final BoolValue sendMessageValue = new BoolValue("SendMessage", true);
    public AutoL() {
        super("AutoL", Category.Misc);
    }

    public static int kill;

    @EventTarget
    void onAttack(EventAttack e) {

        if (HYTUtils.isInLobby()) {
            return;
        }

        if(e.target.isDead) ++ kill;

        if(sendMessageValue.get()) {
            mc.player.addChatMessage(new TextComponentString("Killed"+ kill + " Enemies"));

        }
    }
}
