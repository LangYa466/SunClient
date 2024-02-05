package cn.langya.sun.command.commands;


import cn.langya.sun.Sun;
import cn.langya.sun.command.Command;
import cn.langya.sun.modules.Module;
import cn.langya.sun.utils.ClientUtils;

public class ToggleCommand
implements Command {
    @Override
    public boolean run(String[] args) {
        if (args.length == 2) {
            Module module = Sun.moduleManager.getModule(args[1]);
            if (module == null) {
                ClientUtils.chatlog("The module with the name " + args[1] + " does not exist.");
                return true;
            }
            module.setState(!module.state);
            return true;
        }
        return false;
    }

    @Override
    public String usage() {
        return "USAGE: .toggle [module]";
    }
}

