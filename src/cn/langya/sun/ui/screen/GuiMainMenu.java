package cn.langya.sun.ui.screen;

import net.minecraft.client.gui.*;


public class GuiMainMenu extends GuiScreen {

    public void initGui() {
        int defaultHeight = height / 4 + 30;
        int defaultWidth = width / 2 - 60;
        int buttonWidth = 120;
        int buttonHeight = 20;
        buttonList.add(new GuiButton(0, defaultWidth, defaultHeight, buttonWidth, buttonHeight, "Single Player"));
        buttonList.add(new GuiButton(1, defaultWidth, defaultHeight + 25, buttonWidth, buttonHeight, "Multi Player"));
        buttonList.add(new GuiButton(2, defaultWidth, defaultHeight + 50, buttonWidth, buttonHeight, "Game Options"));
        buttonList.add(new GuiButton(3, defaultWidth, defaultHeight + 75, buttonWidth, buttonHeight, "Quit Game"));
        super.initGui();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawBackground(0);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                mc.displayGuiScreen(new GuiWorldSelection(this));
                break;
            case 1:
                mc.displayGuiScreen(new GuiMultiplayer(this));
                break;
            case 2:
                mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
                break;
            case 3:
                mc.shutdown();
                break;
        }
    }
}