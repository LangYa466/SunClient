package cn.langya.sun.modules.impl.client;

import cn.langya.sun.event.Event;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.utils.ClientUtils;

public class Client extends Module {

    public Client() {
        super("客户端", true, Category.Client);
    }

    @Event
    @Override
    public void onUpdate() {
        if (!getState()) return;
        ClientUtils.loginfo("Test");
    }
}
