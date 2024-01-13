// Decompiled with: CFR 0.152
// Class Version: 8
package cn.langya.sun.command.commands;

import cn.langya.sun.Sun;
import cn.langya.sun.command.Command;
import cn.langya.sun.modules.Module;
import cn.langya.sun.utils.ClientUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ToggleCommand
        extends Command {
    public ToggleCommand() {
        super("toggle", "t");
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
            return Sun.moduleManager.getModules().stream().map(Module::getName).filter(name -> name.toLowerCase().startsWith(finalPrefix)).collect(Collectors.toList());
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
        if (args.length == 1) {
            Module m = Sun.moduleManager.getModule(args[0]);
            if (m != null) {
                m.toggle();
            } else {
                ClientUtils.chatlog("Module not found!");
            }
        } else {
            ClientUtils.chatlog("Usage: t <Module>");
        }
    }
}
