package cn.langya.sun.command.commands;

import cn.langya.sun.Sun;
import cn.langya.sun.command.Command;
import cn.langya.sun.modules.Module;
import cn.langya.sun.utils.ClientUtils;
import org.lwjgl.input.Keyboard;

public class BindCommand
implements Command {
    @Override
    public boolean run(String[] args) {
        if (args.length == 3) {
            Module m = Sun.moduleManager.getModule(args[1]);
            if (m == null) {
                return false;
            }
            m.setKeyCode(Keyboard.getKeyIndex(args[2].toUpperCase()));
            ClientUtils.chatlog(m.getName() + " has been bound to " + args[2] + ".");
            return true;
        }
        return false;
    }

    @Override
    public String usage() {
        return "USAGE: .bind [module] [key]";
    }
}

