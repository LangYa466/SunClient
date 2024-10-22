package cn.langya.sun.modules;

import cn.langya.sun.Sun;
import cn.langya.sun.events.impl.misc.EventKeyPress;
import cn.langya.sun.events.impl.misc.EventTick;
import cn.langya.sun.events.impl.player.EventUpdate;
import cn.langya.sun.events.impl.render.EventRender2D;
import cn.langya.sun.modules.impl.move.*;
import cn.langya.sun.modules.impl.world.*;
import cn.langya.sun.modules.impl.client.*;
import cn.langya.sun.modules.impl.combat.*;
import cn.langya.sun.modules.impl.misc.*;
import cn.langya.sun.modules.impl.render.*;
import cn.langya.sun.modules.impl.player.*;
import cn.langya.sun.utils.ClientUtils;
import com.cubk.event.annotations.EventTarget;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Getter
public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        ClientUtils.loginfo("[ModuleManager] Loading...");
        /*
        try {
            List<Class<?>> classes = getAllClasses(Module.class.getPackage().getName());

            for (Class<?> clazz : classes) {
                if (!Modifier.isAbstract(clazz.getModifiers()) && !Modifier.isInterface(clazz.getModifiers()) && Module.class.isAssignableFrom(clazz)) {
                    try {
                        Module module = (Module) clazz.getDeclaredConstructor().newInstance();
                        for (Field field : module.getClass().getDeclaredFields()) {
                            if (!field.isAccessible()) {
                                field.setAccessible(true);
                            }
                        }
                        modules.add(module);
                    } catch (Exception ignored) {}
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        */

        registerModules();

        ClientUtils.loginfo("[ModuleManager] Load Modules: " + modules.size());
        Sun.eventManager.register(this);
    }

    private List<Class<?>> getAllClasses(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }

        List<Class<?>> classes = new ArrayList<>();

        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }

        return classes;
    }

    private List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();

        if (!directory.exists()) {
            return classes;
        }

        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    classes.addAll(findClasses(file, packageName + "." + file.getName()));
                } else if (file.getName().endsWith(".class")) {
                    Class<?> clazz = Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
                    classes.add(clazz);
                }
            }
        }

        return classes;
    }
    @EventTarget
    private void onKeyPress(EventKeyPress event) {
        for (Module m : modules) {
            if (m.keyCode == event.getKeyCode() && event.getKeyCode() != -1) {
                m.toggle();
            }
        }
    }

    private void registerModule(Module module) {
        modules.add(module);
        Sun.eventManager.register(module);
    }

    public void registerModules() {
        registerModule(new Client());
        registerModule(new HUD());
        registerModule(new Animations());
        registerModule(new ClickGui());
        registerModule(new Eagle());
        registerModule(new KillAura());
        registerModule(new GrimAC());
        registerModule(new InvMove());
        registerModule(new Sprint());
        registerModule(new Scaffold());
        registerModule(new PlayerWarn());
        registerModule(new Teams());
        registerModule(new FullBright());
        registerModule(new AutoL());
        registerModule(new AutoLobby());
        registerModule(new GetRotation());
        registerModule(new NameProtect());
        registerModule(new NoClickDelay());
    }

    public <T extends Module> T getModule(final Class<T> tClass) {
        for (final Module m : this.modules) {
            if (m.getClass() == tClass) {
                return (T) m;
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