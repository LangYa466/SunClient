package net.minecraft.command;

import cn.langya.sun.modules.impl.client.Client;
import cn.langya.sun.modules.impl.combat.KillAura;
import net.minecraft.server.MinecraftServer;

public class CommandState extends CommandBase {

    @Override
    public String getCommandName() {
        return "state";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/state";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

        new Client().setState(!new Client().getState());

    }

}
