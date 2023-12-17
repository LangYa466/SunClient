package net.minecraft.command;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;

public class CommandClickGui extends CommandBase {

    @Override
    public String getCommandName() {
        return "clickgui";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/clickgui";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        //inecraft.getMinecraft().displayGuiScreen(new ClickGui());
    }

}
