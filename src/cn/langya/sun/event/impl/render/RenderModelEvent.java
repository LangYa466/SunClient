package cn.langya.sun.event.impl.render;

import cn.langya.sun.event.Event;
import net.minecraft.entity.EntityLivingBase;



public class RenderModelEvent extends Event.StateEvent {

    private final EntityLivingBase entity;
    private final Runnable modelRenderer;
    private final Runnable layerRenderer;

    public RenderModelEvent(EntityLivingBase entity, Runnable modelRenderer, Runnable layerRenderer) {
        this.entity = entity;
        this.modelRenderer = modelRenderer;
        this.layerRenderer = layerRenderer;
    }

    // @Exclude(Strategy.NAME_REMAPPING)
    public EntityLivingBase getEntity() {
        return entity;
    }

    // @Exclude(Strategy.NAME_REMAPPING)
    public void drawModel() {
        this.modelRenderer.run();
    }

    // @Exclude(Strategy.NAME_REMAPPING)
    public void drawLayers() {
        this.layerRenderer.run();
    }

}
