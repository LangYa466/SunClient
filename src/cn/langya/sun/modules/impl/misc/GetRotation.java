package cn.langya.sun.modules.impl.misc;

import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.utils.ClientUtils;
import cn.langya.sun.values.BoolValue;

/**
 * @author LangYa
 * @date 2024/2/1 ÏÂÎç 04:48
 */

public class GetRotation extends Module {
    BoolValue yaw = new BoolValue("Yaw",true);
    BoolValue pitch = new BoolValue("Pitch",true);

    public GetRotation() {
        super("GetRotation", Category.Misc);
        add(yaw,pitch);
    }

    @Override
    public void onEnable() {
        if(yaw.get()) ClientUtils.chatlog("Yaw: " + mc.player.rotationYaw);
        if(pitch.get()) ClientUtils.chatlog("Pitch: " + mc.player.rotationPitch);
        super.onEnable();
    }
}
