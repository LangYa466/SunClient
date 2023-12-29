package cn.langya.sun.modules.impl.render

import cn.enaium.cf4m.annotation.Event
import cn.langya.sun.Sun
import cn.langya.sun.events.Render2DEvent
import cn.langya.sun.events.UpdateEvent
import cn.langya.sun.ui.FontManager
import cn.langya.sun.ui.Ui
import cn.langya.sun.utils.render.RenderUtils
import cn.langya.sun.values.BoolValue
import cn.langya.sun.values.IntValue
import java.awt.Color


/**
 * @author LangYa
 * @ClassName Arraylist
 * @date 2023/12/28 21:19
 * @Version 1.0
 */


class ArrayList(x: Int, y: Int, witdh: Int, height: Int): Ui(x,y, witdh, height) {


    val shadow = BoolValue("Shaddow",false)
    val radius = IntValue("Radius",6,10,0)

    var yPosition = 50

    @Event
    fun onRender2D(event: Render2DEvent) {

        var initialYPosition = yPosition

        for (module in Sun.moduleManager.modules) {
            if (module.array && module.state) {
                val displayString = module.name
                width = FontManager.height
                height = FontManager.getStringWidth(displayString)
                y = initialYPosition
                FontManager.drawString(displayString, x, y, -1)
                initialYPosition += FontManager.height + 2
            }
        }



    }


}