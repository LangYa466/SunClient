//代码来自派蒙大神
package cn.langya.sun.command.commands;

import cn.langya.sun.Sun;
import cn.langya.sun.command.Command;
import cn.langya.sun.modules.Module;
import cn.langya.sun.utils.ClientUtils;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BindCommand
        extends Command {
    public BindCommand() {
        super("bind", "b");
    }

    @Override
    public List<String> autoComplete(int arg, String[] args) {
        String prefix = "";
        boolean flag = false;
        if (arg == 0 || args.length == 0) {
            flag = true;
        } else if (arg == 1) {
            flag = true;
            prefix = args[0];
        }
        if (flag) {
            String finalPrefix = prefix;
            return Sun.moduleManager.modules.stream().filter(mod -> mod.name.toLowerCase().startsWith(finalPrefix)).map(Module::getName).collect(Collectors.toList());
        }
        if (arg == 2) {
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add("none");
            arrayList.add("show");
            return arrayList;
        }
        return new ArrayList<String>();
    }

    @Override
    public void run(String[] args) {
        if (args.length == 2) {
            Module m = Sun.moduleManager.getModule(args[0]);
            if (m != null) {
                int key = Keyboard.getKeyIndex(args[1].toUpperCase());
                m.setKeyCode(key);
                ClientUtils.chatlog(TextFormatting.GREEN + "Success bound " + m.getName() + " to " + Keyboard.getKeyName(m.keyCode) + "!");
            } else {
                ClientUtils.chatlog(TextFormatting.RED + "Module not found!");
            }
        } else {
            ClientUtils.chatlog(TextFormatting.RED + "Usage: bind <Module> <Key>");
        }
    }
}
