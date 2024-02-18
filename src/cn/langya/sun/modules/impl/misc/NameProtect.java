package cn.langya.sun.modules.impl.misc;

import cn.langya.sun.events.impl.misc.EventText;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.values.ListValue;
import com.cubk.event.annotations.EventTarget;

/**
 * @author LangYa
 * @date 2024/2/3 ÏÂÎç 04:42
 */

public class NameProtect extends Module {

   public static ListValue name = new ListValue("CustomName","SunUser");

   public NameProtect() {
       super("NameProtect", Category.Misc);
   }

   @EventTarget
    void onT(EventText e) {

       if (e.text == null || mc.world == null || mc.player == null) return;

       if(e.text.contains(mc.player.getDisplayName().getFormattedText())) {
           e.text.replace(mc.player.getDisplayName().getFormattedText(),name.get());
       }
   }

}
