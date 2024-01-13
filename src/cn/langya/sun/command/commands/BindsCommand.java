//代码来自派蒙大神

package cn.langya.sun.command.commands;

import cn.langya.sun.Sun;
import cn.langya.sun.command.Command;
import cn.langya.sun.modules.Module;
import cn.langya.sun.utils.ClientUtils;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BindsCommand extends Command
{
    public BindsCommand() {
        super(new String[] { "binds" });
    }
    
    public List<String> autoComplete(final int arg, final String[] args) {
        return new ArrayList<String>();
    }
    
    public void run(final String[] args) {
        for (final Module module : Sun.moduleManager.getModules()) {
            if (module.keyCode == -1) {
                continue;
            }
            if(Objects.equals(Keyboard.getKeyName(module.keyCode), "NONE")) {
                continue;
            }

            ClientUtils.chatlog("&a[Binds]&f" + module.name + " :" + Keyboard.getKeyName(module.keyCode));
        }
    }
}
