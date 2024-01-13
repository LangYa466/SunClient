package cn.langya.sun.modules.impl.client;

import cn.langya.sun.events.impl.WorldLoadEvent;
import cn.langya.sun.values.BoolValue;
import cn.langya.sun.values.DoubleValue;
import com.cubk.event.annotations.EventTarget;
import cn.langya.sun.events.impl.Render2DEvent;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;

public class Client extends Module {

    final BoolValue memoryfix = new BoolValue("MemoryFix",true);

    public Client() {
        super("Client", false, Category.Client);
        add(memoryfix);
    }

    @EventTarget
    void onWorldLoad(WorldLoadEvent e) {
        if(memoryfix.get()) System.gc();
    }
}
