package cn.langya.sun.event;

import cn.langya.sun.modules.impl.client.Client;
import cn.langya.sun.modules.impl.combat.KillAura;
import cn.langya.sun.utils.ClientUtils;

public class EventManager {

    public void onUpdate() {
        new Client().onUpdate();
        new KillAura().onUpdate();
        //       ClientUtils.loginfo("onUpdate Event");
    }

    public void onMove() {

    }

    public void onRender2D() {
        //    ClientUtils.loginfo("onRender2D Event");
    }

    public void onRender3D() {
        //    ClientUtils.loginfo("onRender3D Event");
    }

    public void onAttack() {
        //   ClientUtils.loginfo("onAttack Event");
    }
}
