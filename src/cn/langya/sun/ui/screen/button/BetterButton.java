package cn.langya.sun.ui.screen.button;

import cn.langya.sun.utils.render.RenderUtil;
import cn.langya.sun.utils.render.RoundedUtils;
import org.lwjgl.input.Keyboard;

import java.awt.*;

/**
 * @author LangYa
 * @ClassName BetterButton
 * @date 2024/1/13 下午 11:18
 * @Version 1.0
 */

public class BetterButton extends RenderUtil {
    static float x;
    static float y;
    static float width;
    static float height;
    static Color color;
    public BetterButton(float x, float y, float width, float height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public static void draw() {
        RoundedUtils.drawRound(x,y,width, height,5, color);
    }

    public static boolean isClick(final int mouseX, final int mouseY) {
        return isHovering(mouseX,mouseY) && Keyboard.getKeyCount() == Keyboard.KEY_LEFT;
    }
    private static boolean isHovering(final int mouseX, final int mouseY) {
        return mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
    }

}
