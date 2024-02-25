package cn.langya.sun.modules.impl.combat;

import cn.langya.sun.events.impl.misc.EventPacketRead;
import cn.langya.sun.events.impl.player.EventUpdate;
import cn.langya.sun.modules.Category;
import cn.langya.sun.modules.Module;
import cn.langya.sun.values.ListValue;
import com.cubk.event.annotations.EventTarget;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.network.play.server.SPacketEntityVelocity;

/**
 * @author LangYa
 * @date 2024/2/25 ÖĞÎç 11:57
 */

@Getter
@Setter
public class Velocity extends Module {

    private ListValue mode = new ListValue("Mode","Cancel");

    public Velocity()  {
        super("Velocity", Category.Combat);
        hurt = false;
        mode.getValues().add("Cancel");
        mode.getValues().add("Vanilla");
    }

    boolean hurt;


    @EventTarget
    void onU(EventUpdate e) {

        if(!hurt) return;

        switch (mode.get()) {
            case "Vanilla":
                mc.player.motionX = 0;
                mc.player.motionY = 0;
                mc.player.motionZ = 0;
                break;
        }
    }

    @EventTarget
    void onPr(EventPacketRead e) {
        if (e.packet instanceof SPacketEntityVelocity) {

            hurt = true;

            switch (mode.get()) {
                case "Cancel":
                    e.cancel();
                    break;
            }

        } else if (mc.player.hurtTime >= 0) {
            hurt = false;
        } else {
            hurt = false;
        }

    }

}
