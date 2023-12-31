package cn.langya.sun.modules;

import cn.enaium.cf4m.annotation.Event;
import cn.langya.sun.Sun;
import cn.langya.sun.events.impl.KeyPressEvent;
import cn.langya.sun.modules.impl.client.Client;
import cn.langya.sun.modules.impl.client.MusicPlayer;
import cn.langya.sun.modules.impl.combat.AntiKB;
import cn.langya.sun.modules.impl.combat.Criticals;
import cn.langya.sun.modules.impl.combat.KillAura;
import cn.langya.sun.modules.impl.move.Fly;
import cn.langya.sun.modules.impl.world.Eagle;
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
    private void onKeyPress(KeyPressEvent event){
        modules.stream().filter(module -> module.getKeyCode() == event.getKeyCode()).forEach(module -> module.toggle());
    }
    private void registerModule(Module module) {
        modules.add(module);
        Sun.eventManager.register(module);
    }

    public void registerModules() {
        registerModule(new Client());
        registerModule(new Eagle());
        registerModule(new AntiKB());
        registerModule(new Criticals());
        registerModule(new Fly());
    }
}