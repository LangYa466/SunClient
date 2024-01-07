package cn.langya.sun.modules.impl.render

import com.cubk.event.annotations.EventTarget
import cn.langya.sun.events.impl.Render2DEvent
import cn.langya.sun.ui.FontManager
import cn.langya.sun.ui.Ui
import cn.langya.sun.utils.render.RenderUtil
import cn.langya.sun.values.StringValue
import java.awt.Color


/**
 * @author LangYa
 * @ClassName GameInfo
 * @date 2024/1/6 下午 04:30
 * @Version 1.0
 */

class Text: Ui("Text",0,0,100,100) {

    var text = StringValue("Text","SunClient")

    @EventTarget
    fun onRender2D(e: Render2DEvent) {
        FontManager.drawStringWithShadow(text.get(),x.toFloat(),y.toFloat(),-1)
        RenderUtil.drawRoundedRect(x,y,FontManager.getStringWidth(text.get()),FontManager.height,3,Color(0,0,0,150))
    }

}