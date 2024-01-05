package cn.langya.sun.modules;

import cn.enaium.cf4m.annotation.Event;
import cn.langya.sun.Sun;
import cn.langya.sun.events.impl.KeyPressEvent;
import cn.langya.sun.events.impl.Render2DEvent;
import cn.langya.sun.modules.impl.client.Client;
import cn.langya.sun.modules.impl.combat.AntiKB;
import cn.langya.sun.modules.impl.combat.AutoClicker;
import cn.langya.sun.modules.impl.combat.Critical;
import cn.langya.sun.modules.impl.combat.KillAura;
import cn.langya.sun.modules.impl.misc.GrimAC;
import cn.langya.sun.modules.impl.move.Fly;
import cn.langya.sun.modules.impl.world.Eagle;
import cn.langya.sun.ui.impl.notification.NotificationType;
import cn.langya.sun.utils.ClientUtils;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    public List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        ClientUtils.loginfo("[模块系统] 加载模块中...");
        registerModules();
        ClientUtils.loginfo("[模块系统] 已加载模块: " + modules.size());
        Sun.eventManager.register(this);
    }

    @Event
    private void onKeyPress(KeyPressEvent event) {
        modules.stream().filter(module -> module.getKeyCode() == event.getKeyCode()).forEach(Module::toggle);
    }

    @Event
    public void onRender2D(Render2DEvent event) {
        for (Module module : Sun.moduleManager.modules){
            Sun.uiManager.addNotification(
                    "Notifications",
                    (module.isEnabled() ? "Enabled " : "Disabled ") + module.name,
                    (module.isEnabled() ? NotificationType.SUCCESS : NotificationType.ERROR)
            );
            break;
        }
    }

    private void registerModule(Module module) {
        modules.add(module);
        Sun.eventManager.register(module);
    }

    public void registerModules() {
        registerModule(new Client());
        registerModule(new KillAura());
        registerModule(new Eagle());
        registerModule(new AntiKB());
        registerModule(new Critical());
        registerModule(new Fly());
        registerModule(new AutoClicker());
        registerModule(new GrimAC());
    }
}