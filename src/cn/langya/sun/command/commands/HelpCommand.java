//代码来自派蒙大神

package cn.langya.sun.command.commands;

import cn.langya.sun.Sun;
import cn.langya.sun.command.Command;
import cn.langya.sun.utils.ClientUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelpCommand extends Command
{
    public HelpCommand() {
        super("help", "h");
    }
    
    public List<String> autoComplete(final int arg, final String[] args) {
        return new ArrayList<String>();
    }
    
    public void run(final String[] args) {
        ClientUtils.chatlog("§a[Commands]:§f");
        for (final Command command : Sun.commandManager.getCommands()) {
            ClientUtils.chatlog("§e." + Arrays.toString(command.getNames()));
        }
    }
}
