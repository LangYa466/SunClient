package cn.langya.sun.ui;

import cn.langya.sun.Sun;
import cn.langya.sun.ui.impl.Test;
import cn.langya.sun.utils.Utils;

import java.util.ArrayList;

/**
 * @author LangYa
 * @date 2024/2/3 ÏÂÎç 03:50
 */

public class UIManager extends Utils {

    public static ArrayList<UI> uis = new ArrayList<>();

    public void UIManager() {
        Sun.eventManager.register(this);
        addUI(new Test());
    }

    public static void addUI(UI ui){
        uis.add(ui);
        Sun.eventManager.register(ui);
    }


}
