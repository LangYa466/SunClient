package cn.langya.sun.command;

import cn.langya.sun.command.commands.*;
import cn.langya.sun.events.impl.misc.EventChat;
import cn.langya.sun.utils.ClientUtils;
import com.cubk.event.annotations.EventTarget;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    @Getter
    private HashMap<String[], Command> commands = new HashMap();
    private String prefix = ".";

    public void loadCommands() {
        this.commands.put(new String[]{"help", "h"}, new HelpCommand());
        this.commands.put(new String[]{"bind", "b"}, new BindCommand());
        this.commands.put(new String[]{"toggle", "t"}, new ToggleCommand());
    }

    public boolean processCommand(String rawMessage) {
        boolean safe;
        if (!rawMessage.startsWith(this.prefix)) {
            return false;
        }
        boolean bl = safe = rawMessage.length() > 1;
        if (safe) {
            String beheaded = rawMessage.substring(1);
            String[] args = beheaded.split(" ");
            Command command = this.getCommand(args[0]);
            if (command != null) {
                if (!command.run(args)) {
                    ClientUtils.chatlog(command.usage());
                }
            } else {
                ClientUtils.chatlog("Try .help.");
            }
        } else {
            ClientUtils.chatlog("Try .help.");
        }
        return true;
    }

    private Command getCommand(String name) {
        for (Map.Entry<String[], Command> entry : this.commands.entrySet()) {
            for (String s : entry.getKey()) {
                if (!s.equalsIgnoreCase(name)) continue;
                return entry.getValue();
            }
        }
        return null;
    }

    @EventTarget
    public void processCommand(EventChat e) {
        if (processCommand(e.getMessage())) {
            e.cancel();
        }
    }

}

