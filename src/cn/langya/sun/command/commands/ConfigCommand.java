//代码来自派蒙大神

package cn.langya.sun.command.commands;


import cn.langya.sun.Sun;
import cn.langya.sun.command.Command;
import cn.langya.sun.utils.ClientUtils;

import java.util.ArrayList;
import java.util.List;

public class ConfigCommand extends Command
{
    public ConfigCommand() {
        super(new String[] { "config", "cfg" });
    }
    
    public List<String> autoComplete(final int arg, final String[] args) {
        return new ArrayList<String>();
    }
    
    public void run(final String[] args) {
        if (args.length == 2) {
            final String s = args[0];
            switch (s) {
                case "save": {
                    final String name = args[1];
                    if (!name.isEmpty()) {
                    //    Sun.configManager.saveUserConfig(name + ".json");
                        ClientUtils.chatlog(  "Config " + name + " Saved!");
                        break;
                    }
                    ClientUtils.chatlog("?");
                    break;
                }
                case "load": {
                    final String name = args[1];
                    if (!name.isEmpty()) {
                     //   Sun.configManager.loadUserConfig(name + ".json");
                        ClientUtils.chatlog(  "Config " + name + " Loaded!");
                        break;
                    }
                    ClientUtils.chatlog("?");
                    break;
                }
            }
        }
        else {
            ClientUtils.chatlog("Usage: config save/load <name>");
        }
    }
}
