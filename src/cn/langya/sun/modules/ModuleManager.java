package cn.langya.sun.modules;

import cn.langya.sun.Sun;
import cn.langya.sun.events.impl.misc.EventKeyPress;
import cn.langya.sun.events.impl.player.EventUpdate;
import cn.langya.sun.events.impl.render.EventRender2D;
import cn.langya.sun.utils.ClientUtils;
import com.cubk.event.annotations.EventTarget;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Getter
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
                        }
                        modules.add(module);
                    } catch (Exception ignored) {}
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
    private void onKeyPress(EventKeyPress event) {
        for (final Module m : modules) {
            if (m.keyCode == event.getKeyCode() && event.getKeyCode() != -1) {
                m.toggle();
            }
        }
    }


    @EventTarget
    private void onUpdate(EventUpdate event) {}

    @EventTarget
    public void onRender2D(EventRender2D event) {

    }

    private void registerModule(Module module) {
        modules.add(module);
        Sun.eventManager.register(module);
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