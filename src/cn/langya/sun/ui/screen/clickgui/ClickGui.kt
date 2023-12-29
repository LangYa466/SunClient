package cn.langya.sun.ui.screen.clickgui;

import cn.langya.sun.Sun
import cn.langya.sun.modules.Module
import cn.langya.sun.utils.render.RenderUtils
import net.minecraft.client.audio.PositionedSoundRecord
import net.minecraft.client.gui.GuiScreen
import net.minecraft.init.SoundEvents
import java.awt.Color

class ClickGui : GuiScreen() {

    private val modulePositions = mutableMapOf<Module, Pair<Int, Int>>()

    override fun initGui() {
        super.initGui()

        val modules = Sun.moduleManager!!.modules
        val moduleCategories = modules.groupBy { it.category }

        var y = (height - 10 * modules.size) / 2

        for ((_, categoryModules) in moduleCategories) {
            for (module in categoryModules) {
                modulePositions[module] = Pair(width / 2 - fontRendererObj.getStringWidth(module.name) / 2 - 5, y)
                y += 10
            }

            y += 10
        }
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawRect(0, 0, width, height, Color(0, 0, 0, 150).rgb)

        for ((module, position) in modulePositions) {
            val category = module.category.displayName
            RenderUtils.drawRoundedRect(position.first - 2, position.second - 2, fontRendererObj.getStringWidth("${module.name}: ${if (module.state) "开启" else "关闭"}") + 4, fontRendererObj.FONT_HEIGHT + 4, 5, Color(0, 0, 0, 150))
            drawString(mc.fontRendererObj, "$category > ${module.name}: ${if (module.state) "开启" else "关闭"}", position.first, position.second, -1)
        }

        super.drawScreen(mouseX, mouseY, partialTicks)
    }

    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        super.mouseClicked(mouseX, mouseY, mouseButton)

        for ((module, position) in modulePositions) {
            val (x, y) = position
            if (mouseX >= x && mouseY >= y && mouseX < x + fontRendererObj.getStringWidth(module.name) && mouseY < y + 10) {
                module.state = !module.state

                mc.soundHandler.playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f))
            }
        }
    }
}