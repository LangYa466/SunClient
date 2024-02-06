package cn.langya.sun.modules.impl.client;

import cn.langya.sun.events.impl.misc.EventText;
import cn.langya.sun.events.impl.misc.EventTick;
import cn.langya.sun.events.impl.world.EventWorldLoad;
import cn.langya.sun.utils.misc.TimeUtil;
import cn.langya.sun.utils.misc.WebUtils;
import cn.langya.sun.values.BoolValue;
import com.cubk.event.annotations.EventTarget;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import net.minecraft.util.text.TextFormatting;

import java.io.IOException;

public class Client extends Module {

    final BoolValue memoryfix = new BoolValue("MemoryFix",true);

    public Client() {
        super("Client", false, Category.Client);
        add(memoryfix);
    }

    /*

    @EventTarget
    void onTick(EventTick e) throws IOException {
        TimeUtil t = new TimeUtil();
        if(t.hasTimePassed(300001)) {
            WebUtils.get("https://.cloud/updateuser.php?username=" + mc.player.getDisplayName());
        }
    }

    @EventTarget
    void onT(EventText e) {
        new Thread(() -> {
            try {
                if(e.text.equals(WebUtils.get("https://.cloud/user.txt"))) {
                    e.text.concat(String.format("[%sSunClient] ",TextFormatting.RED));
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

     */
    @EventTarget
    void onWorldLoad(EventWorldLoad e) {
        if(memoryfix.get()) System.gc();
    }
}
