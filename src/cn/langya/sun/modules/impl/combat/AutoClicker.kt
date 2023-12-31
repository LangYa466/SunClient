package cn.langya.sun.modules.impl.combat

import cn.enaium.cf4m.annotation.Event
import cn.langya.sun.events.impl.UpdateEvent
import cn.langya.sun.modules.Category
import cn.langya.sun.modules.Module
import cn.langya.sun.values.BoolValue
import cn.langya.sun.values.DoubleValue
import javafx.scene.input.MouseEvent
import org.lwjgl.input.Keyboard
import java.awt.Robot
import java.awt.event.InputEvent


/**
 * @author LangYa
 * @ClassName AutoClicker
 * @date 2023/12/31 10:58
 * @Version 1.0
 */

class AutoClicker: Module("AutoClicker",true,Category.Combat) {

    private val left = BoolValue("Left Clicker", true)
    private  val right = BoolValue("Right Clicker", false)
    private val maxCps = DoubleValue("Left MaxCPS", 10.0, 20.0, 1.0)
    private val minCps = DoubleValue("Left MinCPS", 10.0, 20.0, 1.0)
    private val RmaxCps = DoubleValue("Right MaxCPS", 14.0, 20.0, 1.0)
    private val RminCps = DoubleValue("Right MinCPS", 10.0, 20.0, 1.0)
    private val robot: Robot = Robot()

    @Event
    fun  onUpdate(e: UpdateEvent)  {
        // 长按判断写不明白
        if(true) {
            if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) && left.get()) {
                leftclick(minCps.get(), maxCps.get())
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && right.get()) {
                rightclick(RminCps.get(), RmaxCps.get())
            }
        }

    }

    private fun leftclick(min: Double, max: Double) {
        while (min >= max) {
            robot.mousePress(InputEvent.BUTTON1_MASK)
            robot.mouseRelease(InputEvent.BUTTON1_MASK)
            min+1
        }
    }

    private fun rightclick(min: Double, max: Double) {
        while (min >= max) {
            robot.mousePress(InputEvent.BUTTON3_MASK)
            robot.mouseRelease(InputEvent.BUTTON3_MASK)
            min+1
        }
    }



}