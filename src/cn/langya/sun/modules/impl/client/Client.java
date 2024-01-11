package cn.langya.sun.modules.impl.client;

import com.cubk.event.annotations.EventTarget;
import cn.langya.sun.events.impl.Render2DEvent;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;

public class Client extends Module {


    public Client() {
        super("Client", false, Category.Client);
        setState(true);
    }
    @EventTarget
    public void onRender2D(Render2DEvent event) {
        if(!isEnabled()) return;
    }
}
