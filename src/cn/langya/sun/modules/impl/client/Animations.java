package cn.langya.sun.modules.impl.client;

import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.values.BoolValue;

/**
* @author LangYa
* @ClassName Animations
* @date 2024/1/14 下午 08:13
* @Version 1.0
*/

public class Animations extends Module {
    public static final BoolValue progressValue = new BoolValue("Progress", true);

    public Animations() {
        super("Animations", Category.Client);
        add(progressValue);
    }

}
