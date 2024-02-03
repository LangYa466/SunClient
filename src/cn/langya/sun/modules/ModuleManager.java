package cn.langya.sun.modules;

import cn.langya.sun.Sun;
import cn.langya.sun.events.impl.misc.KeyPressEvent;
import cn.langya.sun.events.impl.render.Render2DEvent;
import cn.langya.sun.events.impl.player.UpdateEvent;
import cn.langya.sun.modules.impl.client.ClickGui;
import cn.langya.sun.modules.impl.client.Client;
import cn.langya.sun.modules.impl.client.HUD;
import cn.langya.sun.modules.impl.combat.AntiKB;
import cn.langya.sun.modules.impl.combat.AutoClicker;
import cn.langya.sun.modules.impl.misc.GrimAC;
import cn.langya.sun.modules.impl.misc.Teams;
import cn.langya.sun.modules.impl.render.FullBright;
import cn.langya.sun.modules.impl.world.PlayerWarn;
import cn.langya.sun.modules.impl.move.InvMove;
import cn.langya.sun.modules.impl.move.NoWater;
import cn.langya.sun.modules.impl.move.Sprint;
import cn.langya.sun.modules.impl.world.Eagle;
import cn.langya.sun.utils.ClientUtils;
import com.cubk.event.annotations.EventTarget;
import org.lwjgl.input.Keyboard;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        ClientUtils.loginfo("[ModuleManager] Loading...");
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
                            /*Object obj;
                            try {
                                if ((obj = field.get(module)) instanceof AbstractValue) {
                                    module.add((AbstractValue<?>) obj);
                                }
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }*/
                        }
                        modules.add(module);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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
        registerModule(new Eagle());
        registerModule(new AntiKB());
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
    public List<Module> getModules() {
        return modules;
    }
}