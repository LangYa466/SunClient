package cn.langya.sun.modules;

import cn.langya.sun.Sun;
import cn.langya.sun.events.impl.KeyPressEvent;
import cn.langya.sun.events.impl.Render2DEvent;
import cn.langya.sun.events.impl.UpdateEvent;
import cn.langya.sun.modules.impl.client.ClickGui;
import cn.langya.sun.modules.impl.client.Client;
import cn.langya.sun.modules.impl.client.HUD;
import cn.langya.sun.modules.impl.combat.AntiKB;
import cn.langya.sun.modules.impl.combat.AutoClicker;
import cn.langya.sun.modules.impl.combat.Critical;
import cn.langya.sun.modules.impl.combat.KillAura;
import cn.langya.sun.modules.impl.misc.GrimAC;
import cn.langya.sun.modules.impl.misc.Teams;
import cn.langya.sun.modules.impl.render.FullBright;
import cn.langya.sun.modules.impl.world.PlayerWarn;
import cn.langya.sun.modules.impl.move.Fly;
import cn.langya.sun.modules.impl.move.InvMove;
import cn.langya.sun.modules.impl.move.NoWater;
import cn.langya.sun.modules.impl.move.Sprint;
import cn.langya.sun.modules.impl.world.Eagle;
import cn.langya.sun.utils.ClientUtils;
import com.cubk.event.annotations.EventTarget;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    public List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        ClientUtils.loginfo("[ModuleManager] Loading...");
        registerModules();
        ClientUtils.loginfo("[ModuleManager] Load Modules: " + modules.size());
        Sun.eventManager.register(this);
    }

    @EventTarget
    private void onKeyPress(KeyPressEvent event) {
        for (final Module m : modules) {
            if (m.keyCode == event.getKeyCode() && event.getKeyCode() != -1) {
                m.toggle();
            }
        }
        if (Keyboard.KEY_RSHIFT == event.getKeyCode() && event.getKeyCode() != -1) {
            new ClickGui().setState(true);
        }
    }


    @EventTarget
    private void onUpdate(UpdateEvent event) {}

    @EventTarget
    public void onRender2D(Render2DEvent event) {

    }

    private void registerModule(Module module) {
        modules.add(module);
        Sun.eventManager.register(module);
    }

    public void registerModules() {
        registerModule(new Client());
        registerModule(new HUD());
        registerModule(new ClickGui());
        registerModule(new KillAura());
        registerModule(new Eagle());
        registerModule(new AntiKB());
        registerModule(new Critical());
        registerModule(new Fly());
        registerModule(new AutoClicker());
        registerModule(new GrimAC());
        registerModule(new InvMove());
        registerModule(new Sprint());
        registerModule(new NoWater());
        registerModule(new PlayerWarn());
        registerModule(new Teams());
        registerModule(new FullBright());
    }

    public <T extends Module> T getModule(final Class<T> cls) {
        for (final Module m : this.modules) {
            if (m.getClass() == cls) {
                return (T)m;
            }
        }
        return null;
    }

    public Module getModule(final String name) {
        for (final Module m : modules) {
            if (m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }

    public List<Module> getModsByCategory(final Category m) {
        final List<Module> findList = new ArrayList<Module>();
        for (final Module mod : this.modules) {
            if (mod.getCategory() == m) {
                findList.add(mod);
            }
        }
        return findList;
    }

}