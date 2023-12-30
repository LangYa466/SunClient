package cn.langya.sun.modules.impl.client;

import cn.enaium.cf4m.annotation.Event;
import cn.langya.sun.events.impl.Render2DEvent;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;

public class Client extends Module {

    public Client() {
        super("客户端", true, Category.Client);
    }
    @Event
    public void onRender2D(Render2DEvent event) {
        if(!isEnabled()) return;
    }
}
