package cn.langya.sun.command.commands;

import cn.langya.sun.Sun;
import cn.langya.sun.command.Command;
import cn.langya.sun.utils.ClientUtils;

public class HelpCommand
implements Command {
    @Override
    public boolean run(String[] args) {
        ClientUtils.chatlog("Here are the list of commands:");
        for (Command c : Sun.commandManager.getCommands().values()) {
            ClientUtils.chatlog(c.usage());
        }
        return true;
    }

    @Override
    public String usage() {
        return "USAGE: .help";
    }
}

