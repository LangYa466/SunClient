package cn.langya.sun.event.impl.render;

import cn.langya.sun.event.Event;

public class ShaderEvent extends Event {

    private final boolean bloom;


    public ShaderEvent(boolean bloom){
        this.bloom = bloom;
    }

    // @Exclude(Strategy.NAME_REMAPPING)
    public boolean isBloom() {
        return bloom;
    }


}
