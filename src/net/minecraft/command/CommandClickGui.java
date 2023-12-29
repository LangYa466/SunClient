package net.minecraft.command;

import cn.langya.sun.modules.impl.client.Client;
import cn.langya.sun.ui.screen.clickgui.ClickGui;
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
        Minecraft.getMinecraft().displayGuiScreen(new ClickGui());
    }

}
