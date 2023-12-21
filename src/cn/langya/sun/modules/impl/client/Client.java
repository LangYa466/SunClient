package cn.langya.sun.modules.impl.client;

import cn.langya.sun.event.impl.player.UpdateEvent;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.utils.ClientUtils;

public class Client extends Module {

    public Client() {
        super("客户端", true, Category.Client);
    }

    @Override
    public void onUpdateEvent(UpdateEvent event) {
        ClientUtils.loginfo("UpdateEventTest");
    }

    @Override
    public void onEnable() {
        ClientUtils.loginfo("onEnable");
        super.onEnable();
    }

    @Override
    public void onDisable() {
        ClientUtils.loginfo("onDisable");
        super.onDisable();
    }
}
