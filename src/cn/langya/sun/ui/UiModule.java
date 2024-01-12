// by paimon
package cn.langya.sun.ui;

import cn.langya.sun.Sun;
import cn.langya.sun.modules.Module;
import cn.langya.sun.utils.render.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;


public class UiModule extends Gui
{
    public static final Minecraft mc;
    public double moveX;
    public double moveY;
    public double posX;
    public double posY;
    public double width;
    public double height;
    public String name;
    public boolean state;
    
    public UiModule(final String name, final double posX, final double posY, final double width, final double height) {
        this.moveX = 0.0;
        this.moveY = 0.0;
        this.state = false;
        this.name = name;
        this.posX = posX / RenderUtil.getWidth();
        this.posY = posY / RenderUtil.getHeight();
        this.width = width;
        this.height = height;
    }
    
    public <T extends Module> T getModule(final Class<T> clazz) {
        return Sun.moduleManager.getModule(clazz);
    }
    
    public String getName() {
        return this.name;
    }
    
    public double getPosX() {
        return this.posX * RenderUtil.getWidth();
    }
    
    public double getPosY() {
        return this.posY * RenderUtil.getHeight();
    }
    
    public void setPosX(final double posX) {
        this.posX = posX / RenderUtil.getWidth();
    }
    
    public void setPosY(final double posY) {
        this.posY = posY / RenderUtil.getHeight();
    }
    
    public double getWidth() {
        return this.width;
    }
    
    public void setWidth(final double width) {
        this.width = width;
    }
    
    public double getHeight() {
        return this.height;
    }
    
    public void setHeight(final double height) {
        this.height = height;
    }
    
    public void setState(final boolean state) {
        if (state && !this.state) {
            Sun.eventManager.register(this);
        }
        else if (this.state && !state) {
            Sun.eventManager.unregister(this);
        }
        this.state = state;
    }
    
    public void toggle() {
        this.setState(!this.state);
    }
    
    public boolean getState() {
        return this.state;
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
}
