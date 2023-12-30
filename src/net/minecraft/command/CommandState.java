package net.minecraft.command;

import cn.langya.sun.modules.impl.client.Client;
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
        final Client client = new Client();
        client.setState(!client.isEnabled());
    }

}
