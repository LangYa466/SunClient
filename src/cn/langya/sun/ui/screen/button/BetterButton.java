package cn.langya.sun.ui.screen.button;

import cn.langya.sun.ui.font.FontDrawer;
import cn.langya.sun.utils.render.RenderUtil;
import cn.langya.sun.utils.render.RoundedUtil;

import java.awt.*;

/**
 * @author LangYa
 * @ClassName BetterButton
 * @date 2024/1/13 下午 11:18
 * @Version 1.0
 */

public class BetterButton extends RenderUtil {
    protected float x, y;
    protected float width, height;
    Color color;
    public int hoverFade = 0;
    public BetterButton(float x, float y, float width, float height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    public BetterButton(double x, double y, double width, double height, Color color) {
        this.x = (float) x;
        this.y = (float) y;
        this.width = (float) width;
        this.height = (float) height;
        this.color = color;
    }

    public void draw(String text, FontDrawer fontDrawer, int mouseX, int mouseY) {
        boolean hovered = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
        if (hovered) {
            if (hoverFade < 40) hoverFade += 10;
        } else {
            if (hoverFade > 0) hoverFade -= 10;
        }

        RoundedUtil.drawRound(x,y,width, height,5, color);
        RoundedUtil.drawRound(x,y + width / 5 - 4,width, 3,2, Color.white);
        fontDrawer.drawCenteredString(text,RenderUtil.getWidth() - (x + width) + 130,y + 5,-1);
    }


}
