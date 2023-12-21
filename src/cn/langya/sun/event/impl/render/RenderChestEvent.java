package cn.langya.sun.event.impl.render;

import cn.langya.sun.event.Event;
import net.minecraft.tileentity.TileEntityChest;

public class RenderChestEvent extends Event {

    private final TileEntityChest entity;
    private final Runnable chestRenderer;

    public RenderChestEvent(TileEntityChest entity, Runnable chestRenderer) {
        this.entity = entity;
        this.chestRenderer = chestRenderer;
    }

    public TileEntityChest getEntity() {
        return entity;
    }

    public void drawChest() {
        this.chestRenderer.run();
    }

}
