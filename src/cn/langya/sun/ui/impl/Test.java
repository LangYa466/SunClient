package cn.langya.sun.ui.impl;

import cn.langya.sun.events.impl.render.Render2DEvent;
import cn.langya.sun.ui.UI;
import cn.langya.sun.utils.render.RoundedUtil;
import com.cubk.event.annotations.EventTarget;

import java.awt.*;

/**
 * @author LangYa
 * @date 2024/2/3 обнГ 04:01
 */

public class Test extends UI {


    public Test() {
        super("Test");
        setHeight(50);
        setWitdh(50);
        setX(50);
        setY(50);
    }

    @EventTarget
    void onR2D(Render2DEvent e) {
        RoundedUtil.drawRound(50,50,50,50,0, Color.WHITE);
    }


}
