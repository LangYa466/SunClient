package cn.langya.sun.modules.impl.client;

import cn.langya.sun.events.impl.UpdateEvent;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.utils.ClientUtils;
import com.darkmagician6.eventapi.EventTarget;

public class Client extends Module {

    public Client() {
        super("客户端", true, Category.Client);
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        ClientUtils.loginfo("Test");
    }

}
